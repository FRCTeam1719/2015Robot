package org.usfirst.frc1719.autonomous;

import org.usfirst.frc1719.Robot;

public class BringObjectsInZone implements ICommandOption {
    
    private boolean init = true;
    private static double DISTANCE = 1000.0;
    
    @Override
    public void doCMD() {
        if(init) {
            Robot.frontClaw.close();
            Robot.backClaw.close();
            Robot.sensors.resetEncoder10();
            init = false;
        }
        Robot.drive.moveCartesian(1.0D, 0.0D, 0.0D, true);
        
    }
    
    @Override
    public boolean done() {
        return (Math.abs(Robot.sensors.getEncoder10Distance()) > DISTANCE) && (init = true);
    }
    
}
