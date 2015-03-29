package org.usfirst.frc1719.autonomousCommands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveDistance extends Command implements IAutoCommand {
	
	public static final boolean DIRECTION_FORWARDS = true;
	public static final boolean DIRECTION_BACKWARDS = false;
	
	public static final double SPEED = 0.5D;
	
	//Measured in feet
	public static final double TOLERANCE = 1 / 12; //One inch
	
	int actionNumber;
	
	public MoveDistance(int actionNum, boolean direction) {
		requires(Robot.drive);
		requires(Robot.sensors);
		
		actionNumber = actionNum;
		this.direction = direction;
	}
	
	boolean done = false;
	
	boolean direction;
	
	//Measured in feet
	double desiredDistance;
	double currentDistance = 0;
	
	@Override
	protected void end() {
		done = false;
		Robot.drive.moveCartesian(0, 0, 0, false);
	}

	@Override
	protected void execute() {
		currentDistance = Robot.sensors.getDistance();
		
		//If we have moved the desired distance
		if ( Math.abs(desiredDistance - currentDistance) < TOLERANCE ) {
			done = true;
			return;
		}
		
		if (direction == DIRECTION_FORWARDS) {
			Robot.drive.moveCartesian(0, SPEED, 0, true);
		}
		else if (direction == DIRECTION_BACKWARDS) {
			Robot.drive.moveCartesian(0, -SPEED, 0, true);
		}
	}

	@Override
	protected void initialize() {
		desiredDistance = SmartDashboard.getNumber("Move Distance " + actionNumber + " (Feet)");
		Robot.sensors.reset();
	}

	@Override
	protected void interrupted() {
		Robot.drive.moveCartesian(0, 0, 0, false);
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

}
