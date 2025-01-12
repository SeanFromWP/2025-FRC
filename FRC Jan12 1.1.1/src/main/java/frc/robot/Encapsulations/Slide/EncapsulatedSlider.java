// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Encapsulations.Slide;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.AbsoluteEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.PortConstants;
import frc.robot.Constants.RatioConstants;
import frc.robot.Configs;
import frc.robot.CommonFunctions.MotorFunctions;
import frc.robot.Extensions.Motors;
import frc.robot.Constants.SpeedConstants;

public class EncapsulatedSlider implements Motors{
   //TODO
   private final SparkMax sliderMotor;
   private final AbsoluteEncoder sliderEncoder;
   private final DigitalInput coralLimitSwitch;
   private final DigitalInput leftLimitSwitch;
   private final DigitalInput rightLimitSwitch;
   private double targetEncoderValue;
   private boolean isMoving = false;

   public EncapsulatedSlider(){
      sliderMotor = MotorFunctions.createMotor(PortConstants.kSlideMotorPort, false, Configs.slideConfig);
      sliderEncoder = sliderMotor.getAbsoluteEncoder();
      coralLimitSwitch = new DigitalInput(PortConstants.kSlideCoralLimitSwitchPort);
      leftLimitSwitch = new DigitalInput(PortConstants.kSlideLeftLimitSwitchPort);
      rightLimitSwitch = new DigitalInput(PortConstants.kSlideRightLimitSwitchPort);
   }

   @Override
    public void stop(){
        sliderMotor.set(0);
    }

   public void loadCoral(){
      if (coralLimitSwitch.get()){
         loading();
      }
   }

   public void center(){
    //TODO
   }

   public void moveSlide(double cmOffset) {
    double currentEncoderValue = sliderEncoder.getPosition();
    targetEncoderValue = (currentEncoderValue + RatioConstants.kSlideCMPerEncoder(cmOffset)) % 8192;
    isMoving = true;
}

public void update() {
  if (!isMoving) return;

  double currentEncoderValue = sliderEncoder.getPosition();
  if (targetReached(currentEncoderValue, targetEncoderValue)) {
      stop();
      isMoving = false;
  } else {
      if (targetEncoderValue > currentEncoderValue) {
          moveRight();
      } else {
          moveLeft();
      }
  }
}
  
  private Boolean targetReached(double current, double target) {
      double tolerance = 2;
      return Math.abs(current - target) <= tolerance;
  }

  private void loading(){
    leftEnd();    //TODO
    rightEnd();
    leftEnd();
    rightEnd();
 }

  private void leftEnd(){
    sliderMotor.set(-1);
    if (leftLimitSwitch.get()) {
      stop();
    }
  }
  private void rightEnd(){
    sliderMotor.set(1);
    if (rightLimitSwitch.get()) {
      stop();
    }
  }
  
  private void moveRight() {
      sliderMotor.set(1);
  }
  
  private void moveLeft() {
      sliderMotor.set(-1);
  }

  //======== Spare =========

  public void spareRight() {
    sliderMotor.set(SpeedConstants.kSliderSpareSpd);
  }

  public void spareLeft() {
    sliderMotor.set(-SpeedConstants.kSliderSpareSpd);
  }
}