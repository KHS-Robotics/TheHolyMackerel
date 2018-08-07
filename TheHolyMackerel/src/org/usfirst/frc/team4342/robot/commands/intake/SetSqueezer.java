package org.usfirst.frc.team4342.robot.commands.intake;

import org.usfirst.frc.team4342.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetSqueezer extends InstantCommand {

	private Intake intake;
	private boolean squeezing;
	
	public SetSqueezer(Intake intake, boolean squeezing) {
		requires(intake);
		this.intake = intake;
		this.squeezing = squeezing;
	}
	
	@Override
	public void initialize() {
		intake.setSqueezer(squeezing);
	}
}
