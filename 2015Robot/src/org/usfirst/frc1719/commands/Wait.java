package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;

/**Wait for a certain amount of seconds **/
public class Wait extends Command implements IAutoCommand {
	
	public static final double WAIT_TOLERANCE = .5;
	double desiredTime;
	double startTime = 0;
	double timePassed = 0;
	boolean done = false;
	
	public Wait(double desiredTime) {
		this.desiredTime = desiredTime;
	}
	
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
	
	public boolean finished() {
		return isFinished();
	}


}
