// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Encapsulations.Claw.EncapsulatedClawTurner;
import frc.robot.Extensions.GammaSubsystem;

public class ClimbSubsystem extends SubsystemBase implements GammaSubsystem {
  private final EncapsulatedClawTurner clawTurner;
  public ClimbSubsystem(){
    clawTurner = new EncapsulatedClawTurner();
  }

  // =========== 0. 巡航模式 ============
  @Override
  public void initializeSubsystem() {
    clawTurner.stop();
  }

  @Override
  public void resetSubsystem() {
    clawTurner.stop();
  }

  // =========== 1. 巡航模式 ============

  public void hitCage(){
    clawTurner.moveDown();
    //FIXME
  }

  public void hookCage(){
    //FIXME
  }

  public void liftToCage(){
    //FIXME
  }

  private Boolean deepBoolean;

  public void deepClimpMode(){
     deepBoolean = true;
     if (deepBoolean){
        // Main
     }
  }

  public void stopClimbing(){
     deepBoolean = false;
  }
}