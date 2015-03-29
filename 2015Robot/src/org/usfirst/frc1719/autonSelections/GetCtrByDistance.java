package org.usfirst.frc1719.autonSelections;

import java.util.Date;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoSelection;

import edu.wpi.first.wpilibj.command.Command;

public class GetCtrByDistance extends Command implements IAutoSelection {

	private int stage = 0;
	private double ctr_rng;
	private static final double TOLERANCE_1 = 1.0D;
	private static final double TOLERANCE_2 = 0.5D;
	private static final double SPD = 0.5D;
	private static final double NIL = 0.0D;
	private static final long RETREAT_TIME = 1000L;
	private static final long CLEARANCE_TIME = 1000L;
	private Date time;

	public GetCtrByDistance() {
		requires(Robot.drive);
		requires(Robot.fisher);
	}
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		switch(stage) {
		case 0:
			ctr_rng = Robot.sensors.getLIDARDistanceM();
			stage++;
		case 1:
			if(Math.abs(ctr_rng - Robot.sensors.getLIDARDistanceM()) > TOLERANCE_1) {
				stage++;
				time = new Date();
			}
			else {
				Robot.drive.moveCartesian(SPD, NIL, NIL, false);
				break;
			}
		case 2:
			if(((new Date()).getTime() - time.getTime()) > CLEARANCE_TIME) {
				stage++;
			}
			else {
				Robot.drive.moveCartesian(SPD, NIL, NIL, false);
				break;
			}
		case 3:
			if(Robot.fisher.lower()) {
				if (Robot.fisher.extend()) {
					stage++;
				}
				else break;
			}
			else break;
		case 4:
			if(Math.abs(ctr_rng - Robot.sensors.getLIDARDistanceM()) < TOLERANCE_2) {
				time = new Date();
				stage++;
			}
			else {
				Robot.drive.moveCartesian(-SPD, NIL, NIL, true);
				break;
			}
		case 5:
			if(((new Date()).getTime() - time.getTime()) > RETREAT_TIME) {
				stage++;
				time = new Date();
			}
			else {
				Robot.fisher.retract();
				Robot.drive.moveCartesian(NIL, SPD, NIL, true);
				break;
			}
		case 6:
			if(((new Date()).getTime() - time.getTime()) > RETREAT_TIME) {
				stage++;
			}
			else {
				Robot.fisher.extend();
				Robot.drive.moveCartesian(NIL, -SPD, NIL, false);
				break;
			}
		case 7:
			if(Math.abs(ctr_rng - Robot.sensors.getLIDARDistanceM()) > TOLERANCE_1) {
				stage++;
			}
			else {
				Robot.drive.moveCartesian(SPD, NIL, NIL, false);
				break;
			}
		case 8:
			if(Math.abs(ctr_rng - Robot.sensors.getLIDARDistanceM()) < TOLERANCE_2) {
				stage = 5;
				time = new Date();
			}
			else {
				Robot.drive.moveCartesian(SPD, NIL, NIL, false);
				break;
			}
		}
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
