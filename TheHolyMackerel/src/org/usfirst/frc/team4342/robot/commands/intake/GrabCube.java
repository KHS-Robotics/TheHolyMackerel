package org.usfirst.frc.team4342.robot.commands.intake;

import org.usfirst.frc.team4342.robot.commands.CommandBase;
import org.usfirst.frc.team4342.robot.subsystems.Intake;

/**
 * Command to release a cube in autonomous. This command
 * will enable the release for 2 seconds.
 */
public class GrabCube extends CommandBase 
{
	private Intake accum;
	
	/**
	 * Command to release a cube in autonomous. This command
	 * will enable the release for 2 seconds.
	 * @param accum the accum
	 */
	public GrabCube(Intake accum)
	{
		super(2);
		
		this.accum = accum;
		this.requires(accum);
	}

	@Override
	protected void initialize() {
		accum.setSqueezer(false);
		accum.enable();
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		accum.setSqueezer(true);
		accum.stop();
	}

}