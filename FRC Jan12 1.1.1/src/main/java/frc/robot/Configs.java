// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.config.SparkMaxConfig;

public final class Configs {

    public static final SparkMaxConfig driveConfig = new SparkMaxConfig();
    public static final SparkMaxConfig turningConfig = new SparkMaxConfig();

    public static final SparkMaxConfig slideConfig = new SparkMaxConfig();
    public static final SparkMaxConfig clawTurnerConfig = new SparkMaxConfig();
    public static final SparkMaxConfig fingerConfig = new SparkMaxConfig();

    public static final SparkMaxConfig acceleratorConfig = new SparkMaxConfig();
    public static final SparkMaxConfig shooterConfig = new SparkMaxConfig();

    static {
        configDriveMotor();
        configTurningMotor();

        configSlideMotor();
        configClawTurner();
        configFingerMotor();

        configAcceleratorMotor();
        configShooterMotor();
    }

    private static void configDriveMotor() {
      driveConfig.smartCurrentLimit(40);
      driveConfig.idleMode(SparkMaxConfig.IdleMode.kBrake);
      driveConfig.inverted(false);
      driveConfig.voltageCompensation(12.0);
      driveConfig.openLoopRampRate(0.5);
    }

    private static void configTurningMotor() {
      turningConfig.smartCurrentLimit(40);
      turningConfig.idleMode(SparkMaxConfig.IdleMode.kBrake);
      turningConfig.inverted(false);
      turningConfig.voltageCompensation(12.0);
      turningConfig.openLoopRampRate(0.5);
    }

    private static void configSlideMotor() {
      slideConfig.smartCurrentLimit(40);
      slideConfig.idleMode(SparkMaxConfig.IdleMode.kBrake);
      slideConfig.inverted(false);
      slideConfig.voltageCompensation(12.0);
      slideConfig.openLoopRampRate(0.5);
    }

    private static void configClawTurner() {
      clawTurnerConfig.smartCurrentLimit(40);
      clawTurnerConfig.idleMode(SparkMaxConfig.IdleMode.kBrake);
      clawTurnerConfig.inverted(false); //TODO
      clawTurnerConfig.voltageCompensation(12.0);
      clawTurnerConfig.openLoopRampRate(0.5);
    }

    private static void configFingerMotor() {
      fingerConfig.smartCurrentLimit(40);
      fingerConfig.idleMode(SparkMaxConfig.IdleMode.kBrake);
      fingerConfig.inverted(false); //TODO
      fingerConfig.voltageCompensation(12.0);
      fingerConfig.openLoopRampRate(0.5);
    }

    private static void configAcceleratorMotor() {
      acceleratorConfig.smartCurrentLimit(40);
      acceleratorConfig.idleMode(SparkMaxConfig.IdleMode.kBrake);
      acceleratorConfig.inverted(false);
      acceleratorConfig.voltageCompensation(12.0);
      acceleratorConfig.openLoopRampRate(0.5);
    }

    private static void configShooterMotor() {
      shooterConfig.smartCurrentLimit(40);
      shooterConfig.idleMode(SparkMaxConfig.IdleMode.kBrake);
      shooterConfig.inverted(false);
      shooterConfig.voltageCompensation(12.0);
      shooterConfig.openLoopRampRate(0.5);
    }
}