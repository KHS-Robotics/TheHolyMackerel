package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartClimber extends CommandGroup {
	private Climber c;
	public StartClimber(Climber c)
	{
		this.requires(c);
		this.c = c;
	}

	@Override
	protected void execute() {
		this.addSequential(new LaunchClimber(c));
		this.addSequential(new WinchClimber(c), 100.0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
}
