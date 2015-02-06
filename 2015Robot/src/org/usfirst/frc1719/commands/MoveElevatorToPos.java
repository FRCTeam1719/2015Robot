package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorToPos extends Command {
	
	//If this is not set in the constructor, then something bad happened, and
	//the command shouldn't do anything
	public static int ERROR_NUM = -1337;
	private int desiredPosition = ERROR_NUM;
	
	Elevator elevator;
	
	private int currentPosition;
	
	boolean done = false;

	public MoveElevatorToPos(int position) {

		//If the position is invalid
		if (position < 0 || position > 5) {
			System.out.println("BAD ELEVATOR POSITION");
			return;
		}
		
		elevator = Robot.currentElevator;
		
		desiredPosition = position;
		
		requires(Robot.currentElevator);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elevator = Robot.currentElevator;
    	currentPosition = elevator.getElevatorPos();
    	
    	if (desiredPosition == ERROR_NUM) {
    		System.out.println("BAD ELEVATOR POSITION");
    		done = true;
    		return;
    	}
    	
    	if (currentPosition > desiredPosition) {
    		elevator.moveDown();
    	}
    	else if (currentPosition < desiredPosition) {
    		elevator.moveUp();
    	}
    	else if (currentPosition == desiredPosition) {
    		elevator.setStill();
    		done = true;
    	}
    	
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
