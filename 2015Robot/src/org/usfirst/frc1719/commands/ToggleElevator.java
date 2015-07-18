package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.OI;
import org.usfirst.frc1719.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleElevator extends Command {

	//Which elevator we are going to use
	int whichElevator;
	
	boolean done = false;
	
    public ToggleElevator() {
    	requires(Robot.currentElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.currentElevator.setStill();
    	if(OI.getMode()==OI.MODE_BACK){
    		Robot.switchElevator(OI.MODE_FRONT);
    	}else{
    		Robot.switchElevator(OI.MODE_BACK);
    	}
    	
    	
    	done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	done = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
