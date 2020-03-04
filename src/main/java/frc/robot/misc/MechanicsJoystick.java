package frc.robot.misc;

import edu.wpi.first.wpilibj.Joystick;

public class MechanicsJoystick extends Joystick {
    private JoystickMode mode;
    
    public MechanicsJoystick(int port) {
        super(port);

        mode = QuickMod.mechanicsJoystickMode;
    }

    public boolean isManual() {
        return (mode == JoystickMode.MANUAL);
    }

    public enum JoystickMode {
        MANUAL, AUTOMATIC
    }
}