// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Encapsulations.Tube;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.AbsoluteEncoder;

import frc.robot.Constants.MotorPortConstants;
import frc.robot.Constants.RatioConstants;
import frc.robot.Configs;
import frc.robot.CommonFunctions.MotorFunctions;


public class EncapsulatedSlider {
   //TODO
   private final SparkMax sliderMotor;
   private final AbsoluteEncoder sliderEncoder;

   public EncapsulatedSlider(){
      sliderMotor = MotorFunctions.createMotor(MotorPortConstants.kSlideMotorPort, false, Configs.slideConfig);
      sliderEncoder = sliderMotor.getAbsoluteEncoder();
   }

   public void moveSlide(double cmOffset) {
      double currentEncoderValue = sliderEncoder.getPosition();
      double targetEncoderValue = (currentEncoderValue + RatioConstants.kSlideCMPerEncoder(cmOffset)) % 8192;
      
      boolean movePositively = (cmOffset >= 0);

      while (true) {
          currentEncoderValue = sliderEncoder.getPosition();

          if (targetReached(currentEncoderValue, targetEncoderValue)){
            MotorFunctions.stopMotor(sliderMotor);
          }
          if (movePositively){
            movePositively();
          } else {
            moveNegatively();
          }
      }
  }
  
  private boolean targetReached(double current, double target) {
      double tolerance = 2;
      return Math.abs(current - target) <= tolerance;
  }
  
  private void movePositively() {
      sliderMotor.set(1);
  }
  
  private void moveNegatively() {
      sliderMotor.set(-1);
  }
}