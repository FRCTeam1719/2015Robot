package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;


public class MoveElevatorToPos extends Command {
	
	//If this is not set in the constructor, then something bad happened, and
	//the command shouldn't do anything
	public static double ERROR_NUM = -1337D;
	public static double TOLERANCE = 0.2D;
	
	private double desiredPotPosition = ERROR_NUM;
	private double currentPotPosition;
	Elevator elevator;
	
	boolean done = false;

	public MoveElevatorToPos(int position) {

		//If the position is invalid
		if (position < 0 || position > 5) {
			System.out.println("BAD ELEVATOR POSITION");
			done = true;
			return;
		}
		
		elevator = Robot.currentElevator;
		
		desiredPotPosition = Elevator.POTENTIOMETER_POS[position];
		
		requires(Robot.currentElevator);
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elevator = Robot.currentElevator;
    	currentPotPosition = elevator.getPotPos();
    	
    	//If the elevator is at the correct position
    	if (elevator.atPotPos(desiredPotPosition)) {
    		elevator.setStill();
    		done = true;
    		return;
    	}
    	
    	//If the desired position is above the current one
    	if (desiredPotPosition < currentPotPosition) {
    		elevator.moveUp();
    	}
    	//If the desired position is below the current one
    	else if (desiredPotPosition > currentPotPosition) {
    		elevator.moveDown();
    	}
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
