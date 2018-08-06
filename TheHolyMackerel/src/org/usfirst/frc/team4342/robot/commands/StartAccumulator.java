package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Accumulator;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Starts the intake to pick up a cube
 */
public class StartAccumulator extends InstantCommand 
{
	private Accumulator accum;
	
	/**
	 * Starts the intake
	 * @param accum the intake
	 */
	public StartAccumulator(Accumulator accum)
	{
		this.requires(accum);
		this.accum = accum;
	}
	
	@Override
	protected void initialize()
	{
		accum.intake();
	}

}
