package org.usfirst.frc.team4342.robot.commands.intake;

import org.usfirst.frc.team4342.robot.commands.CommandBase;
import org.usfirst.frc.team4342.robot.subsystems.Intake;

/**
 * Starts the intake to release a cube
 */
public class StartRelease extends CommandBase 
{
	private Intake intake;
	
	/**
	 * Starts the intake to release a cube
	 * @param intake the intake
	 */
	public StartRelease(Intake intake)
	{
		this.requires(intake);
		this.intake = intake;
	}
	
	@Override
	public void initialize()
	{
		intake.release();
	}
	
	@Override
	public boolean isFinished()
	{
		return false;
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected void end() {
		intake.stop();
	}
}
