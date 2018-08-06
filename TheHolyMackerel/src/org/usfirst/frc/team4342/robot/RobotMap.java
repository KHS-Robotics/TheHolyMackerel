package org.usfirst.frc.team4342.robot;

import edu.wpi.first.wpilibj.I2C.Port;

public class RobotMap {
	// Joysticks
	public static final int LEFT_DRIVE_JOYSTICK = 0;
	public static final int RIGHT_DRIVE_JOYSTICK = 1;
	public static final int OPERATOR_JOYSTICK = 2;
	
	// NavX
	public static final Port NAVX_PORT = Port.kMXP;
	public static final byte NAVX_UPDATE_RATE_HZ = (byte) 50;
	
	// Motors
	public static final int DRIVE_LEFT = 0;
	public static final int DRIVE_RIGHT = 1;
	public static final int INTAKE = 2;
	public static final int ARM = 3;
	public static final int CLIMBER = 4;
	
	// DigitalInputs
	public static final int FRONT_LS = 0;
	public static final int REAR_LS = 1;
	
	// Encoders
	public static final int DRIVE_LEFT_ENC_A = 2;
	public static final int DRIVE_LEFT_ENC_B = 3;
	public static final int DRIVE_RIGHT_ENC_A = 4;
	public static final int DRIVE_RIGHT_ENC_B = 5;
	public static final int ARM_ENC_A = 6;
	public static final int ARM_ENC_B = 7;
	
	// Pneumatics
	public static final int SQUEEZER = 0;
	public static final int SHOOTER = 1;
	
	
}