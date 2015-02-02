// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc1719.subsystems;

import java.util.Date;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.RobotMap;
import org.usfirst.frc1719.commands.UseDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Subsystem implements Testable {
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private static final int COOLDOWN_TIME = 1000;
	private Date lastRot = new java.util.Date(0L);
	SpeedController leftRear = RobotMap.driveLeftRear;
	SpeedController rightRear = RobotMap.driveRightRear;
	SpeedController leftFront = RobotMap.driveLeftFront;
	SpeedController rightFront = RobotMap.driveRightFront;
	RobotDrive robotDrive = RobotMap.driveRobotDrive;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private boolean isPIDEnabled = true;
	private double[] pastErr = new double[0];
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	/**
	 * Moves the mechanum drive in a given way.
	 * 
	 * @param magnitude
	 *            a double on the interval [-1.0,1.0]. Defines the speed at
	 *            which the robot should move.
	 * @param direction
	 *            the radian measure of angle between the direction of the
	 *            strafe and the 'forward' of the robot.
	 * @param rotation
	 *            a double on the interval [-1.0,1.0]. Defines the speed at
	 *            which the robot should turn in place.
	 */
	@Deprecated
	public void moveMechanum(double magnitude, double direction, double rotation) {
		// converts from degrees to radians then executes mecanumdrivepolar
		robotDrive.mecanumDrive_Polar(magnitude, direction * 180 / Math.PI,
				rotation);
	}

	public void moveCartesian(double x, double y, double rot) {
		// get PID constants
		double KP = SmartDashboard.getNumber("KP") * 0.001D;
		double KI = SmartDashboard.getNumber("KI") * 0.001D;
		double KD = SmartDashboard.getNumber("KD") * 0.001D;

		// Try to go straight if desired using PID
    	if(isPIDEnabled) {
    		if((rot != 0.0D) || ((y == 0.0D) && (x == 0.0D))) isPIDEnabled = false;
    		else {
    			double err = Robot.sensors.getGyro().getAngle();
    			double ierr = 0.0D;
    			for(int i = 0; i < pastErr.length; i++) {
    				ierr += pastErr[i];
    			}
    			double derr = (pastErr.length == 0) ? 0.0D : err - pastErr[pastErr.length - 1];
    			rot = -(KP * err + KI * ierr + KD * derr);
    			double[] nperr = new double[pastErr.length + 1];
    			System.arraycopy(pastErr, 0, nperr, 0, pastErr.length);
    			nperr[pastErr.length] = err;
    			pastErr = nperr;
    		}
    	} else if((((new Date()).getTime() - lastRot.getTime()) > COOLDOWN_TIME) 
    			&& (rot == 0.0D) && ((y != 0.0D) || (x != 0.0D))) {
    		Robot.sensors.getGyro().reset();
    		pastErr = new double[0];
    		isPIDEnabled = true;
    	} else if(rot != 0.0D) {
    		lastRot = new Date();
    	}
		robotDrive.mecanumDrive_Cartesian(x, y, rot, 0);
	}

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
		setDefaultCommand(new UseDrive());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	@Override
	public void test() {
		// move foraward
		System.out.println("moving forward");
		for (int i = 0; i < 2000; i++) {
			moveCartesian(1.0D, 0.0D, 0.0D);
		}
		

	}
}
