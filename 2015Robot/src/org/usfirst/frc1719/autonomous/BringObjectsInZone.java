package org.usfirst.frc1719.autonomous;

import org.usfirst.frc1719.Robot;

public class BringObjectsInZone implements ICommandOption {
    
    private boolean init = true;
    private static double DISTANCE = 3500.0;
    int iterationNumber;
    @Override
    public void doCMD() {
    	System.out.println("ENTERED COMMAND");
        if(init) {
        	System.out.println("INIT");
            Robot.frontClaw.close();
            Robot.backClaw.close();
//            Robot.sensors.resetEncoder10();
            init = false;
            iterationNumber = 0;
        }
        if(iterationNumber < 30){
        	System.out.println("MOVING UP :" + iterationNumber);
        	Robot.frontElevator.moveUp();
        	Robot.backElevator.moveUp();
        }else{
        	System.out.println("SETTING STILL");
        	Robot.frontElevator.setStill();
        	Robot.backElevator.setStill();
        }
        System.out.println("ITERATING :" + iterationNumber);
        iterationNumber++;
    }
    
    @Override
    public boolean done() {
        if((Math.abs(Robot.sensors.getEncoder10Distance()) > DISTANCE) && (init != true)){
        	Robot.drive.moveCartesian(0, 0, 0, false);
        	return true;
        }
        return false;
    }
    
}
