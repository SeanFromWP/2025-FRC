package frc.robot.Encapsulations.Tube;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants.MotorPortConstants;
import frc.robot.Configs;
import frc.robot.CommonFunctions.MotorFunctions;

public class EncapsulatedShooter {
   private final SparkMax shooter;
   private final RelativeEncoder shooterEncoder;

  public EncapsulatedShooter() {
    shooter   = MotorFunctions.createMotor(MotorPortConstants.kShooterPort, false, Configs.shooterConfig);
    shooterEncoder = shooter.getEncoder();
  }

  public void resetshooterEncoder(){
    shooterEncoder.setPosition(0);
  }

  public void shoot(){ //Equals moveUp
    shooter.set(1);
  }

  public void pauseMode(){ //Equals stop
    MotorFunctions.stopMotor(shooter);
  }
}