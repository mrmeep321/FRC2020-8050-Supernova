package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.control.Robot;

public class DrivetrainDefaultCommand extends CommandBase {
    private Drivetrain dt;

    public DrivetrainDefaultCommand(Drivetrain dt) {
        this.dt = dt;

        this.addRequirements(Robot.getDrivetrain());
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        dt.loopTick();        
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}