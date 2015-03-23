package org.usfirst.frc1719.autonSelections;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoSelection;

import edu.wpi.first.wpilibj.command.Command;

public class GetInZone extends Command implements IAutoSelection {

	private static final double NIL = 0.0D;
	private static final double SPD = 0.5D;
	private static final double DISTANCE = 1400.0D;

	public GetInZone() {
		requires(Robot.drive);
	}
	@Override
	protected void end() {
		Robot.drive.moveCartesian(0, 0, 0, false);		
	}

	@Override
	protected void execute() {
		
		System.out.println("ENC VALUE" + Robot.sensors.getEncoder10Distance());
		Robot.drive.moveCartesian(NIL, SPD, NIL, false);
	}

	@Override
	protected void initialize() {
		Robot.sensors.resetEncoder10();		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(Robot.sensors.getEncoder10Distance()) > DISTANCE);
	}

}
