package org.usfirst.frc1719.autonomousCommands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.commands.MoveDistance;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ModularMoveDistance extends Command implements IAutoCommand {
	
	public static final boolean DIRECTION_FORWARDS = true;
	public static final boolean DIRECTION_BACKWARDS = false;
	
	public static final double SPEED = 0.5D;
	
	//Measured in feet
	public static final double TOLERANCE = 1 / 12; //One inch
	
	int actionNumber;
	
	MoveDistance moveCommand;
	
	public ModularMoveDistance(int actionNum, boolean direction) {
		
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
		moveCommand.exec();
	}

	@Override
	protected void initialize() {
		moveCommand = new MoveDistance(SmartDashboard.getNumber("Move Distance " + actionNumber + " (Feet)"), direction);
		moveCommand.init();
		Robot.sensors.reset();
	}

	@Override
	protected void interrupted() {
		Robot.drive.moveCartesian(0, 0, 0, false);
	}

	@Override
	protected boolean isFinished() {
		return moveCommand.isDone();
	}

}
