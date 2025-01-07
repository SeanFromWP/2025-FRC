// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivebaseConstants;
import frc.robot.Constants.ModuleConstants;

public class SwerveModule extends SubsystemBase {
  /** Creates a new SwerveModule. */

  private final SparkMax driveMotor;
  private final SparkMax turningMotor;

  private final SparkMaxConfig driveConfig;
  private final SparkMaxConfig turningConfig;

  private final CANcoder turningEncoder;

  private final RelativeEncoder driveEncoder;

  private final PIDController rotController;

  private final String name;

  public SwerveModule(int driveMotorChannel,
      int turningMotorChannel,
      int turningEncoderChannel, boolean driveInverted, double canCoderMagOffset, String name) {

    this.name = name;

    driveMotor = new SparkMax(driveMotorChannel, SparkMax.MotorType.kBrushless);
    turningMotor = new SparkMax(turningMotorChannel, SparkMax.MotorType.kBrushless);

    driveConfig = new SparkMaxConfig();
    turningConfig = new SparkMaxConfig();

    driveMotor.configure(driveConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    turningEncoder = new CANcoder(turningEncoderChannel);
    CANcoderConfiguration turningEncoderConfiguration = new CANcoderConfiguration();
    turningEncoderConfiguration.MagnetSensor.MagnetOffset = canCoderMagOffset;
    turningEncoder.getConfigurator().apply(turningEncoderConfiguration);

    driveEncoder = driveMotor.getEncoder();

    rotController = new PIDController(ModuleConstants.kPRotController, ModuleConstants.kIRotController,
        ModuleConstants.kDRotController);
    rotController.enableContinuousInput(-180.0, 180.0);

    init();
  }

  public void init() {
    configDriveMotor();
    configTurningMotor();
    resetAllEncoder();
  }

  private void configDriveMotor() {
    driveConfig.smartCurrentLimit(40); // 设置智能电流限制
    driveConfig.idleMode(SparkMaxConfig.IdleMode.kBrake); // 设置空闲模式为制动
    driveConfig.inverted(false); // 设置电机不反转
    driveConfig.voltageCompensation(12.0); // 设置电压补偿为12V
    driveConfig.openLoopRampRate(0.5); // 设置开环斜坡率
  }

  private void configTurningMotor() {
    turningConfig.smartCurrentLimit(40); // 设置智能电流限制
    turningConfig.idleMode(SparkMaxConfig.IdleMode.kBrake); // 设置空闲模式为制动
    turningConfig.inverted(false); // 设置电机不反转
    turningConfig.voltageCompensation(12.0); // 设置电压补偿为12V
    turningConfig.openLoopRampRate(0.5); // 设置开环斜坡率
  }

  public void resetAllEncoder() {
    driveEncoder.setPosition(0);
  }

  public void stopModule() {
    driveMotor.set(0);
    turningMotor.set(0);
  }

  // to get the single swerveModule speed and the turning rate
  public SwerveModuleState getState() {
    return new SwerveModuleState(
        getDriveRate(), new Rotation2d(Math.toRadians(getRotation())));

  }

  // to get the drive distance
  public double getDriveDistance() {
    return driveEncoder.getPosition() / ModuleConstants.kModuleGearRate * 2.0 * Math.PI * ModuleConstants.kWheelRadius;
  }

  // calculate the rate of the drive
  public double getDriveRate() {
    return driveEncoder.getVelocity() / 60.0 / ModuleConstants.kModuleGearRate * 2.0 * Math.PI
        * ModuleConstants.kWheelRadius;
  }

  // to get rotation of turning motor
  public double getRotation() {
    return turningEncoder.getAbsolutePosition().getValueAsDouble() * 360.0;
  }

  // to the get the postion by wpi function
  public SwerveModulePosition getPosition() {
    return new SwerveModulePosition(
        getDriveDistance(), new Rotation2d(Math.toRadians(getRotation())));
  }

  public double[] optimizeOutputVoltage(SwerveModuleState goalState, double currentTurningDegree) {
    goalState.optimize(Rotation2d.fromDegrees(currentTurningDegree)); // Use instance method
    double driveMotorVoltage = goalState.speedMetersPerSecond * ModuleConstants.kDesireSpeedtoMotorVoltage;
    double turningMotorVoltage = rotController.calculate(currentTurningDegree, goalState.angle.getDegrees());
    double[] moduleState = { driveMotorVoltage, turningMotorVoltage };
    return moduleState;
}


  public void setDesiredState(SwerveModuleState desiredState) {
    if (Math.abs(desiredState.speedMetersPerSecond) < DrivebaseConstants.kMinSpeed) {
      stopModule();
    } else {
      var moduleState = optimizeOutputVoltage(desiredState, getRotation());
      driveMotor.setVoltage(moduleState[0]);
      turningMotor.setVoltage(moduleState[1]);
    }
  }

  public void resetTurningDegree() {
    double turningDegreeVoltage = rotController.calculate(getRotation(), 0);
    turningMotor.setVoltage(turningDegreeVoltage);
  }

  public void setTurningDegree90() {
    double turningDegreeTo90Voltage = rotController.calculate(getRotation(), 90);
    turningMotor.setVoltage(turningDegreeTo90Voltage);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber(name + "_ModuleDistance", getDriveDistance());
    SmartDashboard.putNumber(name + "_ModuleVelocity", getDriveRate());
    SmartDashboard.putNumber(name + "_ModuleRotation", getRotation());
    SmartDashboard.putData(name + "_rotController", rotController);
  }

}
