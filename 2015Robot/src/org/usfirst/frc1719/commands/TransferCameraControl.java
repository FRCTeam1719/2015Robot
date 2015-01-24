package org.usfirst.frc1719.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TransferCameraControl extends Command {

	public static final boolean OPERATOR = true;
	public static final boolean DRIVER = false;
	private static boolean controller = DRIVER;
	private static boolean done = false;
	private final boolean who;
	
    public TransferCameraControl(boolean par1) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	who = par1;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Hack -- make sure this command was called by the person currently in control.
    	if(!done && (controller == who)) {
    		controller = !controller;
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(done) {
        	done = false;
        	return true;
        } else return false;
    }

    public static boolean getController() {
    	return controller;
    }
    
    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
