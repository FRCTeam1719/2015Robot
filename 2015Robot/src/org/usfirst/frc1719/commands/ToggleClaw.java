package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClaw extends Command{
	boolean done = false;
	@Override
	protected void end() {
		
	}

	@Override
	protected void execute() {
		//TODO Work with the joystick triggers
		Robot.claws.toggleClaw(0);
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
