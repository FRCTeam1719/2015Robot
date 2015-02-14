package org.usfirst.frc1719.autonomous;

import org.usfirst.frc1719.Robot;

public class GetInZone implements ICommandOption {

	private static final double NIL = 0.0D;
	private static final double SPD = 0.5D;
	private static final double DISTANCE = 1000.0D;
	private boolean flag = true;

	@Override
	public void doCMD() {
		if(flag) {
			Robot.sensors.resetEncoder10();
			flag = false;
		}
		Robot.drive.moveCartesian(NIL, SPD, NIL, true);
	}

	@Override
	public boolean done() {
		return Math.abs(Robot.sensors.getEncoder10Distance()) > DISTANCE;
	}

}
