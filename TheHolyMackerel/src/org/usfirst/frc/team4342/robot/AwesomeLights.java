package org.usfirst.frc.team4342.robot;

import org.usfirst.frc.team4342.robot.logging.Logger;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Class to control the RGB Lights
 */
public class AwesomeLights extends Thread implements Runnable {
    private static final int INTERVAL_MS = 500;
    private static boolean running;
    
    private boolean[] on = { true, false, false };
    private Solenoid power, red, green, blue;
    
    private AwesomeLights(Solenoid power, Solenoid red, Solenoid green, Solenoid blue) {
        this.power = power;
        this.red = red; 
        this.green = green;
        this.blue = blue;
    }
    
    /**
     * Starts the Awesome Lights
     */
    public static void start(Solenoid power, Solenoid red, Solenoid green, Solenoid blue) {
        if(running)
            return;
        running = true;
        
        new AwesomeLights(power, red, green, blue).start();
    }
    
    /**
     * Cycles the RGB Lights
     */
    @Override
    public void run() {
        Logger.info("Starting the Awesome Lights!");
        
        power.set(true);
        
        try {
            while(!Thread.interrupted()) {
                cycleLights();
                Thread.sleep(INTERVAL_MS);
            }
        } catch (InterruptedException ex) {
            Logger.error("The Awesome Lights crashed :(", ex);
        }
        
        Logger.info("Awesome Lights shutting down...");
        
        power.set(false);
        
        running = false;
    }
    
    /**
     * Shifts and sets the lights by calling
     * {@link #shiftLights()} then {@link #setLights()}
     */
    private void cycleLights() {
        shiftLights();
        setLights();
    }
    
    /**
     * Sets the lights based on the state of the internal array
     */
    private void setLights() {
        red.set(on[0]);
        green.set(on[1]);
        blue.set(on[2]);
    }
    
    /**
     * Shifts the internal array
     */
    private void shiftLights() {
        boolean temp = on[on.length-1];
        
        for(int i = on.length-1; i > 0; i--) {
        	on[i] = on[i-1];
        }
        
        on[0] = temp;
    }
}
