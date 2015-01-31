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
	private static final long RETREAT_TIME = 5000L;
	private Date time;
	private boolean printlns = true; // for testing purposes
	
	@Override
	public void doCMD() {
		switch(stage) {
			case 0:
				ctr_rng = Robot.sensors.getDistanceM();
				stage++;
				System.out.println("Completed stage 0: Distance read as " + ctr_rng + "m.");
			case 1:
				if(printlns) {
					System.out.println("Beginning stage 1");
					printlns = false;
				}
				if(Math.abs(ctr_rng - Robot.sensors.getDistanceM()) > TOLERANCE_1) {
					stage++;
					System.out.println("Completed stage 1");
					printlns = true;
				}
				else {
					Robot.drive.moveCartesian(SPD, NIL, NIL);
					break;
				}
			case 2:
				Robot.fisher.extend();
				stage++;
				System.out.println("Completed stage 2");
			case 3:
				if(printlns) {
					System.out.println("Beginning stage 3");
					printlns = false;
				}
				if(Math.abs(ctr_rng - Robot.sensors.getDistanceM()) < TOLERANCE_2) {
					time = new Date();
					stage++;
					System.out.println("Completed stage 3");
					printlns = true;
				}
				else {
					Robot.drive.moveCartesian(-SPD, NIL, NIL);
					break;
				}
			case 4:
				if(printlns) {
					System.out.println("Beginning stage 4");
					printlns = false;
				}
				if(((new Date()).getTime() - time.getTime()) > RETREAT_TIME) {
					stage++;
					time = new Date();
					System.out.println("Completed stage 4");
					printlns = true;
				}
				else {
					Robot.fisher.retract();
					Robot.drive.moveCartesian(NIL, SPD, NIL);
					break;
				}
			case 5:
				if(printlns) {
					System.out.println("Beginning stage 5");
					printlns = false;
				}
				if(((new Date()).getTime() - time.getTime()) > RETREAT_TIME) {
					stage++;
					System.out.println("Completed stage 5");
					printlns = true;
				}
				else {
					Robot.fisher.extend();
					Robot.drive.moveCartesian(NIL, -SPD, NIL);
					break;
				}
			case 6:
				if(printlns) {
					System.out.println("Beginning stage 6");
					printlns = false;
				}
				if(Math.abs(ctr_rng - Robot.sensors.getDistanceM()) > TOLERANCE_1) {
					stage++;
					System.out.println("Completed stage 6");
					printlns = true;
				}
				else {
					Robot.drive.moveCartesian(SPD, NIL, NIL);
					break;
				}
			case 7:
				if(printlns) {
					System.out.println("Beginning stage 7");
					printlns = false;
				}
				if(Math.abs(ctr_rng - Robot.sensors.getDistanceM()) < TOLERANCE_2) {
					stage = 4;
					time = new Date();
					System.out.println("Completed stage 7 (final); returning to stage 4");
					printlns = true;
				}
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
