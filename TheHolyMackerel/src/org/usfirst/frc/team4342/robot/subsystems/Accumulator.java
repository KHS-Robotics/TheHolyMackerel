package org.usfirst.frc.team4342.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

public class Accumulator extends SubsystemBase 
{
	private VictorSP left, right, rotate;
	private Solenoid piston;
	
	public Accumulator(VictorSP left, VictorSP right, Solenoid piston)
	{
		this.left = left;
		this.right = right;
		this.piston = piston;
	}
	
	public void set(boolean b, double power)
	{
		if(b)
		{
			this.left.set(-power);
			this.right.set(power);
		}
	}
	
	public void open(boolean b)
	{
		piston.set(b);
	}
	
	public void close() {
		piston.set(false); }
	
	public void rotate(double y) 
	{
		rotate.set(y);
	}
	
	public void suck(boolean b)
	{
		left.set(1);
		right.set(-1);
	}
	
	@Override
	public void stop() 
	{
		// TODO Auto-generated method stub
	}
	
	
}
