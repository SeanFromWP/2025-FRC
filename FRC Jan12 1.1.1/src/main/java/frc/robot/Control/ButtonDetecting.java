// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Control;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.SubsystemConstants;
/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class ButtonDetecting extends XboxController {

    public ButtonDetecting(){
      super(0);
    }
    
    //Mode

    public Boolean isTargetAlgae(){
        return this.getYButton();
    }

    public Boolean isTargetCoral(){
        return this.getBButton();
    }

    public Boolean isPickingCoral() {
        return this.getLeftBumperButtonPressed();
    }

    public Boolean isTargetClimb(){
        return this.getAButton();
    }

    //Aim

    public Boolean aimLeft() {
        return (this.getLeftTriggerAxis() > SubsystemConstants.kAimLowDeadband)
            && (SubsystemConstants.kAimHighDeadband > this.getLeftTriggerAxis());
    }

    public Boolean aimRight() {
        return (this.getRightTriggerAxis() > SubsystemConstants.kAimLowDeadband)
            && (SubsystemConstants.kAimHighDeadband > this.getLeftTriggerAxis());
    }

    //Shoot

    public Boolean shootLeft() {
        return (this.getLeftTriggerAxis() > SubsystemConstants.kShootLowDeadband);
    }

    public Boolean shootRight() {
        return (this.getRightTriggerAxis() > SubsystemConstants.kShootLowDeadband);
    }

    public Boolean launchAlgae() {
        return (this.getRightTriggerAxis() > SubsystemConstants.kShootLowDeadband);
    }

    public Boolean isClimbingCageStep1(){
       return this.getStartButton();
    }

    public Boolean isClimbingCageStep2(){
        return this.getBackButton();
     }

    // Backup (hat)
    public Boolean isClawUp(){
        return this.getPOV() == 0;
    }

    public Boolean isClawDown(){
        return this.getPOV() == 180;
    }

    public Boolean isSlideLeft(){
        return this.getPOV() == 270;
    }

    public Boolean isSlideRight(){
        return this.getPOV() == 90;
    }
}