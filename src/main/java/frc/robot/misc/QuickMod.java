package frc.robot.misc;

import static frc.robot.misc.Constants.*;
import static frc.robot.misc.MechanicsJoystick.JoystickMode.*;

import frc.robot.misc.MechanicsJoystick.JoystickMode;

public class QuickMod {
    /*
    *   Upload Instructions:
    *   ---------------------
    *   Connect to Wifi 8050 (No password)
    *   Press F1 - Build (Wait for finish) - Press F1 - Deploy (Wait for finish)
    *   Driver station should give a green light on robot and robot code if it started correctly.
    *
    *   You will need to re-upload every time you change something
    */

    //Max speed of the drivetrain is divided by this number
    public static final int speedMod = 2;

    //Set this line equal to either xboxAxisMap or flightstickAxisMap (Don't forget caps)
    //This will determine the controller used to make the drivetrain move
    //Make sure that the controller you selected is the one that was plugged in first (Shows as controller 0 in driver station).
    public static final int[] drivetrainController = xboxAxisMap;

    //Set this line equal to either MANUAL or AUTOMATIC (Don't forget caps) to change the default mode of the mechanics flightstick.
    public static final JoystickMode mechanicsJoystickMode = MANUAL;

    //CAN IDs for all CAN motors on the robot
    //Open up phoenix config while connected to robot to change CAN IDs
    public static final int intake = 7;
    public static final int leftTubeWheel = 3;
    public static final int rightTubeWheel = 2;
    public static final int frontTilt = 10;
    public static final int climberSetup = 8;
    public static final int climberPull = 0;
    public static final int belt = 1;
    public static final int backTilt = 11;

    //PWM ports for all PWM motors on the robot
    public static final int leftMotors = 0;
    public static final int rightMotors = 1;
}