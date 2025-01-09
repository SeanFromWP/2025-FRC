package frc.robot.Encapsulations.Tube;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants.MotorPortConstants;
import frc.robot.Configs;
import frc.robot.CommonFunctions.MotorFunctions;

public class EncapsulatedTentacle {
   private final SparkMax tentacle;
   private final RelativeEncoder tentacleEncoder;

  public EncapsulatedTentacle() {
    tentacle   = MotorFunctions.createMotor(MotorPortConstants.kTentaclePort, false, Configs.tentacleConfig);
    tentacleEncoder = tentacle.getEncoder();
  }

  public void resetTentacleEncoder(){
    tentacleEncoder.setPosition(0);
  }

  public void launchMode(){
    tentacle.set(1); //TODO
  }

  public void algaeMode(){
    tentacle.set(-1);
  }

  public void downMode(){
    tentacle.set(0);
  }
}