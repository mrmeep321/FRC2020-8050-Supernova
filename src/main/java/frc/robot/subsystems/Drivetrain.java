package frc.robot.subsystems;

import java.util.Vector;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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
    public Drivetrain(Joystick controller, Object[]... motConf) {
        super(true);

        lMot = new Vector<>();
        rMot = new Vector<>();
        this.controller = controller;

        for(Object[] i : motConf) {
            if(((String)i[0]).equalsIgnoreCase("left")) lMot.add((SpeedController) i[1]); else if(((String)i[0]).equalsIgnoreCase("right")) rMot.add((SpeedController) i[1]);
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
        double g;

        for(SpeedController i : lMot) {
            i.set(-controller.getY()+controller.getX());
         }

        for(SpeedController i : rMot) {
            
            i.set(controller.getY()-controller.getX());
        }
    }

    @Override
    public void postEnd() {
        motorsOff();
    }
}