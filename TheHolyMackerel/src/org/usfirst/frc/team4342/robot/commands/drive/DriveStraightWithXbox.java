package org.usfirst.frc.team4342.robot.commands.drive;

import org.usfirst.frc.team4342.robot.commands.CommandBase;
import org.usfirst.frc.team4342.robot.subsystems.DriveTrainBase;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Command to drive straight with a xboxcontroller
 */
public class DriveStraightWithXbox extends CommandBase {
	private XboxController xboxcontroller;
	private DriveTrainBase drive;
	
	private double yaw;
	
	/**
	 * Command to drive straight with a xboxcontroller
	 * @param drive the drive
	 */
	public DriveStraightWithXbox(XboxController xboxcontroller, DriveTrainBase drive) {
		this.xboxcontroller = xboxcontroller;
		this.drive = drive;
		
		this.requires(drive);
	}

	@Override
	protected void initialize() {
		yaw = drive.getHeading();
	}

	@Override
	protected void execute() {
		final double LEFT_TRIGGER = -xboxcontroller.getTriggerAxis(Hand.kLeft);
		final double RIGHT_TRIGGER = xboxcontroller.getTriggerAxis(Hand.kRight);
		
		final double Y_INPUT = LEFT_TRIGGER + RIGHT_TRIGGER;
		
		drive.goStraight(Y_INPUT, yaw);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {}
}
