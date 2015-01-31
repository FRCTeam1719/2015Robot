package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.OI;
import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UseElevator extends Command {
	
	public static double JOYSTICK_FLICK_TOLERANCE = 0.8D;
	public static double JOYSTICK_RETURN_TOLERANCE = 0.2D;
	public static double JOYSTICK_DEFAULT_POS = 0.5D;	
	
	boolean done = false;
	private boolean joystickHasReturned = true;
	
	int joystickNum;
	double joystickY = 0.0D;
	
	Elevator elevator;

    public UseElevator(int elevatorNum) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	if (elevatorNum == Elevator.ELEVATOR_BACK) {
    		elevator = Robot.backElevator;
    		joystickNum = OI.RIGHT_JOYSTICK_Y_AXIS;
    	}
    	if (elevatorNum == Elevator.ELEVATOR_FRONT) {
    		elevator = Robot.frontElevator;
    		joystickNum = OI.LEFT_JOYSTICK_Y_AXIS;
    	}
    	
    	requires(elevator);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	joystickY = Robot.oi.getOperatorJoystick().getRawAxis(joystickNum);
    	
    	if (elevator.isMoving()) {
    		
    		if (elevator.getMoveType() == Elevator.MOVE_TYPE_FREE) {
    			
    			//If the elevator has moved past the potPos, change it to stepping,
    			//So that it will stop at future PotPos's
    			if (!elevator.atPotPos()) {
    				
    				//If the elevator was moving down,
    				if (elevator.getDirection() == Elevator.MOVE_DIRECTION_DOWN) {
    					elevator.moveDown();
    				}
    				//If it was moving up
    				else if (elevator.getDirection() == Elevator.MOVE_DIRECTION_UP) {
    					elevator.moveUp();
    				}
    				
    			}
    		}
    	}
    	
    	//The elevator is still
    	else {
    		
    		//If the joystick isn't in the middle
    		if (!(Math.abs(joystickY) < JOYSTICK_FLICK_TOLERANCE)) {
    			if (joystickHasReturned) {
    				
    				//If the joystick was flicked up
    				if (joystickY > 0) {
    					
    					//Move the elevator without regard for potPos,
    					//This will allow it to move past the potentiometer's tolerance
    					elevator.moveFreeUp();
    				}
    				//If it was flicked down
    				else if (joystickY > 0) {
    					
    					//Move the elevator without regard for potPos
    					//This will allow it to move past the potentiometer's tolerance
    					elevator.moveFreeDown();
    				}
    			}
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
