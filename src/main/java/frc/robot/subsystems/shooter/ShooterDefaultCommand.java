package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterDefaultCommand extends CommandBase {
    private ShooterMechanism shooter;
    private Joystick controller;

    public ShooterDefaultCommand(ShooterMechanism shooter) {
        this.addRequirements(shooter);
        this.shooter = shooter;

        this.controller = shooter.getJoystick();
    }

    @Override
    public void execute() {
        if(controller.getTrigger()) {
           shooter.setBelt(-.2);
           shooter.setShooter(-1);
        } else if(controller.getRawButton(3)) {
            
        } else{
            shooter.setBelt(0);
            shooter.setShooter(0);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}