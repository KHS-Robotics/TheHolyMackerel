package org.usfirst.frc.team4342.robot.subsystems;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class TankDrive extends SubsystemBase
{
	private Spark driveR, driveL;
	private Encoder encR, encL;
	
	public TankDrive(Spark driveR, Spark driveL, Encoder encR, Encoder encL)
	{
		this.driveR = driveR;
		this.driveL = driveL;
		this.encR = encR;
		this.encL = encL;
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
	
	@Override
	protected void initDefaultCommand()
	{
		initDefaultCommand();
		OI io = new OI();
		this.setDefaultCommand(new DriveWithJoysticks(io.j0, io.j1, io.t));
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
}
