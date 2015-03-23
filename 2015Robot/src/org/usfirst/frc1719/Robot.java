
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
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.usfirst.frc1719.autonSelections.BringObjectsInZone;
import org.usfirst.frc1719.autonSelections.DoNothing;
import org.usfirst.frc1719.autonSelections.GetCtrByDistance;
import org.usfirst.frc1719.autonSelections.GetInZone;
import org.usfirst.frc1719.autonSelections.ModularAutonomous;
import org.usfirst.frc1719.autonSelections.PickupTwoBins;
import org.usfirst.frc1719.autonomousCommands.CloseBackClaw;
import org.usfirst.frc1719.autonomousCommands.CloseFrontClaw;
import org.usfirst.frc1719.autonomousCommands.OpenBackClaw;
import org.usfirst.frc1719.autonomousCommands.OpenFrontClaw;
import org.usfirst.frc1719.autonomousCommands.Wait;
import org.usfirst.frc1719.interfaces.IAutoSelection;
import org.usfirst.frc1719.interfaces.IDisableable;
import org.usfirst.frc1719.interfaces.ITestable;
import org.usfirst.frc1719.subsystems.CameraMount;
import org.usfirst.frc1719.subsystems.Claw;
import org.usfirst.frc1719.subsystems.Claws;
import org.usfirst.frc1719.subsystems.Drive;
import org.usfirst.frc1719.subsystems.Elevator;
import org.usfirst.frc1719.subsystems.Fisher;
import org.usfirst.frc1719.subsystems.Pneumatics;
import org.usfirst.frc1719.subsystems.Sensors;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static int NUM_AUTO_ACTIONS = 10;
	public static int loopIterationNumber = 0;
	public static Robot instance;
	


	public static IAutoSelection autoSelection;
	
	public static SendableChooser autonomousSelectionChooser;
    public static SendableChooser[] modularAutoActionChoosers;
    public static SendableChooser testSubsystemChooser;

    public static OI oi;
    public static Drive drive;
    public static Pneumatics pneumatics;
    public static Sensors sensors;
    public static CameraMount cameraMount;
    public static Fisher fisher;
    public static Elevator frontElevator;
    public static Elevator backElevator;
    public static Claws claws;
    public static Claw frontClaw;
    public static Claw backClaw;
    public static Claw currentClaw;
    public static Elevator currentElevator;
    public static ArrayList<ITestable> devices = new ArrayList<ITestable>();
    public static ArrayList<IDisableable> commands = new ArrayList<IDisableable>();
	public static SendableChooser driverController;
	static boolean MoveElvRunnnig = false;
	public static ITestable[] m = new ITestable[] {};
	public static ITestable[] toTest;
	public static String previousTest;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
    	
        drive = new Drive();
        pneumatics = new Pneumatics();
        sensors = new Sensors();
        cameraMount = new CameraMount();
        claws = new Claws();
        
        frontClaw = new Claw(RobotMap.frontClawSolenoid);
        backClaw = new Claw(RobotMap.backClawSolenoid);
        currentClaw = frontClaw;
        
        frontElevator = new Elevator(OI.MODE_FRONT, RobotMap.frontElevatorPot,
				 RobotMap.frontElevatorMotor,
				 RobotMap.frontElevatorSwitchTop,
				 RobotMap.frontElevatorSwitchBottom);
        backElevator = new Elevator(OI.MODE_BACK, RobotMap.backElevatorPot,
				RobotMap.backElevatorMotor,
				RobotMap.backElevatorSwitchTop,
				RobotMap.backElevatorSwitchBottom);
        
        fisher = new Fisher();
        currentElevator = frontElevator;

        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        oi = new OI();
       
        //All of the autonomous selections
        autonomousSelectionChooser = new SendableChooser();
        autonomousSelectionChooser.addDefault("Do Nothing", new DoNothing());
        autonomousSelectionChooser.addObject("Pick up one Bin", new BringObjectsInZone());
        autonomousSelectionChooser.addObject("Pick up two Bins", new PickupTwoBins());
        autonomousSelectionChooser.addObject("Get Container by distance", new GetCtrByDistance());
        autonomousSelectionChooser.addObject("Move to Auto Zone", new GetInZone());
        autonomousSelectionChooser.addObject("Modular Autonomous", new ModularAutonomous());
        SmartDashboard.putData("Autonomous Mode", autonomousSelectionChooser);
        
        
        modularAutoActionChoosers = new SendableChooser[NUM_AUTO_ACTIONS];
        
        //Put a set of actions for each modular autonomous step
        for (int i = 0; i < NUM_AUTO_ACTIONS; i++) {
        	SmartDashboard.putNumber("Wait Time", 0);
        	modularAutoActionChoosers[i].addDefault("Do Nothing", new DoNothing());
        	modularAutoActionChoosers[i].addObject("Close Back Claw", new CloseBackClaw());
        	modularAutoActionChoosers[i].addObject("Open Back Claw", new OpenBackClaw());
        	modularAutoActionChoosers[i].addObject("Close Front Claw", new CloseFrontClaw());
        	modularAutoActionChoosers[i].addObject("Open Front Claw", new OpenFrontClaw());
        	modularAutoActionChoosers[i].addObject("Wait", new Wait(SmartDashboard.getNumber("Wait Time") ));
        	SmartDashboard.putData("Modular Action " + i, modularAutoActionChoosers[i]);
        }

        SmartDashboard.putNumber("KP", 45.0D);
        SmartDashboard.putNumber("KI", 0.001D);
        SmartDashboard.putNumber("KD", 5.0D);
        
        driverController = new SendableChooser();
        driverController.addDefault("XBox controller", 0);
        driverController.addObject("3-axis joystick", 1);
        SmartDashboard.putData("Driver controller type", driverController);
        setUpTests();
        SmartDashboard.putBoolean("Avoid Accidents", true);
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	for (IDisableable itr : commands) {
    		itr.disable();
    	}
 
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	System.out.println("AUTON INIT");
        // schedule the autonomous command (example)
        setAutoCommandFromDashboard();
        autoSelection.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	System.out.println("AUTON PERIODIC");
    	double x = Robot.sensors.getGyro().getAngle() / 360.0D;
    	if(Math.abs(x) <= 0.5D) Robot.cameraMount.setXServoRaw(0.5D - x);
    	loopIterationNumber++;
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autoSelection != null) autoSelection.cancel();
        System.out.println("CONFIGURE CONTROLLERS");
        oi.configureController();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	loopIterationNumber++; 
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	loopIterationNumber++;
    	//We don't know what this does, but it breaks things
        //LiveWindow.run();
    	String currentTest = String.valueOf(testSubsystemChooser.getSelected());
    	if(currentTest!=previousTest){
    		testCleanUp();
    	}
    	((ITestable) testSubsystemChooser.getSelected()).test();
    	previousTest= currentTest;
    }
    public static void testCleanUp(){
    	for(int i = 0; i < toTest.length; i++) {
    		toTest[i].reset();
    	}
    }
    
    @Override
    public void testInit() {
    	if (autoSelection != null) autoSelection.cancel();
    	toTest = devices.toArray(m);
    	for(int i = 0; i < toTest.length; i++) {
    		toTest[i].reset();
    	}
    	//Adds radio button to choose which subsystem to test
        testSubsystemChooser = new SendableChooser();
        //Adds a default test option that does nothing
        testSubsystemChooser.addDefault("Do Nothing", new ITestable(){public void test(){Robot.testCleanUp();} public void reset(){} public String getName(){return "Do Nothing";}});
        for(int i = 0; i < toTest.length; i++) {
    		testSubsystemChooser.addObject(toTest[i].getName(), toTest[i]);
    	}
        SmartDashboard.putData("Test Subsystem", testSubsystemChooser);
        
        LiveWindow.setEnabled(false);
    }
    
    //returns the number of loops the robot has gone through
    public static int getLoopIterationNumber() {
    	return loopIterationNumber;
    }
    

    
    private void setUpTests() {
    	Field[] f = Robot.class.getDeclaredFields();
    	for(int i = 0; i < f.length; i++) {
    		if(!Modifier.isStatic(f[i].getModifiers())) continue;
    		try {
				Object o = f[i].get(null);
				if(o instanceof ITestable) devices.add((ITestable) o);
			} catch (IllegalArgumentException | IllegalAccessException e) {throw new RuntimeException(e);}
    	}
    }
    
    public static void setRunningProcess(boolean x){
    	MoveElvRunnnig = x;
    }
    
    public static boolean  isMoveElvRunning(){
    	return MoveElvRunnnig;
    }
    
    public static void switchElevator(int whichElevator) {
    	
    	if (whichElevator == OI.MODE_FRONT) {
    		currentElevator = frontElevator;
    		currentClaw = frontClaw;
    	}
    	else if (whichElevator == OI.MODE_BACK) {
    		currentElevator = backElevator;
    		currentClaw = backClaw;
    	}
    	else {
    		System.out.println("WRONG ELEVATOR");
    	}
    }
    
    public static void setAutoCommandFromDashboard() {
    	autoSelection = (IAutoSelection) autonomousSelectionChooser.getSelected();
    }
    
    //Commands called 50 times per second
    public static double getSeconds() {
    	return loopIterationNumber / 50;
    }

}


