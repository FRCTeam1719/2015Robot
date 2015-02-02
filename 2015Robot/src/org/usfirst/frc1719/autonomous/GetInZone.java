package org.usfirst.frc1719.autonomous;

import java.util.Date;

import org.usfirst.frc1719.Robot;

public class GetInZone implements ICommandOption {

	private static final double NIL = 0.0D;
	private static final double SPD = 0.5D;
	private static final long TIME = 2000;
	private boolean flag = true;
	private Date start;

	@Override
	public void doCMD() {
		if(flag) {
			start = new Date();
			flag = false;
		}
		Robot.drive.moveCartesian(NIL, SPD, NIL);
	}

	@Override
	public boolean done() {
		return ((new Date()).getTime() - start.getTime()) > TIME;
	}

}
