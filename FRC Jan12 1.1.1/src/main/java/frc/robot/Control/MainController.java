// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.Control;

import frc.robot.Managers.ModeManager;

import frc.robot.Subsystems.ClawSubsystem;
import frc.robot.Subsystems.TubeSubsystem;
import frc.robot.Encapsulations.Tube.EncapsulatedHeightShifter;

public class MainController {

    private ButtonDetecting buttonDetecting;

    private ModeManager modeManager;
    private ClawSubsystem clawSubsystem;
    private TubeSubsystem tubeSubsystem;

    private EncapsulatedHeightShifter shifter;

    public MainController() {
        buttonDetecting = new ButtonDetecting();
        clawSubsystem = new ClawSubsystem();
        tubeSubsystem = new TubeSubsystem();
        shifter = new EncapsulatedHeightShifter();
    }

    
    /**
     * @param
     */

    public void checkButtonStatus() {
        // ======== 海藻模式 ========
        if (buttonDetecting.isTargetAlgae()) {
            modeManager.updateAlgae();
        }
        
        //======== 1. 巡邏模式 ========
        //不用按鈕

        //======== 2. 發射模式 ========
        if (buttonDetecting.launchAlgae()) {
            clawSubsystem.launchAlgaeMode();
        }     

    




        // Tube Subsystem

        if (buttonDetecting.isTargetCoral()) {
            modeManager.updateCoral();
        }

        if (buttonDetecting.shootLeft()) {
            if (shifter.currentTubeHeight() == EncapsulatedHeightShifter.Height.L4) {
                tubeSubsystem.l4Mode();
                tubeSubsystem.shoot();
            } else if (shifter.currentTubeHeight() == EncapsulatedHeightShifter.Height.L3) {
                tubeSubsystem.l3Mode();
                tubeSubsystem.shoot();
            } else if (shifter.currentTubeHeight() == EncapsulatedHeightShifter.Height.L2) {
                tubeSubsystem.l2Mode();
                tubeSubsystem.shoot();
            } else if (shifter.currentTubeHeight() == EncapsulatedHeightShifter.Height.L1) {
                tubeSubsystem.l1Mode();
                tubeSubsystem.shoot();
            }
        }
    
        // Climb Subsystem    
        if (buttonDetecting.isClimbingCageStep1()) {
            // Implement action
        }
    
        if (buttonDetecting.isClimbingCageStep2()) {
            // Implement action
        }
    }
}
