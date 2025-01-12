package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Encapsulations.Tube.EncapsulatedShooter;
import frc.robot.Extensions.GammaSubsystem;
import frc.robot.Extensions.LevelModes;
import frc.robot.Encapsulations.Tube.EncapsulatedAccelerator;
import frc.robot.Extensions.LevelModes;
import frc.robot.Extensions.GammaSubsystem;

public class TubeSubsystem extends SubsystemBase implements LevelModes, GammaSubsystem {

  // =========== Intro. ============

  /**
   * @param TubeSubsystem 砲管子系統，只負責發射珊瑚。
   * @param accelerator   加速馬達，負責調整L2、L3、L4的速度
   * @param heightShifter 彈簧，負責調高度
   * @param shooter       在底下，負責把珊瑚傳給加速馬達
   */

   private final EncapsulatedAccelerator accelerator;
   private final EncapsulatedShooter shooter;

   /** @param Boolean 這裡的布林值是Local的。*/
   private Boolean isL1 = false;
   private Boolean isL2 = false;
   private Boolean isL3 = false;
   private Boolean isL4 = false;

   /** @param Boolean 下面的布林值要給StatusManager用。*/
   private Boolean isCoralLaunched = false;

   // =========== 程式開始 ============
   public TubeSubsystem(){
      accelerator = new EncapsulatedAccelerator();
      shooter = new EncapsulatedShooter();
   }

   // =========== 0. 初始設定 ============

   @Override
   public void initializeSubsystem(){
      accelerator.stop();
      shooter.stop();
      //shifter手動壓
   }

   @Override
   public void resetSubsystem() {
      isCoralLaunched = false;
      isL1 = false;
      isL2 = false;
      isL3 = false;
      isL4 = false;
   }

   private void resetLevel(){
      isL1 = false;
      isL2 = false;
      isL3 = false;
      isL4 = false;
   }

   public void shiftHeight(){
   }

   // =========== 1. 暖機模式 ============

   public void warmUp(){
      accelerator.l2Mode();
      shooter.stop();
   }

   // =========== 2. 準備發射模式 ============
   // =========== LevelModes ============

  @Override
   public void l1Mode(){
      resetLevel();
      accelerator.l1Mode();
      shooter.stop();
      isL1 = true;
   }

   @Override
   public void l2Mode(){   //跟暖機模式一樣
      resetLevel();
      accelerator.l2Mode();
      shooter.stop();
      isL2 = true;
   }
  
   @Override
   public void l3Mode(){
      resetLevel();
      accelerator.l3Mode();
      shooter.stop();
      isL3 = true;
   }

   @Override
   public void l4Mode(){
      resetLevel();
      accelerator.l4Mode();
      shooter.stop();
      isL4 = true;
   }

  // =========== 3. 發射！ ============

   public void shoot(){
      shooter.shoot();
      if (isL1) {
         accelerator.l1Mode();
      } else if (isL2) {
         accelerator.l2Mode();
      } else if (isL3) {
         accelerator.l3Mode();
      } else if (isL4) {
         accelerator.l4Mode();
      }
      isCoralLaunched = true;
   }

   public Boolean coralLaunched(){
      return isCoralLaunched;
   }
}
