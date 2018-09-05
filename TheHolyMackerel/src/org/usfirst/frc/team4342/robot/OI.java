package org.usfirst.frc.team4342.robot;

import org.usfirst.frc.team4342.robot.commands.StopSubsystem;
import org.usfirst.frc.team4342.robot.commands.arm.PointToSwitchFront;
import org.usfirst.frc.team4342.robot.commands.climber.LaunchClimber;
import org.usfirst.frc.team4342.robot.commands.climber.WinchClimber;
import org.usfirst.frc.team4342.robot.commands.drive.DriveGoToAngle;
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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI
{
	private static OI instance;
	
	private OI() {
		initDrive();
		initIntake();
		initArm();
		initClimber();
		
		rgbPower = new Solenoid(RobotMap.RBG_POWER);
		red = new Solenoid(RobotMap.RED);
		blue = new Solenoid(RobotMap.BLUE);
		green = new Solenoid(RobotMap.GREEN);
		AwesomeLights.start(rgbPower, red, green, blue);
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
	
	private Spark IntakeMotor;
	private Spark ArmMotor;
	private Spark ClimberMotor;
	
	private DoubleSolenoid squeezer;
	private Solenoid shooter, rgbPower, red, blue, green;
	
	private DigitalInput limitFront;
	private DigitalInput limitBack;
	
	public AHRS NavX;
	
	public TankDrive Drive;
	public Intake Accum;
	public Arm Arm;
	public Climber Climber;
	
	// Joysticks
	public final XboxController DriverController = new XboxController(RobotMap.XBOX_CONTROLLER);
	public final Joystick OperatorJoystick = new Joystick(RobotMap.OPERATOR_JOYSTICK);
	
	private void initDrive() {
		try {
			Logger.info("Initializing Drive...");
			
			NavX = new AHRS(RobotMap.NAVX_PORT, RobotMap.NAVX_UPDATE_RATE_HZ);
			
			LeftDriveMotor = new Spark(RobotMap.DRIVE_LEFT);
			RightDriveMotor = new Spark(RobotMap.DRIVE_RIGHT);
			LeftDriveEncoder = new Encoder(RobotMap.DRIVE_LEFT_ENC_A, RobotMap.DRIVE_LEFT_ENC_B);
			RightDriveEncoder = new Encoder(RobotMap.DRIVE_RIGHT_ENC_A, RobotMap.DRIVE_RIGHT_ENC_B);
			
			Drive = new TankDrive(RightDriveMotor, LeftDriveMotor,  NavX, RightDriveEncoder, LeftDriveEncoder);
			
			JoystickButton driveGoToZero = new JoystickButton(DriverController, ButtonMap.Driver.GO_TO_ZERO);
			driveGoToZero.whenPressed(new DriveGoToAngle(Drive, 0));
			
			JoystickButton driveGoToLeft = new JoystickButton(DriverController, ButtonMap.Driver.GO_TO_LEFT);
			driveGoToLeft.whenPressed(new DriveGoToAngle(Drive, -90));
			
			JoystickButton driveGoToRight = new JoystickButton(DriverController, ButtonMap.Driver.GO_TO_RIGHT);
			driveGoToRight.whenPressed(new DriveGoToAngle(Drive, 90));
			
			JoystickButton driveGoTo180 = new JoystickButton(DriverController, ButtonMap.Driver.GO_180);
			driveGoTo180.whenPressed(new DriveGoToAngle(Drive, 180));
			
		} catch(Exception ex) {
			Logger.error("Failed to initialize Drive!", ex);
		}
	}
	
	private void initArm() {
		try {
			Logger.info("Initializing Arm...");
			
			ArmMotor = new Spark(RobotMap.ARM);
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
			IntakeMotor = new Spark(RobotMap.INTAKE);
			squeezer = new DoubleSolenoid(RobotMap.SQUEEZER_A, RobotMap.SQUEEZER_B);
			Accum = new Intake(IntakeMotor, squeezer);

			// Switch to enable the intake for a cube
			JoystickButton intakeSwitch = new JoystickButton(OperatorJoystick, ButtonMap.Operator.Intake.INTAKE);
			intakeSwitch.whenPressed(new StartIntake(Accum));
			intakeSwitch.whenReleased(new StopSubsystem(Accum));
						
			// Switch to enable reverse intake to release a cube
			JoystickButton releaseSwitch = new JoystickButton(OperatorJoystick, ButtonMap.Operator.Intake.RELEASE);
			releaseSwitch.whenPressed(new StartRelease(Accum));
			releaseSwitch.whenReleased(new StopSubsystem(Accum));
			
			JoystickButton openSqueezer = new JoystickButton(OperatorJoystick, ButtonMap.Operator.Intake.SQUEEZE);
			openSqueezer.whenPressed(new SetSqueezer(Accum, false));
			openSqueezer.whenReleased(new SetSqueezer(Accum, true));
			
		} catch(Exception ex) {
			Logger.error("Failed to initialize Intake!", ex);
		}
	}
	
	private void initClimber() {
		try {
			Logger.info("Initializing Climber...");
			
			ClimberMotor = new Spark(RobotMap.CLIMBER);
			shooter = new Solenoid(RobotMap.SHOOTER);
			
			Climber = new Climber(ClimberMotor, shooter);
			
			JoystickButton launchShooter = new JoystickButton(OperatorJoystick, ButtonMap.Operator.Climber.LAUNCH_SHOOTER);
			launchShooter.whenPressed(new LaunchClimber(Climber));
			
			
			JoystickButton startClimber = new JoystickButton(OperatorJoystick, ButtonMap.Operator.Climber.START_CLIMBER);
			startClimber.whenPressed(new WinchClimber(Climber));
			startClimber.whenReleased(new StopSubsystem(Climber));
			
		} catch(Exception ex) {
			Logger.error("Failed to initialize Climber!", ex);
		}	
	}
}