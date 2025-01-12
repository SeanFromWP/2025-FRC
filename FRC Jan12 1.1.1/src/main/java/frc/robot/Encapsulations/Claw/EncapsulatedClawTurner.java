package frc.robot.Encapsulations.Claw;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;

import com.revrobotics.RelativeEncoder;

import frc.robot.Constants.SubsystemConstants;
import frc.robot.CommonFunctions.MotorFunctions;
import frc.robot.Constants.PortConstants;
import frc.robot.Constants.SpeedConstants;
import frc.robot.Configs;
import frc.robot.Extensions.Motors;

public class EncapsulatedClawTurner implements Motors {
   private final SparkMax clawTurner;
   private final RelativeEncoder clawTurnerEncoder;
   private final DigitalInput clawTurnerTopLimitSwitch;
   private final DigitalInput clawTurnerLowLimitSwitch;

  public EncapsulatedClawTurner() {
    clawTurner = MotorFunctions.createMotor(PortConstants.kClawTurnerPort, false,Configs.clawTurnerConfig);
    clawTurnerEncoder = clawTurner.getEncoder();
    clawTurnerTopLimitSwitch = new DigitalInput(PortConstants.kClawTurnerTopLimitSwitchPort);
    clawTurnerLowLimitSwitch = new DigitalInput(PortConstants.kClawTurnerLowLimitSwitchPort);
  }

  @Override
  public void stop(){
    clawTurner.set(0);
  }

  public void resetClawTurnerEncoder(){
    clawTurnerEncoder.setPosition(0);
  }

  public void moveToTopLimit(){
    clawTurner.set(1);
    if (clawTurnerTopLimitSwitch.get()){
      stop();
    }
  }

  public void moveToLowLimit(){
    clawTurner.set(-1);
    if (clawTurnerLowLimitSwitch.get()){
      stop();
    }
  }

  public void moveUp(){
    clawTurner.set(1);
    if (targetReached(clawTurnerEncoder.getPosition(), true)){
      stop();
    }
  }           

  public void moveDown(){
    clawTurner.set(-1);
    if (targetReached(clawTurnerEncoder.getPosition(), false)){
      stop();
    }
  }

  private Boolean targetReached(double encoderValue, Boolean isMovingUp){
    if (isMovingUp) {
        return encoderValue >= SubsystemConstants.kClawTurnerCycleEncoderCount;
    } else {
        return encoderValue <= -SubsystemConstants.kClawTurnerCycleEncoderCount;
    }
  }

  //======== Spare =========
  public void spareUp() {
    clawTurner.set(SpeedConstants.kClawSpareSpd);
  }

  public void spareDown() {
    clawTurner.set(-SpeedConstants.kClawSpareSpd);
  }

}