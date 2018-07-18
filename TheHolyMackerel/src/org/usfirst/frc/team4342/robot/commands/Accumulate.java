package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.subsystems.Accumulator;

public class Accumulate extends CommandBase{
	private Accumulator a;
	private OI io = OI.getInstance();
	
	public Accumulate(Accumulator a)
	{
		this.requires(a);
		this.a = a;
	}
	
	public void push()
	{
		if(io.jb0.get())
		{
			a.blow(io.j2.getRawAxis(3));
		}
	}
	
	public void pull() 
	{
			a.suck();
	}
	
	public void grab()
	{
		if(io.jb2.get())
		{
			a.grab(true);
		}
		else
		{
			a.grab(false);
		}
	}
	
	public void rotate()
	{
		a.rotate(io.j2.getY());
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		
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
}
