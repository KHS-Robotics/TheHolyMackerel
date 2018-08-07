package org.usfirst.frc.team4342.robot.commands.intake;

import org.usfirst.frc.team4342.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Starts the intake to pick up a cube
 */
public class StartIntake extends InstantCommand 
{
	private Intake accum;
	
	/**
	 * Starts the intake
	 * @param accum the intake
	 */
	public StartIntake(Intake accum)
	{
		this.requires(accum);
		this.accum = accum;
	}
	
	@Override
	protected void initialize()
	{
		accum.enable();
	}

}
