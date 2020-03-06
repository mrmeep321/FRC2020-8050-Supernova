package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DrivetrainDefaultCommand extends CommandBase {
    private Drivetrain dt;

    public DrivetrainDefaultCommand(Drivetrain dt) {
        this.dt = dt;
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