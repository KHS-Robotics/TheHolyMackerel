package org.usfirst.frc.team4342.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot
{
	public void robotInit()
	{
		OI.getInstance();
	}
	
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}
	
}