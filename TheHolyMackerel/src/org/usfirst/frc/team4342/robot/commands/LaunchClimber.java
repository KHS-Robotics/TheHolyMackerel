package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class LaunchClimber extends InstantCommand {
	private Climber c;
	
	public LaunchClimber(Climber c)
	{
		this.requires(c);
		this.c = c;
	}

	@Override
	protected void execute() {
		c.boing();
	}
}
