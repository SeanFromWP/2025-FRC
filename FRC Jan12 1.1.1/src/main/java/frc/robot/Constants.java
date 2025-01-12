// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.first.math.geometry.Translation2d;
//import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */


public final class Constants {
  
  public static final class SubsystemConstants {
    //Claw[0]
    public static final double kClawTurnerCycleEncoderCount = 300; //TODO
    public static final double kClawTurnerTolerance = 2; //TODO
    public static final double kDriveDeadband = 0.09;

    public static final double kAimLowDeadband = 0.19;
    public static final double kAimHighDeadband = 0.4;

    public static final double kShootLowDeadband = 0.85;

    //Tube
  }

  public static final class SpeedConstants {
    public static final double kAcceleratorL4 = 1;
    public static final double kAcceleratorL3 = 0.8;
    public static final double kAcceleratorL2 = 0.6;
    public static final double kAcceleratorL1 = 0.4;

    public static final double kSliderSpareSpd = 0.5;
    public static final double kClawSpareSpd = 0.3;
  }


  public static final class PortConstants {
    //Claw
    public static final int kClawTurnerPort = 0; //FIXME
    public static final int kFingerPort = 0;

    public static final int kClawTurnerTopLimitSwitchPort = 0;
    public static final int kClawTurnerLowLimitSwitchPort = 0;
    public static final int kFingerCoralIntakeLimitSwitchPort = 0;

    //Tube
    public static final int kAcceleratorPort = 0;
    public static final int kShooterPort = 0;

    //Slide
    public static final int kSlideMotorPort = 0;
    
    public static final int kSlideCoralLimitSwitchPort = 0;
    public static final int kSlideLeftLimitSwitchPort = 0;
    public static final int kSlideRightLimitSwitchPort = 0;



  }
  
  public static final class RatioConstants {
    public static final double kSlideCMPerEncoder(double cm) {
      return cm * (8192/16)/Math.PI;
    }
  }

  public final class DrivebaseConstants {
    // drive motor channel
    public static final int kFrontLeftDriveMotorChannel = 22;
    public static final int kFrontRightDriveMotorChannel = 24;
    public static final int kBackLeftDriveMotorChannel = 26;
    public static final int kBackRightDriveMotorChannel = 28;

    // turning motor channel
    public static final int kFrontLeftTurningMotorChannel = 21;
    public static final int kFrontRightTurningMotorChannel = 23;
    public static final int kBackLeftTurningMotorChannel = 25;
    public static final int kBackRightTurningMotorChannel = 27;

    // turnning encoder channel
    public static final int kFrontLeftTurningEncoderChannel = 11;
    public static final int kFrontRightTurningEncoderChannel = 13;
    public static final int kBackLeftTurningEncoderChannel = 15;
    public static final int kBackRightTurningEncoderChannel = 17;

    // gyro channel
    public static final int kGyroPort = 60;

    // can coder magnet offset value
    public static final double kFrontLeftCanCoderMagOffset = -0.179199; //TODO
    public static final double kFrontRightCanCoderMagOffset = -0.294922;
    public static final double kBackLeftCanCoderMagOffset = 0.530517;
    public static final double kBackRightCanCoderMagOffset = -0.621826;

    public static final double kRobotWidth = 0.7;
    public static final double kRobotLength = 0.7;
    public static final double kRobotDiagonal = Math.sqrt(Math.pow(kRobotLength, 2.0) + Math.pow(kRobotWidth, 2.0));

    public static final double kMaxSpeed = 5.5;
    public static final double kMinSpeed = 0.2;
    public static final double kMaxAngularSpeed = kMaxSpeed / (kRobotDiagonal / 2.0); // rad/s

    public static final double xLimiterRateLimit = 5.0;
    public static final double yLimiterRateLimit = 5.0;
    public static final double rotLimiterRateLimit = 5.0;

    public static final Boolean kFrontLeftDriveMotorInverted = true;
    public static final Boolean kFrontRightDriveMotorInverted = false;
    public static final Boolean kBackLeftDriveMotorInverted = true;
    public static final Boolean kBackRightDriveMotorInverted = false;

    public static final Boolean kGyroInverted = false; // wheather gyro is under the robot

    public static final double kGyroOffSet = 0.0; //TODO
  }

  public static final class ModuleConstants {
    public static final double kWheelRadius = 0.0475; //TODO MK4i
    public static final double kModuleGearRate = 6.75;

    public static final double kMaxModuleDriveVoltage = 12.0;
    public static final double kMaxModuleTurningVoltage = 8.0;

    public static final double kDriveClosedLoopRampRate = 0.1;// 1 second 1 unit
    public static final double kTurningClosedLoopRampRate = 0.1;

    public static final double kDesireSpeedtoMotorVoltage = kMaxModuleDriveVoltage / DrivebaseConstants.kMaxSpeed;

    public static final double kMaxSpeedTurningDegree = 180.0;

    public static final double kPDriveController = kDesireSpeedtoMotorVoltage;
    public static final double kIDriveController = 0.0;
    public static final double kDDriveController = 0.0;

    public static final double kPRotController = kMaxModuleTurningVoltage / kMaxSpeedTurningDegree;
    public static final double kIRotController = 0.0;
    public static final double kDRotController = 0.0004;

    public static final Boolean kTurningMotorInverted = true;
  }
}
