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

import org.usfirst.frc1719.commands.CentreCamera;
import org.usfirst.frc1719.commands.DriveServos;
import org.usfirst.frc1719.commands.ExtendFisher;
import org.usfirst.frc1719.commands.MoveCameraCommand;
import org.usfirst.frc1719.commands.RetractFisher;
import org.usfirst.frc1719.commands.SwitchElevator;
import org.usfirst.frc1719.commands.ToggleClaw;
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
	public static final int MODE_BACK  = 0;
	public static final int MODE_FRONT = 1;
	static int currentMode = 0;
	int driverRotationAxis;
	//Button Declaration
	
	//XBOX BINDINGS
	final int LEFT_X = 0;
	final int LEFT_Y = 1;
	final int LEFT_TRIGGER = 2;
	final int RIGHT_TRIGGER = 3;
	final int RIGHT_X = 4;
	final int RIGHT_Y = 5;
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
    
    private Button elevatorPos0;
    private Button elevatorPos1;
    private Button elevatorPos2;
    private Button elevatorPos3;
    private Button elevatorPos4;
    private Button elevatorPos5;
    private Button cameraPos1;
    private Button cameraPos2;
    private Button cameraPos3;
    private Button cameraPos4;
    private Button switchElevator;
    private Button slowSpeed;
    
 
    public OI() {
    	driverController = new Joystick(0);
        operatorJoystick = new Joystick(1);
        cameraJoystick = new Joystick(2);
        

        //Button Creations
        elevatorPos0 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_6);
//        elevatorPos1 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_7);
//        elevatorPos2 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_8);
//        elevatorPos3 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_9);
//        elevatorPos4 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_10);
//        elevatorPos5 = new JoystickButton(operatorJoystick, ATTACK_BUTTON_11);
        
        cameraPos1 = new JoystickButton(cameraJoystick, ATTACK_BUTTON_6);
        cameraPos2 = new JoystickButton(cameraJoystick, ATTACK_BUTTON_7);
        cameraPos3 = new JoystickButton(cameraJoystick, ATTACK_BUTTON_10);
        cameraPos4 = new JoystickButton(cameraJoystick, ATTACK_BUTTON_11);
        
        switchElevator = new JoystickButton(operatorJoystick, ATTACK_BUTTON_5);
        slowSpeed = new JoystickButton(driverController, RIGHT_BUTTON);
        
        Button toggleClaws = new JoystickButton(operatorJoystick, ATTACK_TRIGGER);
//        Button modeFront = new JoystickButton(operatorJoystick, ATTACK_BUTTON_3);
//        Button modeBack = new JoystickButton(operatorJoystick, ATTACK_BUTTON_2);
        Button modeToggle = new JoystickButton(operatorJoystick, ATTACK_BUTTON_3);
        Button extendFisher = new JoystickButton(driverController, Y_BUTTON);
        Button retractFisher = new JoystickButton(driverController, B_BUTTON);
        
        
        //Driver Buttons
        retractFisher.whenPressed(new RetractFisher());
        extendFisher.whenPressed(new ExtendFisher());
        
        
        //Operator Controller
//        modeFront.whenPressed(new PickElevator(MODE_FRONT));
//        modeBack.whenPressed(new PickElevator(MODE_BACK));
        modeToggle.whenPressed(new ToggleElevator());
        toggleClaws.whenPressed(new ToggleClaw());  
        
       
        //camera buttons
        cameraPos1.whenPressed(new MoveCameraCommand(1, .5));
        cameraPos2.whenPressed(new MoveCameraCommand(.75, .5));
        cameraPos3.whenPressed(new MoveCameraCommand(.5, .5));
        cameraPos4.whenPressed(new MoveCameraCommand(.25, .5));
        
        switchElevator.whenPressed(new SwitchElevator());

        
        // SmartDashboard Buttons
        SmartDashboard.putData("UseDrive", new UseDrive());
        SmartDashboard.putData("DriveServos", new DriveServos());
        SmartDashboard.putData("CentreCamera", new CentreCamera());
         
    }
    
    //Periodic method for updating control configurations
    public void configureController(){
    	if((int) Robot.driverController.getSelected() == XBOX){
    		System.out.println("XBOX CONTROLLER");
    		driverRotationAxis = RIGHT_X;
    	}
    	if((int) Robot.driverController.getSelected() == JOYSTICK){
    		System.out.println("JOYSTICK");
    		driverRotationAxis = WINGMAN_Z_AXIS;
    	}
    			
    }
    
    public static boolean getShouldStrafe(){
    	return SmartDashboard.getBoolean("shouldStrafe");
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
    
    public double getOperatorY() {
    	return operatorJoystick.getRawAxis(ATTACK_Y_AXIS);
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
    

	public boolean getAAAOverride() {
		return driverController.getRawButton(RIGHT_BUMPER);
	}
	
	public Button getElevatorPos0() {
		return elevatorPos0;
	}
	
	public Button getElevatorPos1() {
		return elevatorPos1; 
	}
	
	public Button getElevatorPos2() {
		return elevatorPos2;
	}
	
	public Button getElevatorPos3() {
		return elevatorPos3;
	}
	
	public Button getElevatorPos4() {
		return elevatorPos4;
	}
	
	public Button getElevatorPos5() {
		return elevatorPos5;
	}

	public boolean getPIDOverride() {
		return !driverController.getRawButton(LEFT_BUMPER);
	}

	public static int getDirectionFromDashboard() {
		return 1;
		//return (int) Robot.rightOrLeft.getSelected();
	}
	public Button getSlowSpeed(){
		return slowSpeed;
	}
	
	

}

