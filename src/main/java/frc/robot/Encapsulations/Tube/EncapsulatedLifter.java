package frc.robot.Encapsulations.Tube;

import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.MotorPortConstants;
import frc.robot.Configs;
import frc.robot.CommonFunctions.MotorFunctions;

public class EncapsulatedLifter {
   private final SparkMax lifter;

  public EncapsulatedLifter() {
    lifter   = MotorFunctions.createMotor(MotorPortConstants.kLifterPort, false, Configs.lifterConfig);
  }

  public void lowMode(){
  }

  public void highMode(){
  }

  public void downMode(){ //Equals moveUp
  }

  public void pauseMode(){ //Equals stop
    MotorFunctions.stopMotor(lifter);
  }
}