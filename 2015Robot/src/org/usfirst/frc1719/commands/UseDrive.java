// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class  UseDrive extends Command {
	
	//magic numbers: directions to prevent
	private static final boolean FRONT = true;
	private static final boolean BACK = false;
	private static final double ROTATION_REDUCTION = 0.5D;
	
	//Currently unused
	//private static final int RIGHT_Y = 5;
	//Tolerance for dead zone to make it possible to completely stop the robot
	private static final double TOLERANCE = 0.3D;
	//is used to slow down the print return of the gyro when testing
	//Currently Unused
	//private int i = 0;
    private static final double NIL = 0.0D;
    private static final double PTSPEED = 0.2;

	//Should a direction be prevented for robot movement?
	private boolean preventMovement = false;
	//which direction to prevent movement?
	private boolean directionPrevent = false;
	//Creating the lidar and infrared sensors
	Sensors sensor = new Sensors();
	
	
    public UseDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drive);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if(SmartDashboard.getBoolean("Avoid Accidents") && !Robot.oi.getAAAOverride()) {
			if(sensor.getLIDARDistanceCM() == 0) preventMovement = false;
			else if(sensor.getLIDARDistanceCM() < 70){
				preventMovement = true;
				directionPrevent = BACK;
			}
			else if(sensor.getUltrasonicDistanceCM() < 55){
				preventMovement = true;
				directionPrevent = FRONT;
			} else preventMovement = false;
		} else preventMovement = false;
    	//gets values from the joystick
    	double ly = Robot.oi.getDriveY();
    	double lx = Robot.oi.getDriveX();
    	double rx = Robot.oi.getDriveR();
    	
    	
    	//creates a dead zone within tolerance in order to make it possible to stop the robot
    	if (Math.abs(ly) < TOLERANCE) ly = 0.0D;
    	if (Math.abs(lx) < TOLERANCE) lx = 0.0D;
    	if (Math.abs(rx) < TOLERANCE) rx = 0.0D;

    	
    	//If attempting to move in the banned direction, prevent that axis of movement in the banned direction
    	if(preventMovement){
    		if(directionPrevent == FRONT){
    			if(ly > 0){
    				ly = PTSPEED;
    			}
    		}
    		else if(directionPrevent == BACK){
    			if(ly < 0){
    				ly = PTSPEED;
    			}
    		}
    	}

    	//Drives (mechanum) given the values from the joystick
    	Robot.drive.moveCartesian(lx, ly, rx * ROTATION_REDUCTION, Robot.oi.getPIDOverride());
    	
    	if(Robot.getLoopIterationNumber() % 0x40 == 0) {
    		//System.out.println("LIDAR Distance: " + Robot.sensors.getDistance());
    	}
 
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
