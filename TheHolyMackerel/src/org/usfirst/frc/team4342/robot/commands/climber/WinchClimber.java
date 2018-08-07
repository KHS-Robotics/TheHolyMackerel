package org.usfirst.frc.team4342.robot.commands.climber;

import org.usfirst.frc.team4342.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class WinchClimber extends InstantCommand {
	private Climber c;
	
	public WinchClimber(Climber c)
	{
		this.requires(c);
		this.c = c;
	}

	@Override
	protected void execute() {
		c.enable();
	}
}
