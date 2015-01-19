package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorUp extends Command {

	private boolean commandIsFinished   = false;
	
	//If the potentiometer is within this many degrees of the target,
	//it will be considered a full rotation
	private int TOLERANCE = 2;
	
	//The command starts out within the tolerance of it quitting
	private boolean hasMovedPastTolerance = false;

    public MoveElevatorUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.elevator1);
    	requires(Robot.elevator2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (!hasMovedPastTolerance) {
    		Robot.elevator1.moveUp();
    	}
    	
    	double elevatorPos = Robot.elevator1.getPosition();
    	
    	//If the wheel is not within the tolerance
    	if ( (Math.floor(elevatorPos) % 360) > TOLERANCE ) {
    		
    		//if hasMovedPastTolerance is false, but the pot is past the tolerance,
    		//Update the variable
    		if (!hasMovedPastTolerance) {
    			hasMovedPastTolerance = true;
    		}
    		
    		Robot.elevator1.moveUp();
    	}
    	//If the robot is within the tolerance, and it moved past the initial tolerance rance,
    	//Then we are done
    	if ( !((Math.floor(elevatorPos) % 360) > TOLERANCE) && hasMovedPastTolerance) {
    		Robot.elevator1.setStill();
    		commandIsFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        if (commandIsFinished) {
        	commandIsFinished = false;
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
