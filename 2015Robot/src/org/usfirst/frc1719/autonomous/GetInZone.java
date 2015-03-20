package org.usfirst.frc1719.autonomous;

import org.usfirst.frc1719.Robot;

public class GetInZone implements ICommandOption {

	private static final double NIL = 0.0D;
	private static final double SPD = 0.5D;
	private static final double DISTANCE = 1400.0D;
	private boolean init = true;

	@Override
	public void doCMD() {
		if(init) {
			Robot.sensors.resetEncoder10();
			init = false;
		}
		System.out.println("ENC VALUE" + Robot.sensors.getEncoder10Distance());
		Robot.drive.moveCartesian(NIL, SPD, NIL, false);
	}

	@Override
	public boolean done() {
		if( Math.abs(Robot.sensors.getEncoder10Distance()) > DISTANCE){
			Robot.drive.moveCartesian(0, 0, 0, false);
			return true;
		}
		return false;
	}

}
