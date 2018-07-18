package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LaunchClimber extends CommandGroup {
	private Climber c;
	private OI io = OI.getInstance();
	
	public LaunchClimber(Climber c)
	{
		this.requires(c);
		this.c = c;
	}

	@Override
	protected void execute() {
		c.boing(true);
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
