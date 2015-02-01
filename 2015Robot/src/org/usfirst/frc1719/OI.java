// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1719;

import org.usfirst.frc1719.commands.AutonomousCommand;
import org.usfirst.frc1719.commands.CentreCamera;
import org.usfirst.frc1719.commands.DriveServos;
import org.usfirst.frc1719.commands.ExtendFisher;
import org.usfirst.frc1719.commands.LowerFisher;
import org.usfirst.frc1719.commands.RaiseFisher;
import org.usfirst.frc1719.commands.RetractFisher;
import org.usfirst.frc1719.commands.MoveElevatorUp;
import org.usfirst.frc1719.commands.ToggleCamera;
import org.usfirst.frc1719.commands.TransferCameraControl;
import org.usfirst.frc1719.commands.TurnToCamera;
import org.usfirst.frc1719.commands.UseDrive;
import org.usfirst.frc1719.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//Magic number declarations for buttons
	public final static int A_BUTTON = 1;
	public final static int B_BUTTON = 2;
	public final static int X_BUTTON = 3;
	public final static int Y_BUTTON = 4;
	public final static int LEFT_BUMPER  = 5;
	public final static int RIGHT_BUMPER = 6;
	public final static int BACK_BUTTON  = 7;
	public final static int START_BUTTON = 8;
	public final static int LEFT_JOYSTICK_BUTTON = 9;
	public final static int RIGHT_JOYSTICK_BUTTON = 10;
	
	public final static int LEFT_JOYSTICK_X_AXIS = 0;
	public final static int LEFT_JOYSTICK_Y_AXIS = 1;
	public final static int RIGHT_JOYSTICK_X_AXIS = 4;
	public final static int RIGHT_JOYSTICK_Y_AXIS = 5;
	public final static int LEFT_TRIGGER  = 2;
	public final static int RIGHT_TRIGGER = 3;
	
	//POV is the big plus thingy
	public final static int POV_NUMBER = 0;
	
    public static final int TRANSFER_CAMERA_CONTROL_BUTTON = 1;
    
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    
    private Joystick driverJoystick;
    private Joystick operatorJoystick;
    private JoystickButton leftJoystickButtonPressed;
    private JoystickButton bButton;
    private JoystickButton rightBumper;
    private JoystickButton leftBumper;




    public OI() {

        driverJoystick = new Joystick(0);
        operatorJoystick = new Joystick(1);
        
        rightBumper = new JoystickButton(driverJoystick, RIGHT_BUMPER);
        rightBumper.whenPressed(new ToggleCamera());
        
        leftBumper = new JoystickButton(driverJoystick, LEFT_BUMPER);
        leftBumper.whenPressed(new TurnToCamera());
        
        bButton = new JoystickButton(operatorJoystick, B_BUTTON);
        bButton.whenPressed(new MoveElevatorUp(Elevator.ELEVATOR_FRONT) );
        
        
        // Enabling one button as a time is dealt with in TransferCameraControl.execute().
        (new JoystickButton(driverJoystick, TRANSFER_CAMERA_CONTROL_BUTTON))
        	.whenPressed(new TransferCameraControl(TransferCameraControl.DRIVER));
        (new JoystickButton(operatorJoystick, TRANSFER_CAMERA_CONTROL_BUTTON))
        	.whenPressed(new TransferCameraControl(TransferCameraControl.OPERATOR));
        
        
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("UseDrive", new UseDrive());
        SmartDashboard.putData("DriveServos", new DriveServos());
        SmartDashboard.putData("CentreCamera", new CentreCamera());
         
    }
    
    public Joystick getDriverJoystick() {
        return driverJoystick;
    }
    
    public Joystick getOperatorJoystick() {
    	return operatorJoystick;
    }

}

