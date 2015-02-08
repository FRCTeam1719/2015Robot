
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
import org.usfirst.frc1719.autonomous.GetCtrByDistance;
import org.usfirst.frc1719.autonomous.GetInZone;
import org.usfirst.frc1719.autonomous.ICommandOption;
import org.usfirst.frc1719.commands.AutonomousCommand;
import org.usfirst.frc1719.subsystems.CameraMount;
import org.usfirst.frc1719.subsystems.Claws;
import org.usfirst.frc1719.subsystems.Drive;
import org.usfirst.frc1719.subsystems.Elevator;
import org.usfirst.frc1719.subsystems.Fisher;
import org.usfirst.frc1719.subsystems.Pneumatics;
import org.usfirst.frc1719.subsystems.Sensors;
import org.usfirst.frc1719.subsystems.ITestable;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
	private static int loopIterationNumber = 0;
	public static Robot instance;

	public static enum EnumAutoCMD {
		GCBD("Get containers using distance sensor", new GetCtrByDistance()),
		ZONE("Go to the Auto Zone and stop", new GetInZone()),
		NULL("Do nothing", new ICommandOption() {public boolean done() {return true;} public void doCMD() {}});
		
		final String name;
		public final ICommandOption cmd;
		
		EnumAutoCMD(String par1, ICommandOption par2) {
			name = par1;
			cmd = par2;
		}
	}
	
	//Declares a new sendable chooser for autonomous command
    Command autonomousCommand;
    public static SendableChooser autoCMDChooser;

    public static OI oi;
    public static Drive drive;
    public static Pneumatics pneumatics;
    public static Sensors sensors;
    public static CameraMount cameraMount;
    public static Fisher fisher;
    public static Elevator frontElevator;
    public static Elevator backElevator;
    public static Claws claws;
    public static Elevator currentElevator;
    public ArrayList<ITestable> devices = new ArrayList<ITestable>();
	public static SendableChooser driverController;

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

        
       // backElevator = new Elevator(Elevator.ELEVATOR_BACK);
        frontElevator = new Elevator(Elevator.ELEVATOR_FRONT, RobotMap.frontElevatorPot,
				 RobotMap.frontElevatorMotor,
				 RobotMap.frontElevatorSwitchTop,
				 RobotMap.frontElevatorSwitchBottom);

        backElevator = new Elevator(Elevator.ELEVATOR_BACK, RobotMap.backElevatorPot,
				RobotMap.backElevatorMotor,
				RobotMap.backElevatorSwitchTop,
				RobotMap.backElevatorSwitchBottom);
        fisher = new Fisher(RobotMap.fisherSpike, RobotMap.fisherLowered,
        		RobotMap.fisherRetraction, RobotMap.fisherSolenoid, 
        		RobotMap.fisherAimSolenoid);
        currentElevator = frontElevator;

        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousCommand();

        
        //Adds radio button to choose autonomous command
        
        autoCMDChooser = new SendableChooser();
        for(EnumAutoCMD cmd : EnumAutoCMD.values()) {
        	autoCMDChooser.addObject(cmd.name, cmd);
        }
        SmartDashboard.putData("Autonomous Style", autoCMDChooser);
        
        SmartDashboard.putNumber("KP", 45.0D);
        SmartDashboard.putNumber("KI", 0.001D);
        SmartDashboard.putNumber("KD", 5.0D);
        
        driverController = new SendableChooser();
        driverController.addObject("XBox controller", 0);
        driverController.addDefault("3-axis joystick", 1);
        SmartDashboard.putData("Driver controller type", driverController);
        setUpTests();
        SmartDashboard.putBoolean("Avoid Accidents", true);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
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
        if (autonomousCommand != null) autonomousCommand.cancel();
        oi.oiPeriodic();
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
    	ITestable[] m = new ITestable[] {};
    	ITestable[] toTest = devices.toArray(m);
    	for(int i = 0; i < toTest.length; i++) {
    		toTest[i].test();
    	}
    }
    
    @Override
    public void testInit() {
    	ITestable[] m = new ITestable[] {};
    	ITestable[] toTest = devices.toArray(m);
    	for(int i = 0; i < toTest.length; i++) {
    		toTest[i].reset();
    	}
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

}
