package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.misc.MechanicsJoystick;
import frc.robot.subsystems.EnhancedSubsystem;

public class Shooter extends EnhancedSubsystem {
    private TalonSRX shooterL, shooterR, tiltUp, tiltDown, beltMotor;
    private MechanicsJoystick controller;

    public Shooter(TalonSRX tiltUp, TalonSRX tiltDown, TalonSRX shooterL, TalonSRX shooterR, TalonSRX beltMotor, MechanicsJoystick controller) {
        super(true);

        this.shooterL = shooterL;
        this.shooterR = shooterR;
        this.tiltUp = tiltUp;
        this.tiltDown = tiltDown;
        this.beltMotor = beltMotor;
        this.controller = controller;
    }

    @Override
    public void loop() {
        if(controller.isManual()) {
            tiltUp.set(ControlMode.Position, 4096 * controller.getRawAxis(3));
            tiltDown.set(ControlMode.Position, -4096 * controller.getRawAxis(3));
            shooterL.set(ControlMode.PercentOutput, (controller.getRawButton(1) == true) ? 1 : (controller.getRawAxis(1) > 0) ? 0 : controller.getRawAxis(1));
            shooterR.set(ControlMode.PercentOutput, -1*((controller.getRawButton(1) == true) ? 1 : (controller.getRawAxis(1) > 0) ? 0 : controller.getRawAxis(1)));
            beltMotor.set(ControlMode.PercentOutput, (controller.getPOV(0) == 1 || controller.getRawButton(1) == true) ? .25 : (controller.getPOV(180) == 1) ? -.25 : 0);
        }
    }

}