// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Encapsulations.Tube.EncapsulatedTentacle;
import frc.robot.Encapsulations.Tube.EncapsulatedShooter;
import frc.robot.Encapsulations.Tube.EncapsulatedLifter;

public class TubeSubsystem extends SubsystemBase {
   //TODO
   private final EncapsulatedTentacle tentacle;
   private final EncapsulatedLifter lifter;
   private final EncapsulatedShooter shooter;

   public TubeSubsystem(){
      tentacle = new EncapsulatedTentacle();
      lifter = new EncapsulatedLifter();
      shooter = new EncapsulatedShooter();
   }

   public void launchL1(){
      lifter.lowMode();
      tentacle.launchMode();
      shooter.shoot();
   }
   public void launchL2(){
      lifter.lowMode();
      tentacle.launchMode();
      shooter.shoot();
   }
   public void launchL3(){
      lifter.highMode();
      tentacle.launchMode();
      shooter.shoot();
   }
   public void launchL4(){
      lifter.lowMode();
      tentacle.downMode();
      shooter.shoot();
   }

   public void pickLowAlgae(){
      lifter.lowMode();
      tentacle.launchMode();
      tentacle.downMode();
   }

   public void pickHighAlgae(){
      lifter.highMode();
      tentacle.launchMode();
      tentacle.downMode();
   }

}