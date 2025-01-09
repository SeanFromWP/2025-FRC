package frc.robot.Encapsulations.Claw;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants.SubsystemConstants;
import frc.robot.CommonFunctions.MotorFunctions;
import frc.robot.Constants.MotorPortConstants;
import frc.robot.Configs;

public class EncapsulatedClawTurner {
   private final SparkMax clawTurner;
   private final RelativeEncoder clawTurnerEncoder;

  public EncapsulatedClawTurner() {
    clawTurner = MotorFunctions.createMotor(MotorPortConstants.kClawTurnerPort, false,Configs.clawTurnerConfig);
    clawTurnerEncoder = clawTurner.getEncoder();
  }

  public void resetClawTurnerEncoder(){
    clawTurnerEncoder.setPosition(0);
  }

  public void moveUpClawTurner(){
    clawTurner.set(1);
    if (targetReached(clawTurnerEncoder.getPosition(), true)){
      maintainClawTurner();
    }
  }           

  public void moveDownClawTurner(){
    clawTurner.set(-1);
    if (targetReached(clawTurnerEncoder.getPosition(), false)){
      maintainClawTurner();
    }
  }

  private void maintainClawTurner(){
    clawTurner.set(0);
  }

  private Boolean targetReached(double encoderValue, boolean isMovingUp){
    if (isMovingUp) {
        return encoderValue >= SubsystemConstants.kClawTurnerCycleEncoderCount;
    } else {
        return encoderValue <= -SubsystemConstants.kClawTurnerCycleEncoderCount;
    }
  }
}