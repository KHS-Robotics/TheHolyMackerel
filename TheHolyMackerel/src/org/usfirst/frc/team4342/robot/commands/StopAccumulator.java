package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Accumulator;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Stops the intake
 */
public class StopAccumulator extends InstantCommand 
{
	private Accumulator intake;
	
	/**
	 * Stops the intake
	 * @param intake the intake
	 */
	public StopAccumulator(Accumulator intake)
	{
		this.requires(intake);
		this.intake = intake;
	}
	
	@Override
	public void initialize()
	{
		intake.stop();
	}
}
