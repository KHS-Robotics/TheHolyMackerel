package org.usfirst.frc.team4342.robot.commands.arm;

import org.usfirst.frc.team4342.robot.subsystems.Arm;

/**
 * Sets the height of the elevator to place a cube on the scale when
 * the opponent has ownership
 */
public class PointToSwitchBack extends MoveArmToSetpoint {
	private static final double SWITCH_HEIGHT = 0;

	/**
	 * Sets the height of the elevator to place a cube on the scale when
	 * the opponent has ownership
	 * @param elevator the elevator
	 */
	public PointToSwitchBack(Arm arm) {
		super(arm, -SWITCH_HEIGHT);
	}
}
