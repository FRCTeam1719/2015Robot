package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;

public class OpenBackClaw extends Command implements IAutoCommand {

	public OpenBackClaw() {
		requires(Robot.backClaw);
	}
	
	@Override
	protected void end() {		
	}

	@Override
	protected void execute() {
		Robot.backClaw.open();
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
