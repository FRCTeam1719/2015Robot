package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveCameraCommand extends Command {
	double x, y;
	boolean done = false;

	public MoveCameraCommand(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	protected void end() {
		done = false;
	}

	@Override
	protected void execute() {
		System.out.println("moving camera to " + x + "," + y);
		Robot.cameraMount.setXServoRaw(x);
		Robot.cameraMount.setYServoRaw(y);
		done = true;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void interrupted() {
		done = false;
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

}
