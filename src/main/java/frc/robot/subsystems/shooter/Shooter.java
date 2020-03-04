package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.subsystems.EnhancedSubsystem;

public class Shooter extends EnhancedSubsystem {
    private TalonSRX beltMotor;

    public Shooter(TalonSRX beltMotor) {
        super(true);

        this.beltMotor = beltMotor;
    }

    @Override
    public void loop() {
        
    }

}