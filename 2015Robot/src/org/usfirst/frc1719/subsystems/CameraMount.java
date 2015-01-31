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

import org.usfirst.frc1719.RobotMap;
import org.usfirst.frc1719.commands.DriveServos;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * TODO (DGOLDDRAGON28) IMPLEMENT TESTABLE
 */

public class CameraMount extends Subsystem {
	
	//Gives this command access to the camera servos
	Servo yServo = RobotMap.cameraMountYServo;
    Servo xServo = RobotMap.cameraMountXServo;
    
    //stores data when called
    double panPositionX = 0.0D;
    double panPositionY = 0.0D;
    
    //Magic numbers
    //min and max servo positions, useful max for y servo (cannot look straight up), how fast servo pans
    private static final double SERVO_MIN = 0.0D;
    private static final double SERVO_MAX = 1.0D;
    private static final double SERVOY_MAX = 0.6D;
    private static final double SERVO_INCREMENT = 0.015D;
    private static final double SERVO_CENTER = 0.5D;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveServos());
    }
    
  //sets the x servo to given position, between 0 and 1
    public void setXServoRaw(double position) {
    	panPositionX = position;
    	xServo.set(position);
    }

    
    //slowly pans in the direction of the joystick
    public void setXServoPan(double joystickPosition) {
    	// Make Servo pan slower
    	panPositionX += (joystickPosition * SERVO_INCREMENT);
    	//makes sure the servo stays within its range
    	if (panPositionX < SERVO_MIN) panPositionX = SERVO_MIN;
    	if (panPositionX > SERVO_MAX) panPositionX = SERVO_MAX;
    	xServo.set(panPositionX);
    }
    
    //sets the y servo to given position
    public void setYServoRaw(double position) {
    	panPositionY = position;
    	yServo.set(position);
    }
    
    //slowly pans in the direction of the joystick
    public void setYServoPan(double joystickPosition) {
    	//makes servo pan servo
    	panPositionY -= (joystickPosition * SERVO_INCREMENT);
    	//makes sure the servos dont exceed their range
    	if (panPositionY < SERVO_MIN) panPositionY = SERVO_MIN;
    	if (panPositionY > SERVOY_MAX) panPositionY = SERVOY_MAX;
    	yServo.set(panPositionY);
    }
    
    //sets the servo to .5,.5 the center
    public void center() {
    	setXServoRaw(SERVO_CENTER);
    	setYServoRaw(SERVO_CENTER);
    }
    
    public double getXPos() {
    	return panPositionX;
    }
    
    public double getYPos() {
    	return panPositionY;
    }
}

