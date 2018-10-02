package org.usfirst.frc.team4342.robot.commands.drive;

import org.usfirst.frc.team4342.robot.commands.CommandBase;
import org.usfirst.frc.team4342.robot.subsystems.DriveTrainBase;

/**
 * Command to drive straight
 */
public class DriveStraightTimer extends CommandBase {
    private double yaw;

    private DriveTrainBase drive;
    private double speed;
    private double timeout;

    /**
     * Command to drive straight
     * @param drive the drive
     * @param speed the speed
     * @param distance the distance
     */
    public DriveStraightTimer(DriveTrainBase drive, double speed, double timeout) {
      super(timeout);
    	this.drive = drive;
        this.speed = speed;
        this.timeout = timeout;
        
        this.requires(drive);
    }

    @Override
    protected void initialize() {
        yaw = drive.getHeading();
    }

    @Override
    protected void execute() {
        drive.goStraight(speed, yaw);
    }

    @Override
    protected void end() {
        drive.stop();
    }

    @Override
    protected boolean isFinished() {
        return this.isTimedOut();
    }
}
