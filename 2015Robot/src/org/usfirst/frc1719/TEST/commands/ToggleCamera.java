package org.usfirst.frc1719.TEST.commands;

import org.usfirst.frc1719.TEST.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
//toggles the camera through preset positions to allow rapid intake of surroundings
public class ToggleCamera extends Command {
	//makes the program terminable
	private boolean done = false;
	//list of preset positions, currently 9 points in a 3x3 square
	private double[][] presets = new double[][] {{0.0D, 1.0D}, {0.5D, 1.0D}, {1.0D, 1.0D},
			{0.0D, 0.5D}, {0.5D, 0.5D}, {1.0D, 0.5D}, {0.0D, 0.0D}, {0.5D, 0.0D},
			{1.0D, 0.0D}};
	//keeps track of the currently faced preset
	private int i = 0;
	
    public ToggleCamera() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//increments i and resets i if it exceeds the number of presets
    	if(++i >= presets.length) i = 0;
    	//rotates camera to preset i
    	Robot.cameraMount.setXServoRaw(presets[i][0]);
    	Robot.cameraMount.setYServoRaw(presets[i][1]);
    	//terminates function
    	done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(done) {
    		done = false;
    		return true;
    	} else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
