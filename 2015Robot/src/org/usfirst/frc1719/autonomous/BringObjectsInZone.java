package org.usfirst.frc1719.autonomous;

import org.usfirst.frc1719.Robot;

public class BringObjectsInZone implements ICommandOption {
    
    private boolean init = true;
    private static double DISTANCE = 3500.0;
    
    @Override
    public void doCMD() {
        if(init) {
            Robot.frontClaw.close();
            Robot.backClaw.close();
            Robot.sensors.resetEncoder10();
            init = false;
        }
        if(Robot.loopIterationNumber < 200){
        	Robot.frontElevator.moveUp();
        }else{
        	Robot.frontElevator.setStill();
        }
        Robot.drive.moveCartesian(1.0D, 0.0D, 0.05D, false);
        
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
