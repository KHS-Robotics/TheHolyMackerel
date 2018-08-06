package org.usfirst.frc.team4342.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class Accumulator extends SubsystemBase 
{
	private Victor motor;
	private Solenoid squeezer;
	
	private boolean intaking, releasing;
	
	public Accumulator(Victor motor, Solenoid squeezer)
	{
		this.motor = motor;
		this.squeezer = squeezer;
	}
	
	public void intake() {
		if(intaking) 
			return;
		
		intaking = true;
		releasing = false;
		motor.set(0.67);
	}
	
	public void release() {
		if(releasing) 
			return;
		
		releasing = true;
		intaking = false;
		motor.set(-0.5);
	}
	
	public void setSqueezer(boolean squeezing) {
		if(isSqueezing() == squeezing)
			return;
		
		squeezer.set(squeezing);
	}
	
	public boolean isSqueezing() {
		return squeezer.get();
	}
	
	@Override
	public void stop() 
	{
		motor.set(0);
	}
	
	
}
