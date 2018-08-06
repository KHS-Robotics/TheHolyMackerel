package org.usfirst.frc.team4342.robot.commands;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.subsystems.Arm;


public class ArmControlWithJoystick extends CommandBase
{
	private Arm arm;
	private OI io = OI.getInstance();
	private static final double DEADBAND = 0.1;
	
	public ArmControlWithJoystick(Arm arm)
	{
		this.arm = arm;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		double output = io.OperatorJoystick.getY();
		
		if(Math.abs(output) > DEADBAND) 
		{
			if(output < 0 && !arm.isFullyForward()) 
			{
				arm.set(output);
			} 
			else if (output > 0 && !arm.isFullyBack())
			{
				arm.set(output);
			}
			else
			{
				arm.set(0);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

}
