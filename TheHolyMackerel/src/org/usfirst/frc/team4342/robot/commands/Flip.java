package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.subsystems.Accumulator;

import edu.wpi.first.wpilibj.Joystick;

public class Flip extends CommandBase{
	private Accumulator intake;
	private OI io = OI.getInstance();
	
	public Flip(Accumulator intake)
	{
		this.intake = intake;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		intake.rotate(deadband(io.j2));
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}
	
	protected double deadband(Joystick joystick) {
		if(Math.abs(joystick.getY()) < 0.1) {
			return 0;
		}
	    else
	    {
	    	return joystick.getY();
	    }
	}

}
