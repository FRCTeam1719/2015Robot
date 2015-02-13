package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.kntbs.RIOKeys;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestKeys extends Command {

    public TestKeys() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println(RIOKeys.isKeyPressed('G') ? "'G' key is pressed." : "\n");
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
