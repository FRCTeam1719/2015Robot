package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.OI;
import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UseElevator extends Command {
	
	public static double JOYSTICK_FLICK_TOLERANCE_UP   = -0.8D;
	public static double JOYSTICK_FLICK_TOLERANCE_DOWN = 0.8D;
	public static double JOYSTICK_RETURN_TOLERANCE = 0.1D;
	public static double JOYSTICK_DEFAULT_POS = 0.5D;	
	
	boolean done = false;
	
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
    	
    	requires(Robot.frontElevator);
    	requires(Robot.backElevator);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	joystickY = Robot.oi.getOperatorJoystick().getRawAxis(joystickNum);
    	
    	//If the joystick is up
    	if (joystickY < JOYSTICK_FLICK_TOLERANCE_UP) {
    		
    		//If moveUp failed
    		if (elevator.moveUp() == false) {
    			elevator.setStill();
    			done = true;
    		}
    	}
    	
    	//If the joystick is down
    	else if (joystickY > JOYSTICK_FLICK_TOLERANCE_DOWN) {
    		
    		//If moveDown failed
    		if (elevator.moveDown() == false) {
    			elevator.setStill();
    			done = true;
    		}
    	}
    	else {
    		elevator.setStill();
    	}
    	
    	elevator.atPotPos();
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
