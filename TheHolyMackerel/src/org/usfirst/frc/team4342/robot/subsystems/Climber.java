package org.usfirst.frc.team4342.robot.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;

public class Climber extends SubsystemBase{
	private Jaguar j0;
	private Solenoid s;
	
	public Climber(Jaguar j0, Solenoid s)
	{
		this.j0 = j0;
		this.s = s;
	}
	
	public void boing(boolean b)
	{
		s.set(b);
	}
	
	public void winch(boolean b)
	{
		j0.set(1);
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
