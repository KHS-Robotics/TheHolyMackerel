package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.subsystems.Climber;

public class StartClimber extends CommandBase {
	private Climber c;
	private OI io = OI.getInstance();
	
	public StartClimber(Climber c)
	{
		this.c = c;
	}

	@Override
	protected void execute() {
		c.boing(true);
		try {
			wait(1000);
		} catch (InterruptedException e) {
		}
	c.winch(true);
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
