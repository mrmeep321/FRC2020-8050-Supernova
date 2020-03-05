package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import frc.robot.misc.MechanicsJoystick;
import frc.robot.subsystems.EnhancedSubsystem;

public class Intake extends EnhancedSubsystem {
    private TalonSRX leftTilt, rightTilt;
    private MechanicsJoystick controller;

    public Intake(TalonSRX leftTilt, TalonSRX rightTilt, MechanicsJoystick controller) {
        super(true);

        this.leftTilt = leftTilt;
        this.rightTilt = rightTilt;
        this.controller = controller;
    }

    @Override
    public void loop() {
        if(controller.isManual()) {
            //leftTilt.set(ControlMode.Velocity, controller.getRawAxis(0));
        }
    }

}