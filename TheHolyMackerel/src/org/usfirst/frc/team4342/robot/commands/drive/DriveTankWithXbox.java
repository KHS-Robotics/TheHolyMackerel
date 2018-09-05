package org.usfirst.frc.team4342.robot.commands.drive;

import org.usfirst.frc.team4342.robot.commands.CommandBase;
import org.usfirst.frc.team4342.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Command to use the tank drive with two joysticks
 */
public class DriveTankWithXbox extends CommandBase {
	private static final double JOYSTICK_DEADZONE = 0.05;
	// [0,1], 0 for no sensitivity control (linear) and 
	// 1 for full sensitivity control (cubic)
	private static final double SENSITIVITY = 0.15;

	private boolean idle;
	
	private XboxController controller;
	private TankDrive drive;
	
	/*
	 * @param drive the <code>TankDrive</code> subsystem to output to
	 * @see org.usfirst.frc.team4342.robot.subsystems.TankDrive
	 */
	public DriveTankWithXbox(XboxController controller, TankDrive drive)
	{
		this.requires(drive);
		
		this.controller = controller;
		this.drive = drive;
	}
	
	/**
	 * The main logic of this command to actually drive the robot.
	 */
	@Override
	protected void execute()
	{
		final double LEFT_TRIGGER = -controller.getTriggerAxis(Hand.kLeft);
		final double RIGHT_TRIGGER = controller.getTriggerAxis(Hand.kRight);
		final double X_INPUT = controller.getX(Hand.kLeft);
		final double Y_INPUT = LEFT_TRIGGER + RIGHT_TRIGGER;
		
		double left = Y_INPUT + X_INPUT;
		double right = Y_INPUT - X_INPUT;

		if(Math.abs(left) > JOYSTICK_DEADZONE || Math.abs(right) > JOYSTICK_DEADZONE)
		{	
			drive.set(adjust(left), adjust(right));
			idle = false;
		} 
		else if(!idle)
		{
			drive.stop();
			idle = true;
		}
	}
	
	/**
	 * Disables the drive PID and then zeros the outputs
	 */
	@Override
	protected void end()
	{
		drive.stop();
	}
	
	@Override
	protected boolean isFinished()
	{
		return false;
	}
	
	/**
	 * Internal function to adjust output for sensitivity control.
	 * https://www.chiefdelphi.com/forums/showthread.php?p=921992
	 * @param input the desired input before adjustment from -1.0 to 1.0
	 * @return the adjusted output
	 */
	private static double adjust(double input)
	{
		if(input > 1)
			input = 1;
		else if(input < -1)
			input = -1;
		
		return SENSITIVITY*Math.pow(input, 3) + input*(1 - SENSITIVITY);
	}
	
	/** {@inheritDoc} */
	@Override
	protected void initialize() {}
}
