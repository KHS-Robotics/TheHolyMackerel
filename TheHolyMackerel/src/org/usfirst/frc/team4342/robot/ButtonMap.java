package org.usfirst.frc.team4342.robot;

/**
 * Class to keep track of the button maps for teleop
 */
public class ButtonMap 
{

	/**
	 * Drive Sticks to control the Tank Drive
	 */
	public static class Driver
	{
		//	public static final int GO_STRAIGHT = XboxButton.;
			public static final int GO_TO_ZERO = XboxButton.kY.value;
			public static final int GO_TO_LEFT = XboxButton.kX.value;
			public static final int GO_TO_RIGHT = XboxButton.kB.value;
			public static final int GO_180 = XboxButton.kA.value;
	}
	

	public static class Operator
	{
		
		public static class Arm {
			
		}
		
		public static class Intake {
			public static final int INTAKE = 3;
			public static final int SQUEEZE = 1;
			public static final int RELEASE = 4;
		}
		
		public static class Climber {
			public static final int LAUNCH_SHOOTER = 12;
			public static final int START_CLIMBER = 11;
		}
		
		
		public static final int RESET = 10;
		public static final int OVERRIDE = 2;
	}
	

	/**
	 * Represents a digital button on an XboxController
	 */
	 enum XboxButton {
		kBumperLeft(5),
		kBumperRight(6),
		kStickLeft(9),
		kStickRight(10),
		kA(1),
		kB(2),
		kX(3),
		kY(4),
		kBack(7),
		kStart(8);

		private int value;

		XboxButton(int value) {
			this.value = value;
		}
	}
}
