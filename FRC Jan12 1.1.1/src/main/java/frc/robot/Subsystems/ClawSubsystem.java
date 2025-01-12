// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Encapsulations.Claw.EncapsulatedClawTurner;
import frc.robot.Encapsulations.Claw.EncapsulatedFinger;
import frc.robot.Extensions.GammaSubsystem;

public class ClawSubsystem extends SubsystemBase implements GammaSubsystem {

  // =========== Intro. ============

  /**
   * @param ClawSubsystem 夾爪子系統，負責控制接珊瑚、吸珊瑚、吸海藻和發射海藻。
   * @param finger        是夾爪的手指。
   * @param clawTurner    是控制夾爪的旋轉軸。
   */

  private final EncapsulatedFinger finger;
  private final EncapsulatedClawTurner clawTurner;

  /** @param Boolean 下面的布林值要給StatusManager用。*/

  private Boolean isAlgaeCaptured = false;
  private Boolean isCoralCaptured = false;
  private Boolean isAlgaeLaunched = false;

  // =========== 程式開始 ============

  public ClawSubsystem() {
    finger = new EncapsulatedFinger();
    clawTurner = new EncapsulatedClawTurner();
    resetSubsystem();
  }

  // =========== 0. 初始設定 ============
  // =========== GammaSubsystem ============

  @Override
  public void initializeSubsystem() {
    isAlgaeCaptured = false;
    isCoralCaptured = false;
    isAlgaeLaunched = false;
  }

  @Override
  public void resetSubsystem() {
    isAlgaeCaptured = false;
    isCoralCaptured = false;
    isAlgaeLaunched = false;
  }

  public void initClawMode() {
    finger.stop();   // 手指不動
    clawTurner.moveUp(); // 爪子放上面，才不會超界
  } 

  // =========== 1. 巡航模式 ============

  /** @param isAlgar/CoralCaptured 模式設定了。等抓到物件後，切到下一個模式 */

  public void patrolAlgaeMode() {
    clawTurner.moveDown(); // 爪子下來
    finger.outMode();      // 手指吸球
    if (algaeDetected()) {  // TODO Sensor偵測到
      isAlgaeCaptured = true;
    }
  }
  
  public void pickCoralMode() {
    clawTurner.moveDown(); // 爪子下來
    finger.inMode();       // 手指吸珊瑚
    if (coralDetected()) {  // TODO Sensor偵測到
      isCoralCaptured = true;
    }
  }

  public void catchCoralMode() {
    clawTurner.moveUp(); // 爪子上來
    finger.inMode();     // 手指吸珊瑚
    if (coralDetected()) {  // TODO Sensor偵測到
      isCoralCaptured = true;
    }
  }

  // =========== 2. 等發射模式 ============

  /** @param isAlgaeCaptured 抓到球了。等發射後，切到下一個模式 */

  public void algaeReadyMode() {
    finger.stop();        // 手指停止
    clawTurner.moveDown();    // 爪子下來
  }

  // =========== 3. 發射模式 ============

  /** @param isAlgaeLaunched 發射了。回到待機模式 */

  public void launchAlgaeMode() {
    clawTurner.moveDown();    // 爪子下來
    finger.outMode();         // 手指發射球
    isAlgaeLaunched = true;  // 設定為已發射
  }

  // =========== ^_^ 參數區 ^_^ ============

  public Boolean coralCaptured() {
    return isCoralCaptured;  // 本地變成public
  }

  public Boolean algaeCaptured() {
    return isAlgaeCaptured;  // 本地變成public
  }

  public Boolean algaeLaunched() {
    if (isAlgaeCaptured) {
      return isAlgaeLaunched;   // 本地變成public
    }
    return false;
  }
  
  private Boolean algaeDetected() {
    // TODO: Replace with actual sensor detection logic
    return true;
  }

  private Boolean coralDetected() {
      return finger.coralDetected();
  }
}