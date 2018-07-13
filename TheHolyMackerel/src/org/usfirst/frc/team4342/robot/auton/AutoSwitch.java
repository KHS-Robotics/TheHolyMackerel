package org.usfirst.frc.team4342.robot.auton;

import org.usfirst.frc.team4342.robot.commands.DriveStraight;
import org.usfirst.frc.team4342.robot.commands.DriveTurn;
import org.usfirst.frc.team4342.robot.commands.ElevateToSwitch;
import org.usfirst.frc.team4342.robot.commands.ReleaseCube;
import org.usfirst.frc.team4342.robot.logging.Logger;
import org.usfirst.frc.team4342.robot.subsystems.Accumulator;
import org.usfirst.frc.team4342.robot.subsystems.DriveTrainBase;
import org.usfirst.frc.team4342.robot.subsystems.TankDrive;

/**
 * Auto routine to place a cube on the switch for the
 * specified position
 * @see StartPosition
 */
public class AutoSwitch extends AutonomousRoutine 
{	
	/**
	 * Auto routine to place a cube on the switch for the
	 * specified position
	 * @param position the starting position
	 * @param drive the drive
	 * @param i the intake
	 * @see StartPosition
	 */
	public AutoSwitch(StartPosition position, TankDrive drive, Accumulator i) 
	{
		super(position);
		
		if(position == StartPosition.LEFT)
		{
			if(this.isSwitchLeft())
			{
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PANEL_ALIGN_DISTANCE));
				this.addParallel(new ElevateToSwitch(i));
				this.addSequential(new DriveTurn(drive));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_SWITCH_DISTANCE));
				this.addSequential(new ReleaseCube(i));	
			}
			else
			{
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PAST_SWITCH_DISTANCE));
				this.addSequential(new DriveTurn(drive));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PAST_SWITCH_ALIGN_DISTANCE));
				this.addParallel(new ElevateToSwitch(i));
				this.addSequential(new DriveTurn(drive));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_MOVE_TO_SWITCH_DISTANCE));
				this.addSequential(new ReleaseCube(i));
			}
				
		}
		else if(position == StartPosition.RIGHT)
		{
			if(this.isSwitchRight())
			{
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PANEL_ALIGN_DISTANCE));
				this.addSequential(new ElevateToSwitch(i));
				this.addSequential(new DriveTurn(drive, false));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_SWITCH_DISTANCE));
				this.addSequential(new ReleaseCube(i));
			}
			else
			{
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PAST_SWITCH_DISTANCE));
				this.addSequential(new DriveTurn(drive, false));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PAST_SWITCH_ALIGN_DISTANCE));
				this.addParallel(new ElevateToSwitch(i));
				this.addSequential(new DriveTurn(drive, false));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_MOVE_TO_SWITCH_DISTANCE));
				this.addSequential(new ReleaseCube(i));
			}
		}
		else if(position == StartPosition.CENTER)
		{
			final boolean clockwise = this.isSwitchRight();
			
			this.addSequential(new DriveStraight(drive, 0.5, CENTER_STRAIGHT_DISTANCE));
			this.addSequential(new DriveTurn(drive, clockwise));
			this.addParallel(new ElevateToSwitch(i));
			this.addSequential(new DriveStraight(drive, 0.5, CENTER_PANEL_ALIGN_DISTANCE));
			this.addSequential(new DriveTurn(drive, !clockwise));
			this.addSequential(new DriveStraight(drive, 0.5, CENTER_STRAIGHT_DISTANCE));
			this.addSequential(new ReleaseCube(i));
		}
		else
		{
			Logger.warning("No Position for Switch Auto! Crossing Baseline...");
			this.addSequential(new AutoBaseline(position, drive));
		}
	}
}
