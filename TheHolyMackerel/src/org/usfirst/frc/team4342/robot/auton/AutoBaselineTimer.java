package org.usfirst.frc.team4342.robot.auton;

import org.usfirst.frc.team4342.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team4342.robot.commands.drive.DriveStraightTimer;
import org.usfirst.frc.team4342.robot.subsystems.DriveTrainBase;
import org.usfirst.frc.team4342.robot.subsystems.TankDrive;

/**
 * Auto routine to cross the auto line
 */
public class AutoBaselineTimer extends AutonomousRoutine
{
	protected static final double SECONDS_TO_BASELINE = 5;
	/**
	 * Auto routine to cross the auto line
	 * @param position the starting position
	 * @param drive the drive
	 * @see StartPosition
	 */
	public AutoBaselineTimer(StartPosition position, DriveTrainBase drive) 
	{
		super(position);
		
		this.addSequential(new DriveStraightTimer(drive, 0.5, SECONDS_TO_BASELINE));
	}

	/**
	 * Auto routine to cross the auto line
	 * @param drive the drive
	 */
	public AutoBaselineTimer(DriveTrainBase drive)
	{
		this(StartPosition.CENTER, drive);
	}
}
