package org.usfirst.frc1719.autonomousCommands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;

public class OpenFrontClaw extends Command implements IAutoCommand {

	public OpenFrontClaw() {
		requires(Robot.frontClaw);
	}
	
	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		Robot.frontClaw.open();
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
