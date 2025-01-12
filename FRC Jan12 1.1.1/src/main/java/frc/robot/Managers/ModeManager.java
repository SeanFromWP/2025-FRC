package frc.robot.Managers;

import frc.robot.Control.ButtonDetecting;
import frc.robot.Subsystems.ClawSubsystem;
import frc.robot.Subsystems.TubeSubsystem;
import frc.robot.Subsystems.SlideSubsystem;

public class ModeManager {

   // =========== Intro. ============

  /**
   * @param ModeManager 模式管理系統，負責切換到珊瑚、海藻、攀爬3種模式。
   */

   private final StatusManager statusManager;

   private final ClawSubsystem clawSubsystem;
   private final TubeSubsystem tubeSubsystem;
   private final SlideSubsystem slideSubsystem;
   private final ButtonDetecting buttonDetecting;

   // 定義 CoralMode 和 AlgaeMode 的 enum 狀態
   public enum CoralMode {
       PATROL_CORAL,   // 如果 GetCoral = true 則跳出
       LOAD_CORAL,     // 自動執行 load(); 然後跳出
       LAUNCH_CORAL,   // 如果 Launched = true 則跳出
       COMPLETED
   }

   public enum AlgaeMode {
       PATROL_ALGAE,   // 如果 GetAlgae = true 則跳出
       LAUNCH_ALGAE,   // 如果 Launched = true 則跳出
       COMPLETED
   }

   private CoralMode currentCoralMode;
   private AlgaeMode currentAlgaeMode;

   public ModeManager(){
      buttonDetecting = new ButtonDetecting();
      statusManager = new StatusManager();
      clawSubsystem = new ClawSubsystem();
      tubeSubsystem = new TubeSubsystem();
      slideSubsystem = new SlideSubsystem();
      currentCoralMode = CoralMode.COMPLETED; // 初始狀態為完成
      currentAlgaeMode = AlgaeMode.COMPLETED; // 初始狀態為完成
   }

   //========== 第1階段：要抓誰 ==========

   /** @param 啟動珊瑚模式 */
   public void startCoralMode() {
      // 檢查是否可以啟動 CoralMode
      if (!isCoralRunning() && !isAlgaeRunning()) {
         currentCoralMode = CoralMode.PATROL_CORAL; // 設置珊瑚模式為巡邏
      }
   }

   /** @param 啟動海藻模式 */
   public void startAlgaeMode() {
      // 檢查是否可以啟動 AlgaeMode
      if (!isCoralRunning() && !isAlgaeRunning()) {
         currentAlgaeMode = AlgaeMode.PATROL_ALGAE; // 設置海藻模式為巡邏
      }
   }
  

   //========== 第2階段：進入模式 ==========

   /** @param 珊瑚模式 */

   public void updateCoral() {
      switch (currentCoralMode) {
         case PATROL_CORAL:
         
            //Action
            if (buttonDetecting.isPickingCoral()){ //FIXME
            clawSubsystem.pickCoralMode();
            } else {
               clawSubsystem.catchCoralMode();
            }
            
            //Break condition：收到珊瑚
            if (statusManager.hasCoral()) {
               currentCoralMode = CoralMode.LOAD_CORAL;
            }

            break;

         case LOAD_CORAL:
            //Action
            
            slideSubsystem.loadCoral();

            //Breaks automatically
            currentCoralMode = CoralMode.LAUNCH_CORAL;
            break;

         case LAUNCH_CORAL:
            //Action
            tubeSubsystem.warmUp();

            //Break condition
            if (statusManager.isCoralLaunched()) {
               currentCoralMode = CoralMode.COMPLETED;
            }
            break;

         case COMPLETED:
            //Reset all booleans
            statusManager.coralCompleted();
            break;
      }
   }

   /** @param 海藻模式 */

   public void updateAlgae() {
      switch (currentAlgaeMode) {

         //======== 1. 巡邏模式 ========
         case PATROL_ALGAE:
            //Action
            clawSubsystem.patrolAlgaeMode();

            //Break condition
            if (statusManager.hasAlgae()) {
               currentAlgaeMode = AlgaeMode.LAUNCH_ALGAE;
            }
            break;

         //======== 2. 發射模式 ========
         case LAUNCH_ALGAE:
            //Action
            clawSubsystem.algaeReadyMode();

            //Break condition
            if (statusManager.isAlgaeLaunched()) {
               currentAlgaeMode = AlgaeMode.COMPLETED;
            }
            break;
            
         case COMPLETED:
            statusManager.algaeCompleted();
            break;
      }
   }
   
  // =========== ^_^ 參數區 ^_^ ============

   public Boolean isCoralRunning() {
      // 如果 currentCoralMode 不等於 COMPLETED，則代表珊瑚模式正在運行
      return currentCoralMode != CoralMode.COMPLETED;
   }
  
   public Boolean isAlgaeRunning() {
      // 如果 currentAlgaeMode 不等於 COMPLETED，則代表海藻模式正在運行
      return currentAlgaeMode != AlgaeMode.COMPLETED;
   }
}
