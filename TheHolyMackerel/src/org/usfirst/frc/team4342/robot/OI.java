package org.usfirst.frc.team4342.robot;

import org.usfirst.frc.team4342.robot.commands.StopSubsystem;
import org.usfirst.frc.team4342.robot.commands.climber.LaunchClimber;
import org.usfirst.frc.team4342.robot.commands.climber.WinchClimber;
import org.usfirst.frc.team4342.robot.commands.intake.SetSqueezer;
import org.usfirst.frc.team4342.robot.commands.intake.StartIntake;
import org.usfirst.frc.team4342.robot.commands.intake.StartRelease;
import org.usfirst.frc.team4342.robot.logging.Logger;
import org.usfirst.frc.team4342.robot.subsystems.Intake;
import org.usfirst.frc.team4342.robot.subsystems.Arm;
import org.usfirst.frc.team4342.robot.subsystems.Climber;
import org.usfirst.frc.team4342.robot.subsystems.TankDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class OI
{
	private static OI instance;
	public final PowerDistributionPanel PDP;
	
	private OI() {
		PDP = new PowerDistributionPanel();
		LiveWindow.disableTelemetry(PDP);
		
		initDrive();
		initIntake();
		initArm();
		initClimber();
	}
	/**
	 * Gets the instance of the Operator Interface
	 * @return the instance of the Operator Interface
	 */
	public static OI getInstance() {
		if(instance == null)
			instance = new OI();
		
		return instance;
	}
	// Drive
	private Spark LeftDriveMotor;
	private Spark RightDriveMotor;
	
	private Encoder LeftDriveEncoder;
	private Encoder RightDriveEncoder;
	private Encoder ArmEncoder;
	
	private Victor IntakeMotor;
	private Victor ArmMotor;
	private Victor ClimberMotor;
	
	private DoubleSolenoid squeezer;
	private Solenoid shooter;
	
	private DigitalInput limitFront;
	private DigitalInput limitBack;
	
	public AHRS NavX;
	
	public TankDrive Drive;
	public Intake Accum;
	public Arm Arm;
	public Climber Climber;
	
	// Joysticks
	public final Joystick LeftJoystick = new Joystick(RobotMap.LEFT_DRIVE_JOYSTICK);
	public final Joystick RightJoystick = new Joystick(RobotMap.RIGHT_DRIVE_JOYSTICK);
	public final Joystick ArmJoystick = new Joystick(RobotMap.ARM_JOYSTICK);
	public final Joystick IntakeJoystick = new Joystick(RobotMap.INTAKE_JOYSTICK);
	
	private void initDrive() {
		try {
			Logger.info("Initializing Drive...");
			
			NavX = new AHRS(RobotMap.NAVX_PORT, RobotMap.NAVX_UPDATE_RATE_HZ);
			
			LeftDriveMotor = new Spark(RobotMap.DRIVE_LEFT);
			RightDriveMotor = new Spark(RobotMap.DRIVE_RIGHT);
			LeftDriveEncoder = new Encoder(RobotMap.DRIVE_LEFT_ENC_A, RobotMap.DRIVE_LEFT_ENC_B);
			RightDriveEncoder = new Encoder(RobotMap.DRIVE_RIGHT_ENC_A, RobotMap.DRIVE_RIGHT_ENC_B);
			
			Drive = new TankDrive(LeftDriveMotor, RightDriveMotor, NavX, LeftDriveEncoder, RightDriveEncoder);
			
		} catch(Exception ex) {
			Logger.error("Failed to initialize Drive!", ex);
		}
	}
	
	private void initArm() {
		try {
			Logger.info("Initializing Arm...");
			
			ArmMotor = new Victor(RobotMap.ARM);
			ArmEncoder = new Encoder(RobotMap.ARM_ENC_A, RobotMap.ARM_ENC_B);
			limitFront = new DigitalInput(RobotMap.FRONT_LS);
			limitBack = new DigitalInput(RobotMap.REAR_LS);
			
			Arm = new Arm(ArmMotor, ArmEncoder, limitFront, limitBack);
			
		} catch(Exception ex) {
			Logger.error("Failed to initialize Arm!", ex);
		}
	}
	
	private void initIntake() {
		try {
			Logger.info("Initializing Intake...");
			
			// Intake
			IntakeMotor = new Victor(RobotMap.INTAKE);
			squeezer = new DoubleSolenoid(RobotMap.SQUEEZER_A, RobotMap.SQUEEZER_B);
			Accum = new Intake(IntakeMotor, squeezer);

			// Switch to enable the intake for a cube
			JoystickButton intakeSwitch = new JoystickButton(IntakeJoystick, ButtonMap.Operator.Intake.INTAKE);
			intakeSwitch.whenPressed(new StartIntake(Accum));
			intakeSwitch.whenReleased(new StopSubsystem(Accum));
						
			// Switch to enable reverse intake to release a cube
			JoystickButton releaseSwitch = new JoystickButton(IntakeJoystick, ButtonMap.Operator.Intake.RELEASE);
			releaseSwitch.whenPressed(new StartRelease(Accum));
			releaseSwitch.whenReleased(new StopSubsystem(Accum));
			
			JoystickButton openSqueezer = new JoystickButton(IntakeJoystick, ButtonMap.Operator.Intake.SQUEEZE);
			openSqueezer.whenPressed(new SetSqueezer(Accum, false));
			openSqueezer.whenReleased(new SetSqueezer(Accum, true));
			
		} catch(Exception ex) {
			Logger.error("Failed to initialize Intake!", ex);
		}
	}
	
	private void initClimber() {
		try {
			Logger.info("Initializing Climber...");
			
			ClimberMotor = new Victor(RobotMap.CLIMBER);
			shooter = new Solenoid(RobotMap.SHOOTER);
			
			Climber = new Climber(ClimberMotor, shooter);
			
			JoystickButton launchShooter = new JoystickButton(IntakeJoystick, ButtonMap.Operator.Climber.LAUNCH_SHOOTER);
			launchShooter.whenPressed(new LaunchClimber(Climber));
			
			
			JoystickButton startClimber = new JoystickButton(IntakeJoystick, ButtonMap.Operator.Climber.START_CLIMBER);
			startClimber.whenPressed(new WinchClimber(Climber));
			startClimber.whenReleased(new StopSubsystem(Climber));
			
		} catch(Exception ex) {
			Logger.error("Failed to initialize Climber!", ex);
		}	
	}
}