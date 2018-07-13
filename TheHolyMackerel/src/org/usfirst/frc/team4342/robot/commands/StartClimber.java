package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.subsystems.Climber;

public class StartClimber {
	private Climber c;
	private OI io = OI.getInstance();
	
	public StartClimber(Climber c)
	{
		this.c = c;
	}
	
	public void initialize()
	{
		if(io.jb3.get())
		{
			c.boing(true);
			try 
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
			}
			c.winch(true);
		}
		else
		{
			c.winch(false);
		}
	}
}
