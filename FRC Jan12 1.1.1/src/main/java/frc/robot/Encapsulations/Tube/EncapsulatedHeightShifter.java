package frc.robot.Encapsulations.Tube;

import edu.wpi.first.wpilibj.Servo;

public class EncapsulatedHeightShifter {
    public enum Height {
      L4, L3, L2, L1, 
    }

    private final Servo shifter4;
    private final Servo shifter3;
    private final Servo shifter2;
    private final Servo shifter1;

    private Height currentHeight;

    public EncapsulatedHeightShifter() {
        shifter4 = new Servo(0);
        shifter3 = new Servo(1);
        shifter2 = new Servo(2);
        shifter1 = new Servo(3);
        currentHeight = Height.L4; // 初始狀態
    }

    public void setHeight(Height height) {
        switch (height) {
            case L4:
                shifter4.close();
                break;
            case L3:
                shifter3.close();
                break;
            case L2:
                shifter2.close();
                break;
            case L1:
                shifter1.close();
                break;
        }
        currentHeight = height;
    }

    public Height currentTubeHeight() {
        return currentHeight;
    }
}
