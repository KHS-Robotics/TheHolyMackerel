package org.usfirst.frc.team4342.robot.subsystems;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.commands.ArmControlWithJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class Arm extends SubsystemBase {
	private Victor motor;
	private Encoder encoder;
	private DigitalInput frontLS, rearLS;
	
	public Arm(Victor motor, Encoder encoder, DigitalInput frontLS, DigitalInput rearLS) {
		this.motor = motor;
		this.encoder = encoder;
		this.frontLS = frontLS;
		this.rearLS = rearLS;
	}
	
	@Override
	protected void initDefaultCommand() {
		OI oi = OI.getInstance();
		this.setDefaultCommand(new ArmControlWithJoystick(oi.Arm));
	}
	
	public boolean isFullyForward() {
		return frontLS.get();
	}
	
	public boolean isFullyBack() {
		return rearLS.get();
	}
	
	public void set(double output) {
		motor.set(output);
	}
	
	public double getPosition() {
		return encoder.getDistance();
	}

	@Override
	public void stop() {
		motor.set(0);
	}
}
