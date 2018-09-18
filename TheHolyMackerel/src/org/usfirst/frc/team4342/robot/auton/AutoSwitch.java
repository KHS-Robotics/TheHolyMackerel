package org.usfirst.frc.team4342.robot.auton;

import org.usfirst.frc.team4342.robot.commands.arm.PointToSwitchFront;
import org.usfirst.frc.team4342.robot.commands.drive.DriveGoToAngle;
import org.usfirst.frc.team4342.robot.commands.drive.DriveStraight;
import org.usfirst.frc.team4342.robot.commands.intake.SetSqueezer;
import org.usfirst.frc.team4342.robot.logging.Logger;
import org.usfirst.frc.team4342.robot.subsystems.Intake;
import org.usfirst.frc.team4342.robot.subsystems.Arm;
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
	public AutoSwitch(StartPosition position, TankDrive drive, Intake i, Arm arm) 
	{
		super(position);
		
		if(position == StartPosition.LEFT)
		{
			if(this.isSwitchLeft())
			{
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PANEL_ALIGN_DISTANCE));
				this.addParallel(new PointToSwitchFront(arm));
				this.addSequential(new DriveGoToAngle(drive, 90));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_SWITCH_DISTANCE));
				this.addSequential(new SetSqueezer(i, false));	
			}
			else
			{
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PAST_SWITCH_DISTANCE));
				this.addSequential(new DriveGoToAngle(drive, 90));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PAST_SWITCH_ALIGN_DISTANCE));
				this.addParallel(new PointToSwitchFront(arm));
				this.addSequential(new DriveGoToAngle(drive,180));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_MOVE_TO_SWITCH_DISTANCE));
				this.addSequential(new SetSqueezer(i, false));
			}
				
		}
		else if(position == StartPosition.RIGHT)
		{
			if(this.isSwitchRight())
			{
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PANEL_ALIGN_DISTANCE));
				this.addSequential(new PointToSwitchFront(arm));
				this.addSequential(new DriveGoToAngle(drive, -90));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_SWITCH_DISTANCE));
				this.addSequential(new SetSqueezer(i, false));
			}
			else
			{
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PAST_SWITCH_DISTANCE));
				this.addSequential(new DriveGoToAngle(drive, -90));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_PAST_SWITCH_ALIGN_DISTANCE));
				this.addParallel(new PointToSwitchFront(arm));
				this.addSequential(new DriveGoToAngle(drive, 180));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_RIGHT_MOVE_TO_SWITCH_DISTANCE));
				this.addSequential(new SetSqueezer(i, false));
			}
		}
		else if(position == StartPosition.CENTER)
		{
			if(this.isSwitchRight())
			{
			
			this.addSequential(new DriveStraight(drive, 0.5, DIST_FROM_WALL));
			this.addSequential(new DriveGoToAngle(drive, 45));
			this.addParallel(new PointToSwitchFront(arm));
			this.addSequential(new DriveStraight(drive, 0.5, X_DIST_RIGHT_HYP));
			this.addSequential(new DriveGoToAngle(drive, 0));
			this.addSequential(new DriveStraight(drive, 0.5, RIGHT_DIST_REMAINING_TO_SWITCH));
			this.addSequential(new SetSqueezer(i, false));
			}
			else
			{
				this.addSequential(new DriveStraight(drive, 0.5, DIST_FROM_WALL));
				this.addSequential(new DriveGoToAngle(drive, 45));
				this.addParallel(new PointToSwitchFront(arm));
				this.addSequential(new DriveStraight(drive, 0.5, X_DIST_LEFT_HYP));
				this.addSequential(new DriveGoToAngle(drive, 0));
				this.addSequential(new DriveStraight(drive, 0.5, LEFT_DIST_REMAINING_TO_SWITCH));
				this.addSequential(new SetSqueezer(i, false));
			}
		}
		else
		{
			Logger.warning("No Position for Switch Auto! Crossing Baseline...");
			this.addSequential(new AutoBaseline(position, drive));
		}
	}
}
