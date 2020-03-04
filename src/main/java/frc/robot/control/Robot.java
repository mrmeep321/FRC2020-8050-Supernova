/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.control;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMTalonFX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.misc.MechanicsJoystick;
import frc.robot.subsystems.EnhancedSubsystemCollection;
import frc.robot.subsystems.drivetrain.DriveMotor;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.DriveMotor.MotorDirection;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterBelt;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private SpeedController dtMotorLeftA;
  private SpeedController dtMotorRightA;
  //private ColorSensorV3 color;
  //private I2C.Port colorPort;
  private Drivetrain dt;
  private Shooter shooter;
  private ShooterBelt shooterBelt;
  private EnhancedSubsystemCollection shooters;

  private Joystick jA;
  private MechanicsJoystick jB;

  /*private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private final ColorMatch match = new ColorMatch();*/

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    jA = new Joystick(0);
    jB = new MechanicsJoystick(1);

    dtMotorLeftA = new PWMTalonFX(0);
    SpeedController dtMotorLeftB = new PWMTalonFX(0);
    dtMotorRightA = new PWMTalonFX(1);
    SpeedController dtMotorRightB = new PWMTalonFX(1);

    dt = new Drivetrain(jA, new DriveMotor(dtMotorLeftA, MotorDirection.LEFT), new DriveMotor(dtMotorLeftB, MotorDirection.LEFT), new DriveMotor(dtMotorRightA, MotorDirection.RIGHT), new DriveMotor(dtMotorRightB, MotorDirection.RIGHT));
    shooter = new Shooter(new TalonSRX(1), new TalonSRX(2), jB);
    shooterBelt = new ShooterBelt(new TalonSRX(3), jB);


    //colorPort = I2C.Port.kOnboard;
    //color = new ColorSensorV3(colorPort);

    /*match.addColorMatch(kBlueTarget);
    match.addColorMatch(kGreenTarget);
    match.addColorMatch(kRedTarget);
    match.addColorMatch(kYellowTarget); */
    
    shooters = new EnhancedSubsystemCollection(shooter, shooterBelt);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    dt.start();
    shooters.start();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    /*if(match.matchClosestColor(color.getColor()).color == kRedTarget)
    dtMotorLeftA.set(0.5); else dtMotorLeftA.set(0);*/
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}