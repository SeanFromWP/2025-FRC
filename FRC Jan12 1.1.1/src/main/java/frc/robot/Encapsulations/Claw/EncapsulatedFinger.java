// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Encapsulations.Claw;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.PortConstants;
import frc.robot.Configs;
import frc.robot.CommonFunctions.MotorFunctions;
import frc.robot.Extensions.Motors;

public class EncapsulatedFinger implements Motors {
   private final SparkMax fingerMotor;
   private final DigitalInput coralIntakeLimitSwitch;

   public EncapsulatedFinger(){
      fingerMotor = MotorFunctions.createMotor(PortConstants.kFingerPort, false, Configs.fingerConfig);
      coralIntakeLimitSwitch = new DigitalInput(PortConstants.kFingerCoralIntakeLimitSwitchPort);
   }

   @Override
   public void stop(){
      fingerMotor.set(0);
   }

   public void inMode(){
      fingerMotor.set(1);
   }
   public void outMode(){
      fingerMotor.set(-1);
   }

   public Boolean coralDetected(){
      return coralIntakeLimitSwitch.get();
   }
}