package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Accumulator;

/**
 * Sets the height of the elevator to place a cube
 * on the switch
 */
public class ElevateToSwitch extends CommandBase {
	private Accumulator rotation;

	/**
	 * Sets the rotation of the arm to place a cube
	 * on the switch
	 */
	public ElevateToSwitch(Accumulator rotation) {
	this.requires(rotation);
		this.rotation = rotation;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		rotation.rotate(45);
		
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
