package org.usfirst.frc1719.autonomousCommands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;

public class CloseFrontClaw extends Command implements IAutoCommand {

	public CloseFrontClaw() {
		requires(Robot.frontClaw);
	}
	
	@Override
	protected void end() {		
	}

	@Override
	protected void execute() {
		Robot.frontClaw.close();
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}


}
