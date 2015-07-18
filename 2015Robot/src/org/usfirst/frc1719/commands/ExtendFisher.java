package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IDisableable;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExtendFisher extends Command implements IDisableable {
	//makes program terminable
	boolean done = false;
	
	public ExtendFisher() {
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
		
		//extends, ends once extended
		if(Robot.fisher.lower()){
			if (Robot.fisher.extend()) {
				done = true;
				
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;
	}
	// Called once after isFinished returns true
	//makes the command repeatably executable
	protected void end() {
		Robot.commands.remove(this);
		done = false;
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
