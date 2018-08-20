package org.usfirst.frc.team4342.robot.subsystems;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.commands.intake.IntakeWithJoystick;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

public class Intake extends SubsystemBase 
{
	private Spark motor;
	private DoubleSolenoid squeezer;
	
	private boolean intaking, releasing;
	
	public Intake(Spark intakeMotor, DoubleSolenoid squeezer)
	{
		this.motor = intakeMotor;
		this.squeezer = squeezer;
	}
	
	@Override
	protected void initDefaultCommand()
	{
		super.initDefaultCommand();
	}
	
	public void enable() {
		if(intaking) 
			return;
		
		intaking = true;
		releasing = false;
		set(0.67);
	}
	
	public void release() {
		if(releasing) 
			return;
		
		releasing = true;
		intaking = false;
		set(-0.5);
	}
	
	public void set(double output) {
		motor.set(output);
	}
	
	public void setSqueezer(boolean squeezing) {
		if(isSqueezing() == squeezing)
			return;
		
		squeezer.set(squeezing ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
	}
	
	public boolean isSqueezing() {
		return squeezer.get() == DoubleSolenoid.Value.kForward;
	}
	
	@Override
	public void stop() 
	{
		motor.set(0);
		releasing = false;
		intaking = false;
	}
	
	
}
