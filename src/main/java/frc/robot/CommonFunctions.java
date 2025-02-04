// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.first.math.geometry.Translation2d;
//import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
//import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

public final class CommonFunctions {
  
  public static final class MotorFunctions {

    public static SparkMax createMotor(int port, boolean isBrushed, SparkMaxConfig config) {
      SparkMax.MotorType motorType = isBrushed ? SparkMax.MotorType.kBrushed : SparkMax.MotorType.kBrushless;
      SparkMax motor = new SparkMax(port, motorType);
      
      motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
      return motor;
  }
  

    public static final void stopMotor(SparkMax motor){
      motor.set(0);
    }
  }
}