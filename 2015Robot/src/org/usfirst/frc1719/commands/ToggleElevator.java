package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleElevator extends Command {

	//Which elevator we are going to use
	int whichElevator;
	
	boolean done = false;
	
    public ToggleElevator(int whichElevator) {
        this.whichElevator = whichElevator;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.currentElevator.setStill();
    	Robot.setCurrentElevator(whichElevator);    	
    	done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (done) {
        	done = false;
        	return true;
        }
        else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
