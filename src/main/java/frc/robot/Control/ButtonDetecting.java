// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Control;
import edu.wpi.first.wpilibj.PS4Controller;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class ButtonDetecting extends PS4Controller {

    public ButtonDetecting(){
      super(0);
    }
    
    //claw

    public boolean isPatrolMode() {
        return this.getCircleButton();
    }

    public boolean isScoringMode() {
        return this.getCircleButton();
    }

    //launch

    public boolean isLaunchingAlgae() {
        return this.getL2Button();
    }

    public boolean isLaunchingL1Coral() {
        return this.getR2Button();
    }

    public boolean isLaunchingL2Coral() {
        return this.getR2Button();
    }

    public boolean isLaunchingL3Coral() {
        return this.getR2Button();
    }

    public boolean isLaunchingL4Coral() {
        return this.getR2Button();
    }
    //Climb

    public boolean isClimbingDeepCage() {
        return this.getSquareButton();
    }

 /*   public boolean isClimbingShallowCage() {
        return this.getTriangleButton();
    } */
}