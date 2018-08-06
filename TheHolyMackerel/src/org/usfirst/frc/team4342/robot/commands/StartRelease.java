package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Accumulator;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Starts the intake to release a cube
 */
public class StartRelease extends InstantCommand 
{
	private Accumulator accum;
	
	/**
	 * Starts the intake to release a cube
	 * @param intake the intake
	 */
	public StartRelease(Accumulator accum)
	{
		this.requires(accum);
		this.accum = accum;
	}
	
	@Override
	public void initialize()
	{
		accum.release();
	}
}
