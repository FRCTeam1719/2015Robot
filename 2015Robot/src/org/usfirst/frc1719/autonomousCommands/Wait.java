package org.usfirst.frc1719.autonomousCommands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**Wait for a certain amount of seconds **/
public class Wait extends Command implements IAutoCommand {
	
	public static final double WAIT_TOLERANCE = .5;
	double desiredTime;
	double startTime = 0;
	double timePassed = 0;
	boolean done = false;
	
	public Wait(int actionNum, int desiredTime) {
		this.desiredTime = desiredTime;
		this.actionNum = actionNum;
	}
	
	int actionNum;
	
	

	
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
