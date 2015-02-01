package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RetractFisher extends Command {
	//makes the program terminable
	boolean done = false;
	
	public RetractFisher() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.fisher);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("retract" + Robot.fisher.retract());
		if(Robot.fisher.retract()){
			done = true;
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
