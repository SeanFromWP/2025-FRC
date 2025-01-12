package frc.robot.Encapsulations.Tube;

import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.PortConstants;
import frc.robot.Configs;
import frc.robot.CommonFunctions.MotorFunctions;
import frc.robot.Extensions.Motors;

public class EncapsulatedShooter implements Motors {
   private final SparkMax shooter;

  public EncapsulatedShooter() {
    shooter   = MotorFunctions.createMotor(PortConstants.kShooterPort, false, Configs.shooterConfig);
  }

  public void shoot(){
    shooter.set(1); //shooter要轉
  }

  @Override
  public void stop(){
      shooter.set(0);
  }
}