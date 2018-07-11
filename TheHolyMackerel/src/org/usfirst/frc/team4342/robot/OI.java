package org.usfirst.frc.team4342.robot;

import org.usfirst.frc.team4342.robot.subsystems.Accumulator;
import org.usfirst.frc.team4342.robot.subsystems.Climber;
import org.usfirst.frc.team4342.robot.subsystems.TankDrive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
	public final Spark t1= new Spark(0);
	public final Spark t2= new Spark(0);
	private Encoder e1 = new Encoder(0,0);
	private Encoder e2 = new Encoder(0,0);
	private Victor v0 = new Victor(0);
	private Victor v1 = new Victor(0);
	private Victor v2 = new Victor(0);
	private Solenoid s0 = new Solenoid(0);
	private Solenoid s1 = new Solenoid(0);
	
	public AHRS NavX = new AHRS(RobotMap.NAVX_PORT, RobotMap.NAVX_UPDATE_RATE_HZ);
	
	public final TankDrive drive = new TankDrive(t1,t2,NavX, e1,e2);
	public Accumulator acc = new Accumulator(v0, v1, s0);
	public Climber c = new Climber(v2, s1);
	
	
	public Joystick j0 = new Joystick(0);
	public Joystick j1 = new Joystick(1);
	public Joystick j2 = new Joystick(2);
	
	public JoystickButton jb0 = new JoystickButton(j2, 1);
	public JoystickButton jb1 = new JoystickButton(j2, 2);
	public JoystickButton jb2 = new JoystickButton(j2, 3);
	public JoystickButton jb3 = new JoystickButton(j2, 6);
	public JoystickButton jb4 = new JoystickButton(j2, 5);
}