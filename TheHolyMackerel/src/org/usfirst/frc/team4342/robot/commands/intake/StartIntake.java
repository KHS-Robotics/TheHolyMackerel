package org.usfirst.frc.team4342.robot.commands.intake;

import org.usfirst.frc.team4342.robot.commands.CommandBase;
import org.usfirst.frc.team4342.robot.subsystems.Intake;

/**
 * Starts the intake to pick up a cube
 */
public class StartIntake extends CommandBase 
{
	private Intake intake;
	
	/**
	 * Starts the intake
	 * @param intake the intake
	 */
	public StartIntake(Intake intake)
	{
		this.requires(intake);
		this.intake = intake;
	}
	
	@Override
	protected void initialize()
	{
		intake.enable();
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
