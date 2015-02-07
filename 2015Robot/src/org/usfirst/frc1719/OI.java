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
import org.usfirst.frc1719.commands.MoveElevatorToPos;
import org.usfirst.frc1719.commands.RetractFisher;
import org.usfirst.frc1719.commands.ToggleElevator;
import org.usfirst.frc1719.commands.UseDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//Modes
	final int XBOX = 0;
	final int JOYSTICK = 1;
	final int MODE_BACK  = 0;
	final int MODE_FRONT = 1;
	static int currentMode = 0;
	int driverRotationAxis;
	//Button Declaration
	
	//XBOX BINDINGS
	final int LEFT_X = 0;
	final int LEFT_Y = 1;
	final int LEFT_TRIGGER = 3;
	final int RIGHT_TRIGGER = 4;
	final int RIGHT_X = 5;
	final int RIGHT_Y = 6;
	final int A_BUTTON = 1;
	final int B_BUTTON = 2;
	final int X_BUTTON = 3;
	final int Y_BUTTON = 4;
	final int LEFT_BUMPER = 5;
	final int RIGHT_BUMPER = 6;
	final int BACK_BUTTON = 7;
	final int START_BUTTON = 8;
	final int LEFT_BUTTON = 9;
	final int RIGHT_BUTTON = 10;
	
	//LOGITECH ATTACK 3 BINDINGS
	final int ATTACK_X_AXIS = 0;
	final int ATTACK_Y_AXIS = 1;
	final int ATTACK_TRIGGER = 1;
	final int ATTACK_BUTTON_2 = 2;
	final int ATTACK_BUTTON_3 = 3;
	final int ATTACK_BUTTON_4 = 4;
	final int ATTACK_BUTTON_5 = 5;
	final int ATTACK_BUTTON_6 = 6;
	final int ATTACK_BUTTON_7 = 7;
	final int ATTACK_BUTTON_8 = 8;
	final int ATTACK_BUTTON_9 = 9;
	final int ATTACK_BUTTON_10 = 10;
	final int ATTACK_BUTTON_11 = 11;
	
	//WINGMAN BINDINGS
	final int WINGMAN_X_AXIS = 0;
	final int WINGMAN_Y_AXIS = 1;
	final int WINGMAN_Z_AXIS = 2;
	final int WINGMAN_THROTTLE = 3;
	final int WINGMAN_TRIGGER = 1;
	final int WINGMAN_BUTTON_2 = 2;
	final int WINGMAN_BUTTON_3 = 3;
	final int WINGMAN_BUTTON_4 = 4;
	final int WINGMAN_BUTTON_5 = 5;
	final int WINGMAN_BUTTON_6 = 6;
	final int WINGMAN_BUTTON_7 = 7;
	
	//Non Hardware Bindings
	final int DRIVER_X = 0;
	final int DRIVER_Y = 1;
	
	
	//POV is the big plus thingy
	
    
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

    
    private Joystick driverController;
    private Joystick operatorJoystick;
    private Joystick cameraJoystick;
    	
    	
    
 
    public OI() {
    	driverController = new Joystick(0);
        operatorJoystick = new Joystick(1);
        cameraJoystick = new Joystick(2);
        

        //Button Creations
        Button elevatorPos0 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_6);
        Button elevatorPos1 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_7);
        Button elevatorPos2 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_8);
        Button elevatorPos3 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_9);
        Button elevatorPos4 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_10);
        Button elevatorPos5 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_11);
        //Suppressed because currently unused but will be in the future
        @SuppressWarnings("unused")
    	Button toggleClaws = new JoystickButton(operatorJoystick, ATTACK_TRIGGER);
        Button modeFront = new JoystickButton(operatorJoystick, ATTACK_BUTTON_3);
        Button modeBack = new JoystickButton(operatorJoystick, ATTACK_BUTTON_2);
        Button extendFisher = new JoystickButton(driverController, WINGMAN_BUTTON_4);
        Button retractFisher = new JoystickButton(driverController, WINGMAN_BUTTON_5);
        
        
        
        //Driver Buttons
        retractFisher.whenPressed(new RetractFisher());
        extendFisher.whenPressed(new ExtendFisher());
        
        
        //Operator Controller
        elevatorPos0.whenPressed(new MoveElevatorToPos(0));
        elevatorPos1.whenPressed(new MoveElevatorToPos(1));
        elevatorPos2.whenPressed(new MoveElevatorToPos(2));
        elevatorPos3.whenPressed(new MoveElevatorToPos(3));
        elevatorPos4.whenPressed(new MoveElevatorToPos(4));
        elevatorPos5.whenPressed(new MoveElevatorToPos(5));
        modeFront.whenPressed(new ToggleElevator(MODE_FRONT));
        modeBack.whenPressed(new ToggleElevator(MODE_BACK));
         
        /* PLACEHOLDER CODE FOR CLAWS
         * toggleClaws.whenPressed(new toggleClaws())
         */
       
        

        
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("UseDrive", new UseDrive());
        SmartDashboard.putData("DriveServos", new DriveServos());
        SmartDashboard.putData("CentreCamera", new CentreCamera());

        
        
        
        
         
    }
    
    //Periodic method for updating control configurations
    public void OIPeriodic(){
    	if((int) Robot.driverController.getSelected() == XBOX){
    		driverRotationAxis = RIGHT_X;
    	}
    	if((int) Robot.driverController.getSelected() == JOYSTICK){
    		driverRotationAxis = WINGMAN_Z_AXIS;
    	}
    			
    }
    
    
    public Joystick getDriverJoystick() {
        return driverController;
    }
    
    public Joystick getOperatorJoystick() {
    	return operatorJoystick;
    }
    
    public double getCameraX(){
    	double x;
    	x = cameraJoystick.getRawAxis(ATTACK_X_AXIS);
    	return x;
    }
    
    public double getCameraY(){
    	double y;
    	y = cameraJoystick.getRawAxis(ATTACK_Y_AXIS);
    	return y;
    }
    
    public double getDriveX(){
    	double x;
    	x = driverController.getRawAxis(DRIVER_X);
    	return x;
    }
    public double getDriveY(){
    	double y;
    	y = driverController.getRawAxis(DRIVER_Y);
    	return y;
    }
    public double getDriveR(){
    	double r;
    	r = driverController.getRawAxis(driverRotationAxis);
    	return r;
    }
    public static int getMode(){
    	return currentMode;
    }
    public static void setMode(int newMode){
    	currentMode = newMode;
    }
    

}

