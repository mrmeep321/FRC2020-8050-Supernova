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
            }
    }
}