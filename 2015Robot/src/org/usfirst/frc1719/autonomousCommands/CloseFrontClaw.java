package org.usfirst.frc1719.autonomousCommands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;

public class CloseFrontClaw extends Command implements IAutoCommand {

	boolean done;
	public CloseFrontClaw() {
		requires(Robot.frontClaw);
	}
	
	@Override
	protected void end() {		
	}

	@Override
	protected void execute() {
		Robot.frontClaw.close();
		done = true;
	}

	@Override
	protected void initialize() {
		done = false;
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		return done;
	}


}
