package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.Joystick;

public class DriveWithJoysticks extends CommandBase{
	private Joystick j0, j1;
	private TankDrive t;
	
	public DriveWithJoysticks(Joystick j0, Joystick j1, TankDrive t) {
		this.j0 = j0;
		this.j1 = j1;
		this.t = t;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		t.drive(j0.getY(), j1.getY());
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
}
