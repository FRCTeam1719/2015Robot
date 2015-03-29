package org.usfirst.frc1719.autonSelections;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoSelection;

import edu.wpi.first.wpilibj.command.Command;

public class GetInZone extends Command implements IAutoSelection {

	private static final double NIL = 0.0D;
	private static final double SPD = 0.5D;
	private static final int DURATION = 150;

	public GetInZone() {
		requires(Robot.drive);
	}
	
	int startingLoopNumber;
	int currentLoopNumber;
	
	@Override
	protected void end() {
		Robot.drive.moveCartesian(0, 0, 0, false);		
	}

	@Override
	protected void execute() {
		currentLoopNumber = Robot.getLoopIterationNumber();
		
		
		System.out.println("Loop #: " + Robot.getLoopIterationNumber());
		Robot.drive.moveCartesian(SPD, NIL, NIL, false);
	}

	@Override
	protected void initialize() {
		Robot.sensors.resetEncoder10();	
		startingLoopNumber = Robot.getLoopIterationNumber();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		return (currentLoopNumber - startingLoopNumber) > DURATION;
	}

}
