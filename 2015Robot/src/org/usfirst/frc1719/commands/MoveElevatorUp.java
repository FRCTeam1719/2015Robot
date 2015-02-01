package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorUp extends Command {
	
	Elevator elevator;
	
	boolean hasMovedPastPotPos = false;
	boolean done = false;

    public MoveElevatorUp(int elevatorNum) {
        
    	if (elevatorNum == Elevator.ELEVATOR_FRONT) {
    		elevator = Robot.frontElevator;
    	}
    	else if (elevatorNum == Elevator.ELEVATOR_BACK) {
    		elevator = Robot.backElevator;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//The elevator starts out at the pot pos, so we have to move past it before we go up
    	//normally
    	if (!hasMovedPastPotPos) {
    		//go up without any regard for the potentiometer
    		elevator.moveFreeUp();
    		
    		if (!elevator.atPotPos()) {
    			hasMovedPastPotPos = true;
    		}
    		return;
    	}
    	
    	//We have moved past the pot pos
    	else {
    		
    		elevator.moveUp();
    		
    		if (elevator.atPotPos() ) {
    			done = true;
    			hasMovedPastPotPos = false;
    			return;
    		}
    	}
    	
    	System.out.println("Move type: " + elevator.getMoveType());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (done) {
        	done = false;
        	hasMovedPastPotPos = false;
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
