package frc.robot.Managers;

import frc.robot.Subsystems.ClawSubsystem;
import frc.robot.Subsystems.TubeSubsystem;
import frc.robot.Subsystems.SlideSubsystem;

public class StatusManager {

   
  // =========== Intro. ============

  /**
   * @param StatusManager 狀態管理系統，所有子系統會把布林值傳進來。
   */

   private final ClawSubsystem clawSubsystem;
   private final TubeSubsystem tubeSubsystem;
   private final SlideSubsystem slideSubsystem;

   public StatusManager(){
      clawSubsystem = new ClawSubsystem();
      tubeSubsystem = new TubeSubsystem();
      slideSubsystem = new SlideSubsystem();
   }

   //========== 第1階段：抓到了嗎 ==========


   public Boolean hasCoral(){
      return clawSubsystem.coralCaptured();
   }

   public Boolean hasAlgae(){
      return clawSubsystem.algaeCaptured();
   }

   //========== 第2階段：還在不在 ==========

   public Boolean isCoralLaunched() {
      return tubeSubsystem.coralLaunched();
   }

   public Boolean isAlgaeLaunched() {
      return clawSubsystem.algaeLaunched();
   }

   //========== 第3階段：重設所有Boolean ==========

   public void coralCompleted() {
      clawSubsystem.resetSubsystem();
      slideSubsystem.resetSubsystem();
      tubeSubsystem.resetSubsystem();
   }

   public void algaeCompleted() {
      clawSubsystem.resetSubsystem();
      slideSubsystem.resetSubsystem();
      tubeSubsystem.resetSubsystem();
   }
}
