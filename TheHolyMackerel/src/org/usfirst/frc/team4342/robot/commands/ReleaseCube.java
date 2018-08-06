package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Accumulator;

/**
 * Command to release a cube in autonomous. This command
 * will enable the release for 2 seconds.
 */
public class ReleaseCube extends CommandBase 
{
	private Accumulator accum;
	
	/**
	 * Command to release a cube in autonomous. This command
	 * will enable the release for 2 seconds.
	 * @param accum the accum
	 */
	public ReleaseCube(Accumulator accum)
	{
		super(2);
		
		this.accum = accum;
		this.requires(accum);
	}

	@Override
	protected void initialize() {
		accum.release();
		
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		accum.stop();
	}

}