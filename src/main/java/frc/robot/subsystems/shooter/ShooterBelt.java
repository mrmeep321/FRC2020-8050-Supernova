package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.misc.MechanicsJoystick;
import frc.robot.subsystems.EnhancedSubsystem;

public class ShooterBelt extends EnhancedSubsystem {
    private TalonSRX beltMotor;
    private MechanicsJoystick controller;
    
    public ShooterBelt(TalonSRX beltMotor, MechanicsJoystick controller) {
        super(true);

        this.beltMotor = beltMotor;
        this.controller = controller;
    }

    @Override
    public void loop() {
        if(controller.isManual()) {
            beltMotor.set(ControlMode.PercentOutput, (controller.getPOV(0) == 1) ? .25 : (controller.getPOV(180) == 1 ? -.25 : 0));
        }
    }
}