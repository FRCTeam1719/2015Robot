package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.OI;
import org.usfirst.frc1719.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClaw extends Command{
	boolean done = false;
	final int FRONT_CLAW = 0;
	final int BACK_CLAW = 1;
	final int RIGHT_TRIGGER = 2;
	final int LEFT_TRIGGER = 3;
	
	
	@Override
	protected void end() {
		
	}

	@Override
	protected void execute() {
		Robot.claws.toggleClaw(OI.getMode());
		done = true;
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void interrupted() {
		
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

}