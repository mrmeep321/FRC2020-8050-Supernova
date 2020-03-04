package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.misc.MechanicsJoystick;
import frc.robot.subsystems.EnhancedSubsystem;

public class Shooter extends EnhancedSubsystem {
    private TalonSRX shooter, angler;
    private MechanicsJoystick controller;

    public Shooter(TalonSRX angler, TalonSRX shooter, MechanicsJoystick controller) {
        super(true);

        this.shooter = shooter;
        this.angler = angler;
        this.controller = controller;
    }

    @Override
    public void loop() {
        if(controller.isManual()) {
            angler.set(ControlMode.Position, 4096 * controller.getRawAxis(3));
            angler.set(ControlMode.Position, -4096 * controller.getRawAxis(3));
            shooter.set(ControlMode.PercentOutput, ((controller.getRawButton(1) == true) ? 1 : 0));
        }
    }

}