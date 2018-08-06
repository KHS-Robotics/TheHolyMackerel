package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartClimber extends CommandGroup 
{
	public StartClimber(Climber c)
	{
		this.addSequential(new LaunchClimber(c));
		this.addSequential(new Wait(2.0));
		this.addSequential(new WinchClimber(c));
	}
}
