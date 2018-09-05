package org.usfirst.frc.team4342.robot.subsystems;

import org.usfirst.frc.team4342.robot.ButtonMap;
import org.usfirst.frc.team4342.robot.Constants;
import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.commands.arm.ArmControlWithJoystick;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class Arm extends PIDSubsystem {
	private double divider = 1.0;
	
	private Spark motor;
	private Encoder encoder;
	private DigitalInput frontLS, rearLS;
	private boolean override;
	
	public Arm(Spark motor, Encoder encoder, DigitalInput frontLS, DigitalInput rearLS) {
		super(Constants.ArmPID.P, Constants.ArmPID.I, Constants.ArmPID.D);
		setInputRange(-130, 130);
		setOutputRange(-1, 1);
		setAbsoluteTolerance(0.25);
		disable();
		
		this.motor = motor;
		this.encoder = encoder;
		this.frontLS = frontLS;
		this.rearLS = rearLS;
	}
	
	public void setDivider(double divider)
	{
		this.divider = divider;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initSendable(SendableBuilder builder)
	{
		// DO NOT TOUCH ME
	}

	public void setOverride(boolean flag)
	{
		override = flag;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disable() 
	{
		if(this.getPIDController().isEnabled())
			super.disable();
	}

	/**
	 * Gets the encoder distance
	 * @return the encoder distance
	 */
	@Override
	protected double returnPIDInput() 
	{
		return encoder.getDistance();
	}

	/**
	 * Sets the calculated output to the motor
	 */
	@Override
	protected void usePIDOutput(double output) 
	{
		set(output);
	}
	
	@Override
	protected void initDefaultCommand() {
		OI oi = OI.getInstance();
		this.setDefaultCommand(new ArmControlWithJoystick(oi.Arm, new JoystickButton(oi.OperatorJoystick, ButtonMap.Operator.OVERRIDE)));
	}
	
	public boolean isFullyForward() {
		return frontLS.get();
	}
	
	public boolean isFullyBack() {
		return rearLS.get();
	}
	
	public void set(double output) {
	
		if(!override) {
			if (output > 0 && isFullyForward()) {
				output = output > 0.1 ? 0.1 : output;
			}
		
			if (output < 0 && isFullyBack()) {
				output = output < -0.1 ? -0.1 : output;
			}
		}
		
		motor.set(output / divider);
	}

	public void stop() {
		disable();
		motor.set(0);
	}
}
