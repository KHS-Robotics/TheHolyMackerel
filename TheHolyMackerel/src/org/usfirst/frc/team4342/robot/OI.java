package org.usfirst.frc.team4342.robot;

import org.usfirst.frc.team4342.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;

public class OI
{
private static OI instance;
	
	/**
	 * Gets the instance of the Operator Interface
	 * @return the instance of the Operator Interface
	 */
	public static OI getInstance() {
		if(instance == null)
			instance = new OI();
		
		return instance;
	}
	//tank drive
	private Spark t1= new Spark(0);
	private Spark t2= new Spark(0);
	private Encoder e1 = new Encoder(0,0);
	private Encoder e2 = new Encoder(0,0);
	public TankDrive t = new TankDrive(t1,t2,e1,e2);
	
	public Joystick j0 = new Joystick(0);
	public Joystick j1 = new Joystick(0);
}