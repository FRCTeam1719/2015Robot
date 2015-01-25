package org.usfirst.frc1719.autonomous;

import java.util.Date;

import org.usfirst.frc1719.Robot;

public class GetCtrByDistance implements ICommandOption {

	private int stage = 0;
	private double ctr_rng;
	private static final double TOLERANCE_1 = 1.0D;
	private static final double TOLERANCE_2 = 0.1D;
	private static final double SPD = 0.5D;
	private static final double NIL = 0.0D;
	private static final long RETREAT_TIME = 500L;
	private Date time;
	
	@Override
	public void doCMD() {
		switch(stage) {
			case 0:
				ctr_rng = Robot.sensors.getDistanceM();
				stage++;
			case 1:
				if(Math.abs(ctr_rng - Robot.sensors.getDistanceM()) > TOLERANCE_1) stage++;
				else {
					Robot.drive.moveCartesian(SPD, NIL, NIL);
					break;
				}
			case 2:
				if(Robot.fisher.extend()) stage++;
				else break;
			case 3:
				if(Math.abs(ctr_rng - Robot.sensors.getDistanceM()) < TOLERANCE_2) {
					time = new Date();
					stage++;
				}
				else {
					Robot.drive.moveCartesian(-SPD, NIL, NIL);
					break;
				}
			case 4:
				if(((new Date()).getTime() - time.getTime()) > RETREAT_TIME) {
					stage++;
					time = new Date();
				}
				else {
					Robot.fisher.retract();
					Robot.drive.moveCartesian(NIL, SPD, NIL);
					break;
				}
			case 5:
				if(((new Date()).getTime() - time.getTime()) > RETREAT_TIME) stage++;
				else {
					Robot.fisher.extend();
					Robot.drive.moveCartesian(NIL, -SPD, NIL);
				}
			case 6:
				if(Math.abs(ctr_rng - Robot.sensors.getDistanceM()) < TOLERANCE_2) stage--;
				else {
					Robot.drive.moveCartesian(SPD, NIL, NIL);
					break;
				}
		}
	}

	@Override
	public boolean done() {
		return false;
	}
}
