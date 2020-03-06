package frc.robot.subsystems.drivetrain;

import java.util.Vector;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMTalonFX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.misc.Constants;
import frc.robot.misc.QuickMod;
import frc.robot.subsystems.EnhancedSubsystem;
import frc.robot.subsystems.drivetrain.DriveMotor.MotorDirection;

public class Drivetrain extends SubsystemBase {
    private Vector<SpeedController> lMot;
    private Vector<SpeedController> rMot;
    private Joystick controller;

    /*
     * Constructs a new drivetrain using the configuration array. Config array -
     * {String motorType (Left, Right), SpeedController motor}
     *
     *
     */
    public Drivetrain() {
        Vector<DriveMotor> motConf = new Vector<>();

        lMot = new Vector<>();
        rMot = new Vector<>();

        motConf.add(new DriveMotor(new PWMTalonFX(0), DriveMotor.MotorDirection.LEFT));
        motConf.add(new DriveMotor(new PWMTalonFX(9), DriveMotor.MotorDirection.RIGHT));

        this.controller = new Joystick(0);

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

    public void drive(double speed) {
        double mod = QuickMod.speedMod;
        double g;

        g = speed/QuickMod.speedMod;

        for(SpeedController i : lMot) {
            i.set(((g > 1) ? 1 : (g < -1) ? -1 : g)/mod);
         }

        for(SpeedController i : rMot) {
            g = -g;
            i.set(((g > 1) ? 1 : (g < -1) ? -1 : g)/mod);
        }
    }

    public void drive(double speedL, double speedR) {
        double mod = QuickMod.speedMod;
        double l, r;

        l = speedL;
        r = speedR;

        for(SpeedController i : lMot) {
            i.set(((l > 1) ? 1 : (l < -1) ? -1 : l)/mod);
         }

        for(SpeedController i : rMot) {
            i.set(((r > 1) ? 1 : (r < -1) ? -1 : r)/mod);
        }
    }

    public void loopTick() {
        drive(-controller.getRawAxis(QuickMod.drivetrainController[0])+controller.getRawAxis(QuickMod.drivetrainController[1]), (controller.getRawAxis(QuickMod.drivetrainController[0])-controller.getRawAxis(QuickMod.drivetrainController[1]))/2);
    }

    public void postEnd() {
        motorsOff();
    }

    public void autonDriveMod(int ms, double mod) {
        try {
            for(SpeedController i : lMot) {
               i.set(1/mod);
             }3
    
            for(SpeedController i : rMot) {
                i.set(-1/mod);
            }
            
            Thread.sleep(ms);

            for(SpeedController i : lMot) {
                i.set(0);
            }

            for(SpeedController i: rMot) {
                i.set(0);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}