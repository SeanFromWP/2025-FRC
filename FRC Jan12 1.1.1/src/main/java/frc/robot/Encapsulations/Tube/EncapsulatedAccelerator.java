package frc.robot.Encapsulations.Tube;

import com.revrobotics.spark.SparkMax;

import frc.robot.Constants.PortConstants;
import frc.robot.Constants.SpeedConstants;
import frc.robot.Configs;
import frc.robot.CommonFunctions.MotorFunctions;
import frc.robot.Extensions.LevelModes;
import frc.robot.Extensions.Motors;

public class EncapsulatedAccelerator implements LevelModes, Motors {
  private final SparkMax accelerator;

  public EncapsulatedAccelerator() {
    accelerator   = MotorFunctions.createMotor(PortConstants.kAcceleratorPort, false, Configs.acceleratorConfig);
  }
  
  // =========== LevelModes ============
  @Override
  public void l4Mode(){ //Equals moveUp
      accelerator.set(SpeedConstants.kAcceleratorL4);
  }

  @Override
  public void l3Mode(){
      accelerator.set(SpeedConstants.kAcceleratorL3);
  }

  @Override
  public void l2Mode(){
      accelerator.set(SpeedConstants.kAcceleratorL2);
  }

  @Override
  public void l1Mode(){
      accelerator.set(SpeedConstants.kAcceleratorL1);
  }

  // =========== Motors ============

  @Override
  public void stop() {
      accelerator.set(0);
  }
}