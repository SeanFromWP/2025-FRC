// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.Control;
import frc.robot.Subsystems.ClawSubsystem;
import frc.robot.Subsystems.TubeSubsystem;

public class MainController {

    private ButtonDetecting buttonDetecting;
    private ClawSubsystem clawSubsystem;
    private TubeSubsystem tubeSubsystem;


    public MainController() {
        buttonDetecting = new ButtonDetecting();
        clawSubsystem = new ClawSubsystem();
        tubeSubsystem = new TubeSubsystem();
    }

    public void checkButtonStatus() {

    //Claw subsystem
        if (buttonDetecting.isPatrolMode()) {
            clawSubsystem.patrolMode();
        }

        if (buttonDetecting.isScoringMode()) {
            clawSubsystem.patrolMode();
        }

        if (buttonDetecting.isLaunchingAlgae()) {
            clawSubsystem.launchAlgaeMode();
        }

    //Tube Subsystem
        if (buttonDetecting.isLaunchingL1Coral()) {
            tubeSubsystem.launchL1();
        }
        if (buttonDetecting.isLaunchingL2Coral()) {
            tubeSubsystem.launchL2();
        }
        if (buttonDetecting.isLaunchingL3Coral()) {
            tubeSubsystem.launchL3();
        }
        if (buttonDetecting.isLaunchingL4Coral()) {
            tubeSubsystem.launchL4();
        }

    //Climb Subsystem    
        if (buttonDetecting.isClimbingDeepCage()) {
            
        }
    }
}
