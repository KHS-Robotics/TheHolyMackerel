package org.usfirst.frc.team4342.robot.commands.arm;

import org.usfirst.frc.team4342.robot.OI;
import org.usfirst.frc.team4342.robot.commands.CommandBase;
import org.usfirst.frc.team4342.robot.subsystems.Arm;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class ArmControlWithJoystick extends CommandBase
{
private static final double DEADBAND = 0.03;
	
	private boolean idle, initializedIdle;
	
	private JoystickButton override;
	private Arm arm;
	
	public ArmControlWithJoystick (Arm arm, JoystickButton override) {
		this.override = override;
		this.arm = arm;
		
		this.requires(arm);
	}

	@Override
	protected void initialize() {
		arm.setDivider(1.5);
		
		if(!arm.isFullyBack() && !arm.isFullyForward()) {
			arm.setSetpoint(arm.getPosition());
			arm.enable();
		}
	}

	@Override
	protected void execute() {
		final double INPUT = -OI.getInstance().OperatorJoystick.getY();
		final boolean OVERRIDE = override.get(); 
		arm.setOverride(OVERRIDE);
		idle = checkJoystickDeadband(INPUT);

		// Emergency override in case sensors malfunction
		if(!idle && OVERRIDE) {
			arm.disable();
			arm.set(INPUT);
			return;
		}
		else if(OVERRIDE) {
			arm.stop();
			return;
		}
		
		if(idle && !initializedIdle) {
			arm.setSetpoint(arm.getPosition()); // hold current height
			arm.enable();
			initializedIdle = true;
		} 
		else if(!idle) {
			arm.disable();
			arm.set(INPUT);
			initializedIdle = false;
		}
	}

	@Override
	protected void end() {
		arm.stop();
		arm.setDivider(1);
	}
	
	private static boolean checkJoystickDeadband(double a) {
		return Math.abs(a) < DEADBAND;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
