package org.usfirst.frc.team4342.robot.commands.arm;

import org.usfirst.frc.team4342.robot.subsystems.Arm;

/**
 * Sets the height of the elevator to place a cube on the scale when
 * the opponent has ownership
 */
public class PickupCubeFront extends MoveArmToSetpoint {
	private static final double GROUND_HEIGHT = 130;

	/**
	 * Sets the height of the elevator to place a cube on the scale when
	 * the opponent has ownership
	 * @param elevator the elevator
	 */
	public PickupCubeFront(Arm arm) {
		super(arm, GROUND_HEIGHT);
	}
	
	@Override
	protected boolean isFinished() {
		return super.isFinished() || arm.isFullyForward();
	}
}
