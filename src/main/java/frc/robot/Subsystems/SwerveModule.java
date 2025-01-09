// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;


import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;

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
import frc.robot.Configs;
import frc.robot.CommonFunctions.MotorFunctions;

public class SwerveModule extends SubsystemBase {
  private final SparkMax driveMotor;
  private final SparkMax turningMotor;

  private final CANcoder turningEncoder;

  private final RelativeEncoder driveEncoder;

  private final PIDController rotController;

  private final String name;

  public SwerveModule(int driveMotorChannel,
      int turningMotorChannel,
      int turningEncoderChannel, boolean driveInverted, double canCoderMagOffset, String name) {

    this.name = name;

    driveMotor = MotorFunctions.createMotor(driveMotorChannel, driveInverted, Configs.driveConfig);
    turningMotor = MotorFunctions.createMotor(turningMotorChannel, driveInverted, Configs.turningConfig);
    
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
    resetAllEncoder();
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
