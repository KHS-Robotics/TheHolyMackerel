package org.usfirst.frc.team4342.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;

public class Climber extends SubsystemBase{
	private Spark motor;
	private Solenoid shooter;
	
	public Climber(Spark motor, Solenoid shooter)
	{
		this.motor = motor;
		this.shooter = shooter;
	}
	
	public void boing()
	{
		shooter.set(true);
	}
	
	public void enable()
	{
		motor.set(1);
	}
	
	@Override
	public void stop() {
		motor.set(0);
		
	}

}
