package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Accumulator;
import org.usfirst.frc.team4342.robot.subsystems.Climber;
import org.usfirst.frc.team4342.robot.subsystems.TankDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class DriveWithJoysticks extends CommandBase{
	private Joystick j0, j1, j2;
	private Button b0, b1, b2, b3, b4;
	private TankDrive t;
	private Accumulator a;
	private Climber c;
	
	public DriveWithJoysticks(Joystick j0, Joystick j1, Joystick j2, TankDrive t, Accumulator a) {
		this.j0 = j0;
		this.j1 = j1;
		this.j2 = j2;
		this.t = t;
		this.a = a;
		b0 = new JoystickButton(j2, 1);
		b1 = new JoystickButton(j2, 2);
		b2 = new JoystickButton(j2, 3);
		b3 = new JoystickButton(j2, 6);
		b4 = new JoystickButton(j2, 5);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		t.drive(j0.getY(), j1.getY());
		a.rotate(j2.getY());
		a.set(b0.get(), -j2.getRawAxis(3));
		a.suck(b1.get());
		a.open(b2.get());
		c.boing(b3.get());
		c.winch(b4.get());
		
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
