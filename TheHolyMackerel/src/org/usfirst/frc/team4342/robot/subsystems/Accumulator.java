package org.usfirst.frc.team4342.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class Accumulator extends SubsystemBase 
{
	private Victor left, right, rotate;
	private Solenoid piston;
	private DigitalInput limitLeft, limitRight;
	
	public Accumulator(Victor left, Victor right, Solenoid piston, DigitalInput limitLeft, DigitalInput limitRight)
	{
		this.left = left;
		this.right = right;
		this.piston = piston;
		this.limitLeft = limitLeft;
		this.limitRight = limitRight;
	}
	
	public void blow(double power)
	{
		this.left.set(-power);
		this.right.set(power);
	}
	
	public void grab(boolean b)
	{
		piston.set(b);
	}
	
	public void rotate(double y) 
	{
		if(!limitLeft.get() && !limitRight.get())
			rotate.set(y);
	}
	
	public void suck()
	{
		left.set(1);
		right.set(-1);
	}
	
	@Override
	public void stop() 
	{
		left.set(0);
		right.set(0);
		piston.set(false);
	}
	
	
}
