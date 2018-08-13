package org.usfirst.frc.team4342.robot.commands.arm;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.commands.CommandBase;
import org.usfirst.frc.team4342.robot.subsystems.Arm;


public class ArmControlWithJoystick extends CommandBase
{
	private Arm arm;
	private OI oi = OI.getInstance();
	private static final double DEADBAND = 0.1;
	
	public ArmControlWithJoystick(Arm arm)
	{
		this.requires(arm);
		this.arm = arm;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		double output = -oi.ArmJoystick.getY();
		
		if(Math.abs(output) > DEADBAND) 
		{
			arm.set(output / 2);	//change speed LEO WAS CHANGING THIS NUMBER
		} 
		else 
		{
			arm.stop();
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		arm.stop();
	}

}
