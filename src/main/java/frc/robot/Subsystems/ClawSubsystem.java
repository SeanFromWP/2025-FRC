// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Encapsulations.Claw.EncapsulatedClawTurner;
import frc.robot.Constants.MotorPortConstants;
import frc.robot.CommonFunctions.MotorFunctions;
import frc.robot.Configs;

public class ClawSubsystem extends SubsystemBase {
   private final SparkMax finger;
   private final EncapsulatedClawTurner clawTurner;

   public ClawSubsystem() {
     finger = MotorFunctions.createMotor(MotorPortConstants.kFingerPort, false, Configs.fingerConfig);
     clawTurner = new EncapsulatedClawTurner();
   }

   public void initialClawMode(){
     finger.set(0);
     clawTurner.moveUpClawTurner();
   }

   public void patrolMode() {
     finger.set(1);
     clawTurner.moveDownClawTurner();
   }

   public void scoringMode() {
     finger.set(0);
     clawTurner.moveDownClawTurner();
   }

   public void launchAlgaeMode() {
     finger.set(-1);
     clawTurner.moveDownClawTurner();
   }

   public Boolean objectCaptured() {
    return true;
  }

   public Boolean isLaunching() {
    return false;
  }

   private Boolean deepBoolean;

   public void deepClimpMode(){
      deepBoolean = true;
      if (deepBoolean){
         //Main
      }
   }
   public void stopClimbing(){
      deepBoolean = false;
   }
}