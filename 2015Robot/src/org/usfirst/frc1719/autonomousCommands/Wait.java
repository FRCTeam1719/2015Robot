package org.usfirst.frc1719.autonomousCommands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;

/**Wait for a certain amount of seconds **/
public class Wait extends Command implements IAutoCommand {
	
	public static final double WAIT_TOLERANCE = .5;
	
	public Wait(double seconds) {
		this.desiredTime = seconds;
	}
	
	double desiredTime;
	double startTime = 0;
	double timePassed = 0;
	boolean done = false;

	@Override
	protected void end() {
		done = false;
	}

	//Do nothing
	@Override
	protected void execute() {
		timePassed = Robot.getSeconds() - startTime;
	}

	@Override
	protected void initialize() {
		startTime = Robot.getSeconds();
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(timePassed - desiredTime) < WAIT_TOLERANCE;
	}

}
