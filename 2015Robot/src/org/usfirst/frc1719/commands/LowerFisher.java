package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IDisableable;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerFisher extends Command implements IDisableable {
	
	boolean done = false;
    public LowerFisher() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.fisher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.commands.add(this);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//lowers the arm, stops once done
    	if(Robot.fisher.lower()){
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//makes the command repeatedly executable
    	done = false;
    	
    	Robot.commands.remove(this);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	public void disable() {
		Robot.fisher.spike.set(Relay.Value.kOff);
		Robot.fisher.fisherAimSolenoid.set(false);
		Robot.fisher.fisherSolenoid.set(false);
	}
}
