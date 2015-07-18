package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.OI;
import org.usfirst.frc1719.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchElevator extends Command {

    public SwitchElevator() {
        requires(Robot.frontElevator);
        requires(Robot.backElevator);
        requires(Robot.currentElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.currentElevator.equals(Robot.frontElevator)) {
    		Robot.switchElevator(OI.MODE_BACK);
    		OI.setMode(OI.MODE_BACK);
    	}
    	else if (Robot.currentElevator.equals(Robot.backElevator)) {
    		Robot.switchElevator(OI.MODE_FRONT);
    		OI.setMode(OI.MODE_FRONT);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
