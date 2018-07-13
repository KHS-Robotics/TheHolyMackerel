package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Accumulator;

/**
 * Command to release a cube in autonomous. This command
 * will enable the release for 2 seconds.
 */
public class ReleaseCube extends CommandBase 
{
	private Accumulator intake;
	
	/**
	 * Command to release a cube in autonomous. This command
	 * will enable the release for 2 seconds.
	 * @param intake the intake
	 */
	public ReleaseCube(Accumulator intake)
	{
		super(2);
		
		this.intake = intake;
		this.requires(intake);
	}

	@Override
	protected void initialize() {
		intake.grab(false);
		intake.blow(1.0);
		
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		intake.stop();
		intake.grab(true);
	}

}