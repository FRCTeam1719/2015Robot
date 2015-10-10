package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveDistance extends Command {
	
	public static boolean DIRECTION_FORWARDS = true;
	public static boolean DIRECTION_BACKWARDS = false;
	
	double SPEED = 0.5;
	
	double desiredDistance;
	double currentDistance;
	boolean direction;

    public MoveDistance(double distance, boolean direction) {
    	requires(Robot.drive);
    	
    	desiredDistance = currentDistance;
    	this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.sensors.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.moveCartesian(0, SPEED, 0, true);
    	currentDistance = Robot.sensors.getDistance();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return currentDistance > desiredDistance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.moveCartesian(0, 0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.moveCartesian(0, 0, 0, false);
    }
    
    public void init() {
    	initialize();
    }
    public void exec() {
    	execute();
    }
    
    public boolean isDone() {
    	return isFinished();
    }
    
    
}
