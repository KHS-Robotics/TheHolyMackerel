package org.usfirst.frc.team4342.robot.commands.arm;

import org.usfirst.frc.team4342.robot.commands.CommandBase;
import org.usfirst.frc.team4342.robot.subsystems.Arm;

/**
 * Elevate Command to set the elevator to a specified height
 */
public class MoveArmToSetpoint extends CommandBase {
	protected final Arm arm;
	private double rot;

	/**
	 * Elevate Command to set the elevator to a specified height
	 * @param elevator the elevator
	 * @param height the desired height of the elevator
	 */
	public MoveArmToSetpoint(Arm arm, double rot) {
		super(2.5);
		
		this.arm = arm;
		this.rot = rot;
		
		this.requires(arm);
	}
	
	@Override
	protected void initialize() {
		arm.setSetpoint(rot);
		arm.enable();
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return arm.onTarget() || this.isTimedOut();
	}

	@Override
	protected void end() {
	}
}
