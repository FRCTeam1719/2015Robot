package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.RobotMap;
import org.usfirst.frc1719.interfaces.IDisableable;
import org.usfirst.frc1719.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleClaw extends Command implements IDisableable {
	boolean done = false;
	final int FRONT_CLAW = 0;
	final int BACK_CLAW = 1;
	final int RIGHT_TRIGGER = 2;
	final int LEFT_TRIGGER = 3;
	
	Claw currentClaw;
	
	public  ToggleClaw() {
		requires(Robot.currentClaw);
	}
	
	@Override
	protected void end() {
		Robot.commands.remove(this);
	}

	@Override
	protected void execute() {
		currentClaw = Robot.currentClaw;
		if (currentClaw.getState() == Claw.CLAW_OPEN) {
			currentClaw.close();
		}
		else if (currentClaw.getState() == Claw.CLAW_CLOSED) {
			currentClaw.open();
		}
		
		done = true;
	}

	@Override
	protected void initialize() {
		Robot.commands.add(this);
	}

	@Override
	protected void interrupted() {
		Robot.commands.remove(this);
		
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

	@Override
	public void disable() {
		RobotMap.frontClawSolenoid.set(false);
		RobotMap.backClawSolenoid.set(false);
	}

}
