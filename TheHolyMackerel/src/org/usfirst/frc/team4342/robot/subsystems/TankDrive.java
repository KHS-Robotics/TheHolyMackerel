package org.usfirst.frc.team4342.robot.subsystems;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.commands.DriveWithJoysticks;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class TankDrive extends DriveTrainBase
{
	private Spark driveR, driveL;
	private Encoder encR, encL;
	private AHRS navx;
	
	public TankDrive(Spark driveR, Spark driveL, AHRS navx, Encoder encR, Encoder encL)
	{
		super(navx);
		
		this.driveR = driveR;
		this.driveL = driveL;
		this.encR = encR;
		this.encL = encL;
		this.navx = navx;
	}
	
	public void drive(double left, double right)
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
	
	public void setNeutralMode(NeutralMode mode)
	{
		driveR.free();
		driveL.free();
	}
	
	@Override
	public void resetNavX() 
	{
		super.resetNavX();
	}
	
	@Override
	protected void initDefaultCommand()
	{
		initDefaultCommand();
		OI io = new OI();
		this.setDefaultCommand(new DriveWithJoysticks(io.j0, io.j1, io.drive));
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goStraight(double direction, double yaw) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double remainingDistance(double distance, double[] distances) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getAllDistances() {
		// TODO Auto-generated method stub
		return null;
	}
}
