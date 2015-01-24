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

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import org.usfirst.frc1719.Robot;

/**
 *
 */
public class  UseDrive extends Command {
	//Magic numbers: these numbers determine the input from the joystick
	private static final int LEFT_X = 0;
	private static final int LEFT_Y = 1;
	private static final int RIGHT_X = 4;
	private static final double KP = 0.03D;
	private static final double KI = 0.001D;
	private static final double KD = 0.0001D;
	//Currently unused
	//private static final int RIGHT_Y = 5;
	//Tolerance for dead zone to make it possible to completely stop the robot
	private static final double TOLERANCE = 0.3D;
	//is used to slow down the print return of the gyro when testing
	//Currently Unused
	//private int i = 0;
	private boolean flag = true;
	private double[] pastErr = new double[0];
	
	
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
    	//gets values from the joystick
    	double ly = Robot.oi.getDriverJoystick().getRawAxis(LEFT_Y);
    	double lx = Robot.oi.getDriverJoystick().getRawAxis(LEFT_X);
    	double rx = (!TransferCameraControl.getController()
    			&& Robot.oi.getDriverJoystick().getRawButton(DriveServos.JOYSTICK_RIGHT_BUTTON)) ? 
    			0.0D : Robot.oi.getDriverJoystick().getRawAxis(RIGHT_X);
    	
    	//creates a dead zone within tolerance in order to make it possible to stop the robot
    	if (Math.abs(ly) < TOLERANCE) ly = 0.0D;
    	if (Math.abs(lx) < TOLERANCE) lx = 0.0D;
    	if (Math.abs(rx) < TOLERANCE) rx = 0.0D;
    	
    	// Try to go straight if desired using PID
    	if(flag) {
    		if((rx != 0.0D) || ((ly == 0.0D) && (lx == 0.0D))) flag = false;
    		else {
    			double err = Robot.sensors.getGyro().getAngle();
    			double ierr = 0.0D;
    			for(int i = 0; i < pastErr.length; i++) {
    				ierr += pastErr[i];
    			}
    			double derr = (pastErr.length == 0) ? 0.0D : err - pastErr[pastErr.length - 1];
    			rx = -(KP * err + KI * ierr + KD * derr);
    			double[] nperr = new double[pastErr.length + 1];
    			System.arraycopy(pastErr, 0, nperr, 0, pastErr.length);
    			nperr[pastErr.length] = err;
    			pastErr = nperr;
    		}
    	} else if((rx == 0.0D) && ((ly != 0.0D) || (lx != 0.0D))) {
    		Robot.sensors.getGyro().reset();
    		pastErr = new double[0];
    		flag = true;
    	}
    	
    	//Drives (mechanum) given the values from the joystick
    	Robot.drive.moveCartesian(lx, ly, rx);
    	
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
