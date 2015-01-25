package org.usfirst.frc1719.autonomous;

import org.usfirst.frc1719.Robot;

public class DriveRight implements ICommandOption {

	public DriveRight() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCMD() {
		Robot.drive.moveCartesian(0.5D, 0.0D, 0.0D);

	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
