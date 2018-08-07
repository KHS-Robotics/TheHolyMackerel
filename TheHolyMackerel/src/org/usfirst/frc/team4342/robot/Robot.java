package org.usfirst.frc.team4342.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4342.robot.auton.AutoBaseline;
//import org.usfirst.frc.team4342.robot.auton.AutoSwitch;
import org.usfirst.frc.team4342.robot.auton.AutonomousRoutine;
import org.usfirst.frc.team4342.robot.auton.Priority;
import org.usfirst.frc.team4342.robot.auton.StartPosition;
import org.usfirst.frc.team4342.robot.logging.DemonDashboard;
import org.usfirst.frc.team4342.robot.logging.Logger;
import org.usfirst.frc.team4342.robot.logging.PDPLogger;

/**
 * Main Robot Class
 * @author FRC Team 4342
 */
public class Robot extends TimedRobot {
	private SendableChooser<StartPosition> startPositionChooser;
	private SendableChooser<Priority> priorityChooser;
	private AutonomousRoutine autonomousRoutine;
	
	/**
	 * Robot-wide initialization code
	 */
	@Override
	public void robotInit() {
		Logger.info("Bootstrapping TheHolyMackerel...");
		
		OI.getInstance();
		DemonDashboard.start();
		PDPLogger.start();
		
		Logger.info("Initializing autonomous choosers...");
		startPositionChooser = new SendableChooser<StartPosition>();
		startPositionChooser.addDefault("Left", StartPosition.LEFT);
		startPositionChooser.addObject("Center", StartPosition.CENTER);
		startPositionChooser.addObject("Right", StartPosition.RIGHT);
		SmartDashboard.putData("Start Position Chooser", startPositionChooser);
		
		priorityChooser = new SendableChooser<Priority>();
		priorityChooser.addDefault("Baseline", Priority.BASELINE);
		priorityChooser.addObject("Switch", Priority.SWITCH);
		SmartDashboard.putData("Priority Chooser", priorityChooser);
		
		SmartDashboard.putNumber("E-P" , 0.0);
		SmartDashboard.putNumber("E-I" , 0.0);
		SmartDashboard.putNumber("E-D" , 0.0);
		
		SmartDashboard.putNumber("D-P" , 0.0);
		SmartDashboard.putNumber("D-I" , 0.0);
		SmartDashboard.putNumber("D-D" , 0.0);
		
		Logger.info("Starting USB Camera on dev0...");
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setFPS(20);
		camera.setResolution(320, 200);
		
		Logger.info("Finished bootstrapping TheHolyMackerel.");
	}
	
	/**
	 * Initialization code for teleop (operator control) mode
	 */
	@Override
	public void teleopInit() {
		Logger.info("Entering teleop...");
		stopAutonomousRoutine();
	}

	/**
	 * Periodic code for teleop (operator control) mode
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * Initialization code for autonomous mode 
	 */
	@Override
	public void autonomousInit() {
		Logger.info("Entering autonomous...");
		stopAutonomousRoutine();

		
		final OI oi = OI.getInstance();

		String routine;
		final StartPosition position = startPositionChooser.getSelected();
		switch(priorityChooser.getSelected()) {
			case BASELINE:
				routine = "AutoBaseline";
				autonomousRoutine = new AutoBaseline(position, oi.Drive);
			break;
			
			case SWITCH:
				routine = "AutoSwitch";
				//autonomousRoutine = new AutoSwitch(position, oi.Drive, oi.Accum);
			break;
			
			
			default:
				routine = null;
				Logger.warning("Auto priority could not be determined! Crossing auto line!");
				autonomousRoutine = new AutoBaseline(position, oi.Drive);
		}

		if(routine != null)
			Logger.info("Selected \"" + routine + "\" auto routine for " + position.toString().toLowerCase());
		
		startAutonomousRoutine();
	}
	
	/**
	 * Periodic code for autonomous mode
	 */
	@Override
	public void autonomousPeriodic() {
		if(autonomousRoutine != null)
			Scheduler.getInstance().run();
	}
	
	/**
	 * Initialization code for disabled mode
	 */
	@Override
	public void disabledInit() {
		Logger.info("Entering disabled...");
		stopAutonomousRoutine();
		Scheduler.getInstance().run();
	}
	
	private int temp;
	@Override
	public void disabledPeriodic() {
		// Temporary for tuning PID values
//		if(temp % 50 == 0) {
//			double EP = SmartDashboard.getNumber("E-P", 0.0);
//			double EI = SmartDashboard.getNumber("E-I", 0.0);
//			double ED = SmartDashboard.getNumber("E-D", 0.0);
//			OI.getInstance().Elevator.setPID(EP, EI, ED);
//			
//			double DP = SmartDashboard.getNumber("D-P", 0.0);
//			double DI = SmartDashboard.getNumber("D-I", 0.0);
//			double DD = SmartDashboard.getNumber("D-D", 0.0);
//			OI.getInstance().Drive.setPID(DP, DI, DD);
//
//			temp = 0;
//		}
//
//		temp++;
	}
	
	/**
	 * Initialization code for test mode 
	 */
	@Override
	public void testInit() {
		Logger.info("Entering test...");
		stopAutonomousRoutine();
		Scheduler.getInstance().run();
		
		
	}
	
	/**
	 * Starts the autonomous routine
	 */
	private void startAutonomousRoutine() {
		if(autonomousRoutine != null && !autonomousRoutine.isRunning()) {
			Logger.info("Starting autonomous routine...");
			autonomousRoutine.start();
		}
	}
	
	/**
	 * Stops the autonomous routine
	 */
	private void stopAutonomousRoutine() {
		if(autonomousRoutine != null) {
			Logger.info("Stopping autonomous routine...");
			autonomousRoutine.cancel();
		}
	}
}
