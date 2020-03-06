package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DrivetrainAutonCommand extends CommandBase {
    private Drivetrain dt;

    public DrivetrainAutonCommand(Drivetrain dt) {
        this.dt = dt;
    }

    @Override
    public void execute() {
        try {
            dt.drive(.25);
            Thread.sleep(2000);
            dt.drive(0);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}