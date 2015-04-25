package org.usfirst.frc1719.autonSelections;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoSelection;

import edu.wpi.first.wpilibj.command.Command;

public class PickupTwoBins extends Command implements IAutoSelection {

	boolean done = false;
	int iterationNumber = 0;
	
	public PickupTwoBins() {
		requires(Robot.frontElevator);
		requires(Robot.backElevator);
		requires(Robot.frontClaw);
		requires(Robot.backClaw);
		requires(Robot.drive);
	}

	@Override
	protected void end() {
		done = false;
		Robot.frontElevator.setStill();
		Robot.backElevator.setStill();
		Robot.drive.moveCartesian(0, 0, 0, false);
		System.out.println("DONE");
	}

	@Override
	protected void execute() {
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
			done = true;
		}
		iterationNumber++;
		
	}

	@Override
	protected void initialize() {
		Robot.backClaw.close();
		iterationNumber = 0;
		System.out.println("CMDINIT");
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
