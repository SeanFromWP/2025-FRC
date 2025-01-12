package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Encapsulations.Slide.EncapsulatedSlider;
import frc.robot.Extensions.GammaSubsystem;

public class SlideSubsystem extends SubsystemBase implements GammaSubsystem{
   // =========== Intro. ============

  /**
   * @param SlideSubsystem 滑軌子系統，負責移動砲管、上膛跟瞄準。
   * @param slider         滑軌的馬達。
   */

   private final EncapsulatedSlider slider;


  /** @param Boolean 下面的布林值要給StatusManager用。*/

   private Boolean isCoralLoaded = false;
   private Boolean isSlideAimed  = false;

  // =========== 程式開始 ============

  public SlideSubsystem() {
    slider = new EncapsulatedSlider();
  } 

  // =========== 0. 初始設定 ============

  @Override
  public void initializeSubsystem() {
    slider.center();
  }  

  @Override
  public void resetSubsystem(){
    isCoralLoaded = false;
    isSlideAimed = false;
  }  

  // =========== 第1階段：上膛 ============

  public void loadCoral(){
     slider.loadCoral();
     isCoralLoaded = true;
  }

  // =========== 第2階段：瞄準 ============

  public void aimTube(double cmOffset){
     slider.moveSlide(cmOffset); //滑軌照著limelight回傳的數值移動
     isSlideAimed = true;
  }

  // =========== ^_^ 參數區 ^_^ ============

  public Boolean coralLoaded(){
    return isCoralLoaded;
  }

  public Boolean slideAimed(){
    return isSlideAimed;
  }

  // =========== ^_^ 備用 ^_^ ============

  public void spareLeft(){
    slider.spareLeft();
  }
  public void spareRight(){
    slider.spareRight();
  }
}
