package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UseElevator extends Command {
	
	public static final double Y_AXIS_TOLERANCE = 0.1D;
	
	double joystickY = 0.0D;
	
	Elevator elevator;

    public UseElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.frontElevator);
    	requires(Robot.backElevator);
    	
    	elevator = Robot.currentElevator;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	joystickY = Robot.oi.getOperatorY();
    	
    	//This is in case the elevator gets toggled
    	elevator = Robot.currentElevator;
    	
    	elevator.setSpeed(Math.abs(joystickY) );
    	
    	//If the axis is within the tolerance, don't move
    	if (Math.abs(joystickY) < Y_AXIS_TOLERANCE) {
    		joystickY = 0;
    	}
    	//User is pulling down
    	if (joystickY < 0) {
    		elevator.moveDown();
    	}
    	
    	//User is pulling up
    	else if (joystickY > 0) {
    		elevator.moveUp();
    	}
    	
    	//User isn't moving the joystick
    	else if (joystickY == 0) {
    		elevator.setStill();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
