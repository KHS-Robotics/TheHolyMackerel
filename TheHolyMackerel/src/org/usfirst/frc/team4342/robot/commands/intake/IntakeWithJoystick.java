package org.usfirst.frc.team4342.robot.commands.intake;

import org.usfirst.frc.team4342.robot.commands.CommandBase;
import org.usfirst.frc.team4342.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Joystick;

public class IntakeWithJoystick extends CommandBase {
	private static final double DEADBAND = 0.05;

	private Intake intake;
	private Joystick joystick;
	
	public IntakeWithJoystick(Intake intake, Joystick joystick) {
		this.intake = intake; 
		this.joystick = joystick;
		this.requires(intake);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		double input = joystick.getY();
		input = Math.abs(input) > DEADBAND ? input : 0;
		intake.set(input);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		intake.stop();
	}

}
