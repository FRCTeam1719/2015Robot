package org.usfirst.frc1719.autonomous;

import org.usfirst.frc1719.Robot;

public class GetCtrByDistance implements ICommandOption {

	private int stage = 0;
	private double ctr_rng;
	private static final double TOLERANCE_1 = 1.0D;
	private static final double TOLERANCE_2 = 0.1D;
	private static final double FULL_SPEED = 1.0D;
	private static final double NIL = 0.0D;
	
	@Override
	public void doCMD() {
		switch(stage) {
			case 0:
				ctr_rng = Robot.sensors.getDistanceM();
				stage++;
			case 1:
				if(Math.abs(ctr_rng - Robot.sensors.getDistanceM()) > TOLERANCE_1) stage++;
				else {
					Robot.drive.moveMechanum(FULL_SPEED, Math.PI / 2, NIL);
					break;
				}
			case 2:
				if(fisherExtended()) stage++;
				else {
					extendFisher();
					break;
				}
			case 3:
				if(Math.abs(ctr_rng - Robot.sensors.getDistanceM()) < TOLERANCE_2) stage++;
				else {
					Robot.drive.moveMechanum(FULL_SPEED, -Math.PI / 2, NIL);
					break;
				}
			case 4:
				if(fisherRetracted()) stage++;
				else {
					retractFisher();
					break;
				}
			case 5:
				if(Math.abs(ctr_rng - Robot.sensors.getDistanceM()) < TOLERANCE_2) stage--;
				else {
					if(!fisherExtended()) extendFisher();
					Robot.drive.moveMechanum(FULL_SPEED, Math.PI / 2, NIL);
					break;
				}
		}
	}

	@Override
	public boolean done() {
		return Robot.instance.isAutonomous() && Robot.instance.isEnabled();
	}

	// Template methods for use until we get the fisher API integrated.
	private void extendFisher() {
		
	}
	
	private void retractFisher() {
		
	}
	
	private boolean fisherExtended() {
		return true;
	}
	
	private boolean fisherRetracted() {
		return true;
	}
}
