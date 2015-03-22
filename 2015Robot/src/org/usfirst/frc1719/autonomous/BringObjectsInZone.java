package org.usfirst.frc1719.autonomous;

import org.usfirst.frc1719.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BringObjectsInZone extends Command {
    
    private boolean done = false;
    private static double DISTANCE = 3500.0;
    int iterationNumber;
    

	@Override
	protected void end() {
		Robot.drive.moveCartesian(0, 0, 0, false);
		done = false;
	}

	@Override
	protected void execute() {
		System.out.println("ENTERED COMMAND");
		
        if(iterationNumber < 30){
        	System.out.println("MOVING UP :" + iterationNumber);
        	Robot.frontElevator.moveUp();
        	Robot.backElevator.moveUp();
        }else{
        	System.out.println("SETTING STILL");
        	Robot.frontElevator.setStill();
        	Robot.backElevator.setStill();
        	done = true;
        }
        System.out.println("ITERATING :" + iterationNumber);
        iterationNumber++;
		
	}

	@Override
	protected void initialize() {
		System.out.println("INIT");
        Robot.frontClaw.close();
        Robot.backClaw.close();
//        Robot.sensors.resetEncoder10();
        iterationNumber = 0;
	}

	@Override
	protected void interrupted() {
		
		
	}

	@Override
	protected boolean isFinished() {
		return done;
	}
    
}
