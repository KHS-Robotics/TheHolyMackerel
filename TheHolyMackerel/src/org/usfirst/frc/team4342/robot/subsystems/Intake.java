package org.usfirst.frc.team4342.robot.subsystems;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.commands.intake.IntakeWithJoystick;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;

public class Intake extends SubsystemBase 
{
	private Victor motor;
	private DoubleSolenoid squeezer;
	
	private boolean intaking, releasing;
	
	public Intake(Victor motor, DoubleSolenoid squeezer)
	{
		this.motor = motor;
		this.squeezer = squeezer;
	}
	
	@Override
	protected void initDefaultCommand()
	{
		OI oi = OI.getInstance();
		this.setDefaultCommand(new IntakeWithJoystick(oi.Accum, oi.IntakeJoystick));
	}
	
	public void enable() {
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
	
	public void set(double output) {
		motor.set(output);
	}
	
	public void setSqueezer(boolean squeezing) {
		if(isSqueezing() == squeezing)
			return;
		
		squeezer.set(DoubleSolenoid.Value.kForward);
	}
	
	public boolean isSqueezing() {
		return squeezer.get() == DoubleSolenoid.Value.kForward;
	}
	
	@Override
	public void stop() 
	{
		motor.set(0);
	}
	
	
}
