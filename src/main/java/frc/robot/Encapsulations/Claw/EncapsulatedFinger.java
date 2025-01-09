// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Encapsulations.Claw;
import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.MotorPortConstants;
import frc.robot.Configs;
import frc.robot.CommonFunctions.MotorFunctions;


public class EncapsulatedFinger {
   //TODO
   private final SparkMax fingerMotor;

   public EncapsulatedFinger(){
      fingerMotor = MotorFunctions.createMotor(MotorPortConstants.kFingerPort, false, Configs.fingerConfig);
   }

   public void inMode(){
      fingerMotor.set(1);
   }
   public void outMode(){
      fingerMotor.set(-1);
   }
   public void stopMode(){
      MotorFunctions.stopMotor(fingerMotor);
 }
}