package org.usfirst.frc.team4342.robot;

import org.usfirst.frc.team4342.robot.commands.StartAccumulator;
import org.usfirst.frc.team4342.robot.commands.StartClimber;
import org.usfirst.frc.team4342.robot.commands.StartRelease;
import org.usfirst.frc.team4342.robot.commands.StopSubsystem;
import org.usfirst.frc.team4342.robot.logging.Logger;
import org.usfirst.frc.team4342.robot.subsystems.Accumulator;
import org.usfirst.frc.team4342.robot.subsystems.Arm;
import org.usfirst.frc.team4342.robot.subsystems.Climber;
import org.usfirst.frc.team4342.robot.subsystems.TankDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class OI
{
	private static OI instance;
	public PowerDistributionPanel PDP;
	
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
	
	private Solenoid squeezer;
	private Solenoid shooter;
	
	private DigitalInput limitFront;
	private DigitalInput limitBack;
	
	public AHRS NavX;
	
	public TankDrive Drive;
	public Accumulator Accum;
	public Arm Arm;
	public Climber Climber;
	
	// Joysticks
	public final Joystick LeftJoystick = new Joystick(0);
	public final Joystick RightJoystick = new Joystick(1);
	public final Joystick OperatorJoystick = new Joystick(2);
	
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
			squeezer = new Solenoid(RobotMap.SQUEEZER);
			Accum = new Accumulator(IntakeMotor, squeezer);

			// Switch to enable the intake for a cube
			JoystickButton intakeSwitch = new JoystickButton(OperatorJoystick, ButtonMap.Operator.INTAKE);
			intakeSwitch.whenPressed(new StartAccumulator(Accum));
			intakeSwitch.whenReleased(new StopSubsystem(Accum));
						
			// Switch to enable reverse intake to release a cube
			JoystickButton releaseSwitch = new JoystickButton(OperatorJoystick, ButtonMap.Operator.RELEASE);
			releaseSwitch.whenPressed(new StartRelease(Accum));
			releaseSwitch.whenReleased(new StopSubsystem(Accum));
			
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
			
			JoystickButton climbWithShooter = new JoystickButton(OperatorJoystick, ButtonMap.Operator.CLIMB_WITH_SHOOTER);
			climbWithShooter.whenPressed(new StartClimber(Climber));
			climbWithShooter.whenReleased(new StopSubsystem(Climber));
			
		} catch(Exception ex) {
			Logger.error("Failed to initialize Climber!", ex);
		}	
	}
}