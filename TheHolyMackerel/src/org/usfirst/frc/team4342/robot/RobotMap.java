package org.usfirst.frc.team4342.robot;

import edu.wpi.first.wpilibj.I2C.Port;

public class RobotMap {
	// Joysticks
	public static final int XBOX_CONTROLLER = 0;
	public static final int OPERATOR_JOYSTICK = 1;
	
	// NavX
	public static final Port NAVX_PORT = Port.kMXP;
	public static final byte NAVX_UPDATE_RATE_HZ = (byte) 50;
	
	// Motors
	public static final int DRIVE_LEFT = 1;
	public static final int DRIVE_RIGHT = 0;
	public static final int INTAKE = 3;
	public static final int ARM = 2;
	public static final int CLIMBER = 4;
	
	// DigitalInputs
	public static final int FRONT_LS = 1;
	public static final int REAR_LS = 0;
	
	// Encoders
	public static final int DRIVE_LEFT_ENC_A = 4;
	public static final int DRIVE_LEFT_ENC_B = 5;
	public static final int DRIVE_RIGHT_ENC_A = 3;
	public static final int DRIVE_RIGHT_ENC_B = 2;
	public static final int ARM_ENC_A = 6;
	public static final int ARM_ENC_B = 7;
	
	// Pneumatics
	public static final int SQUEEZER_A = 0;
	public static final int SQUEEZER_B = 1;
	public static final int SHOOTER = 2;
	
	// RGB
	public static final int RBG_POWER = 4;
	public static final int RED = 6;
	public static final int GREEN = 5;
	public static final int BLUE = 7;
}