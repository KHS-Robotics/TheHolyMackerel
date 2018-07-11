package org.usfirst.frc.team4342.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

public class Climber extends SubsystemBase{
	private Victor v0;
	private Solenoid s;
	
	public Climber(Victor v0, Solenoid s)
	{
		this.v0 = v0;
		this.s = s;
	}
	
	public void boing(boolean b)
	{
		s.set(b);
	}
	
	public void winch(boolean b)
	{
		v0.set(1);
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
