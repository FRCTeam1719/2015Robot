package org.usfirst.frc1719.autonomous;

import org.usfirst.frc1719.Robot;

public class PickupTwoBins implements ICommandOption{

	boolean init = true;
	int iterationNumber = 0;
	
	@Override
	public void doCMD() {
		if(init){
			Robot.backClaw.close();
			iterationNumber = 0;
			init = false;
			System.out.println("CMDINIT");
		}
		if(iterationNumber < 200){
			Robot.backElevator.moveUp();
			System.out.println("MOVINGBACKUP");
		}else if(iterationNumber < 400){
			Robot.backElevator.setStill();
			Robot.drive.moveCartesian(0, -0.5, 0, false);
			System.out.println("STOPELVANDBACKUP");
		}else if(iterationNumber < 600){
			Robot.drive.moveCartesian(0, 0, 0, false);
			Robot.frontClaw.close();
			Robot.frontElevator.moveUp();
			System.out.println("STOPMOVINGANDCLOSEFRNTELV");
		}else{
			Robot.frontElevator.setStill();
			Robot.backElevator.setStill();
			Robot.drive.moveCartesian(0, 0, 0, false);
			System.out.println("DONE");
		}
		iterationNumber++;
		
	}

	@Override
	public boolean done() {
		return false;
	}

}
