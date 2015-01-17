package org.usfirst.frc1719.TEST.commands;

import org.usfirst.frc1719.TEST.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToCamera extends Command {
	
	//checks if the command was just called
	private boolean flag = true;
	//makes the command terminable
	private boolean done = false;
	//target for rotation: the current direction the camera is facing
	private double target = 0;
	//Degree tolerance for target
	private static final double TOLERANCE = 15.0D;
	//how fast the robot turns
	private static final double SPD = 0.5D;
	private static final double NIL = 0.0D;
	
    public TurnToCamera() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if this command has just been called, set gyro to 0 and set target to current camera direction
    	if(flag) {
    		Robot.sensors.getGyro().reset();
    		target = (Robot.cameraMount.getXPos() * 180) - 90;
    		//makes sure this wont be run again until next time
    		flag = false;
    	}
    	//Gets current angle from when the command was first called
    	double curr = Robot.sensors.getGyro().getAngle();
    	//Checks if rotation is complete to within tolerance
    	if (Math.abs(target - curr) < TOLERANCE) {
    		System.out.println("Rotation Completed");
    		//Centers camera
    		Robot.cameraMount.center();
    		//ends command
    		done = true;
    		//resets flag so that the command will work next time
    		flag = true;
    		return;
    	}
    	
    	System.out.println("Current Error: " + (target - curr));
    	double dir = (target < curr) ? -SPD : SPD;
    	Robot.drive.moveMechanum(NIL, NIL, dir);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    //ends the program once the robot has turned to within tolerance
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
