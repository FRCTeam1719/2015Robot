package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;

public class CloseBackClaw extends Command implements IAutoCommand {

	boolean done;

	public CloseBackClaw() {
		requires(Robot.backClaw);
	}

	@Override
	protected void end() {
	}

	@Override
	protected void execute() {
		Robot.backClaw.close();
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
