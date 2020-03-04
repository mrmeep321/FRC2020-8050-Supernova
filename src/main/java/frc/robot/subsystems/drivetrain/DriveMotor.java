package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.SpeedController;

public class DriveMotor {
    private SpeedController motor;
    private MotorDirection dir;
    
    public DriveMotor(SpeedController motor, MotorDirection dir) {
        this.motor = motor;
        this.dir = dir;
    }

    public SpeedController getMotor() {
        return motor;
    }

    public MotorDirection getDir() {
        return dir;
    }

    public enum MotorDirection {
        LEFT, RIGHT
    }
}