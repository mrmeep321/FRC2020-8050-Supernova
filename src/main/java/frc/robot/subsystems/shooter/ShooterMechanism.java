package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.misc.QuickMod;

public class ShooterMechanism extends SubsystemBase {
    private VictorSPX intake, leftTubeWheel, rightTubeWheel, frontTilt;
    private TalonSRX climberSetup, climberPull, belt, backTilt;
    private Joystick controller;
    
    public ShooterMechanism() {
        intake = new VictorSPX(QuickMod.intake);
        leftTubeWheel = new VictorSPX(QuickMod.leftTubeWheel);
        rightTubeWheel = new VictorSPX(QuickMod.rightTubeWheel);
        frontTilt = new VictorSPX(QuickMod.frontTilt);

        climberSetup = new TalonSRX(QuickMod.climberSetup);
        climberPull = new TalonSRX(QuickMod.belt);
        belt = new TalonSRX(QuickMod.belt);
        backTilt = new TalonSRX(QuickMod.backTilt);

        this.controller = new Joystick(1);

        this.setDefaultCommand(new ShooterDefaultCommand(this));
    }

    public Joystick getJoystick() {
        return controller;
    }

    public void changeShooterAngle(double angle) {
        //frontTilt.set(ControlMode.Position, 4096*(angle/180));
        frontTilt.set(ControlMode.Position, (4096*(angle/180)));
    }

    public void setShooter(double percent) {
        leftTubeWheel.set(ControlMode.PercentOutput, percent);
        rightTubeWheel.set(ControlMode.PercentOutput, -percent);
    }

    public void setBelt(double percent) {
        belt.set(ControlMode.PercentOutput, percent);
    }

    public void setIntake(double percent) {

    }
}