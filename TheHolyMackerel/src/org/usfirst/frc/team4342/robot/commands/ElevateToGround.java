package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.subsystems.Accumulator;

/**
 * Sets the height of the elevator to place a cube
 * on the switch
 */
public class ElevateToGround extends CommandBase {
	/**
	 * Sets the rotation of the arm to place a cube
	 * on the switch
	 */
	public ElevateToGround(Accumulator rotation) {
		rotation.rotate(0);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
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
