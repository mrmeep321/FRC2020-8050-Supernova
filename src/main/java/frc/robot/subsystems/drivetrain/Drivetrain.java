package frc.robot.subsystems.drivetrain;

import java.util.Vector;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.misc.Constants;
import frc.robot.misc.QuickMod;
import frc.robot.subsystems.EnhancedSubsystem;
import frc.robot.subsystems.drivetrain.DriveMotor.MotorDirection;

public class Drivetrain extends EnhancedSubsystem {
    private Vector<SpeedController> lMot;
    private Vector<SpeedController> rMot;
    private Joystick controller;

    /*
    * Constructs a new drivetrain using the configuration array.
    * Config array - {String motorType (Left, Right), SpeedController motor}
    *
    *
    */
    public Drivetrain(Joystick controller, DriveMotor... motConf) {
        super(true);

        lMot = new Vector<>();
        rMot = new Vector<>();
        this.controller = controller;

        for(DriveMotor i : motConf) {
            DriveMotor.MotorDirection dir = i.getDir();
            if(dir.equals(MotorDirection.LEFT)) lMot.add(i.getMotor()); else if(dir.equals(MotorDirection.RIGHT)) rMot.add(i.getMotor());
        }
    }

    public void motorsOff() {
        for(SpeedController i : lMot) {
            i.set(0);
        }
        
        for(SpeedController i : rMot) {
            i.set(0);
        }
    }

    @Override
    public void loop() {
        double mod = QuickMod.speedMod;
        double g;
        for(SpeedController i : lMot) {
            g = -controller.getRawAxis(QuickMod.drivetrainController[0])+controller.getRawAxis(QuickMod.drivetrainController[1]);
            i.set(((g > 1) ? 1 : (g < -1) ? -1 : g)/mod);
         }

        for(SpeedController i : rMot) {
            g = controller.getRawAxis(QuickMod.drivetrainController[0])+controller.getRawAxis(QuickMod.drivetrainController[1]);
            i.set(((g > 1) ? 1 : (g < -1) ? -1 : g)/mod);
        }
    }

    @Override
    public void postEnd() {
        motorsOff();
    }
}