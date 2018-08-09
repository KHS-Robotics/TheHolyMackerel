package org.usfirst.frc.team4342.robot.subsystems;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.commands.drive.DriveTankWithJoysticks;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class TankDrive extends DriveTrainBase
{
	private Spark driveR, driveL;
	private Encoder encR, encL;
	private double direction;
	
	public TankDrive(Spark driveR, Spark driveL, AHRS navx, Encoder encR, Encoder encL)
	{
		super(navx);
		
		this.driveR = driveR;
		this.driveL = driveL;
		this.encR = encR;
		this.encL = encL;
	}
	
	public void set(double left, double right)
	{
		driveR.set(right);
		driveL.set(left);
	}
	
	public void resetEncoders()
	{
		encR.reset();
		encL.reset();
	}
	
	public double getLeftDistance()
	{
		return encL.getDistance();
	}
	
	public double getRightDistance()
	{
		return encR.getDistance();
	}
	
	@Override
	public void resetNavX() 
	{
		super.resetNavX();
	}
	
	@Override
	protected void initDefaultCommand()
	{
		OI oi = OI.getInstance();
		this.setDefaultCommand(new DriveTankWithJoysticks(oi.LeftJoystick, oi.RightJoystick, oi.Drive));
	}

	@Override
	public void stop() {
		driveR.set(0);
		driveL.set(0);
	}

	@Override
	public void pidWrite(double output) {
		double left = direction + output;
		double right = direction - output;	
		driveL.set(left);
		driveR.set(right);
	}

	@Override
	public void goStraight(double direction, double yaw) {
		this.setHeading(yaw);
		this.direction = direction;
		enablePID();
	}

	@Override
	public double remainingDistance(double distance, double[] distances) {
		final double initialRight = distances[0];
		final double initialLeft = distances[1];

		final double CURRENT_RIGHT_VAL = Math.abs(getRightDistance());
		final double CURRENT_LEFT_VAL = Math.abs(getLeftDistance());
		final double DELTA_RIGHT = Math.abs(CURRENT_RIGHT_VAL - initialRight);
		final double DELTA_LEFT = Math.abs(CURRENT_LEFT_VAL - initialLeft);
		
		final double AVERAGE = (DELTA_RIGHT + DELTA_LEFT) / 2;
		
		final double REMAINING = distance - AVERAGE;
		
		return REMAINING;
	}

	@Override
	public double[] getAllDistances() {
		return new double[] {
				Math.abs(getRightDistance()),
				Math.abs(getLeftDistance())
			};
	}
}
