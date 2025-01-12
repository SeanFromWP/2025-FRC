// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

public final class CommonFunctions {
  
  public static final class MotorFunctions {

    public static SparkMax createMotor(int port, Boolean isBrushed, SparkMaxConfig config) {
      SparkMax.MotorType motorType = isBrushed ? SparkMax.MotorType.kBrushed : SparkMax.MotorType.kBrushless;
      SparkMax motor = new SparkMax(port, motorType);
      
      motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
      return motor;
    }
  }
}