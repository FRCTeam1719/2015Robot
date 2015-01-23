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

import org.usfirst.frc1719.autonomous.*;
import org.usfirst.frc1719.commands.AutonomousCommand;
import org.usfirst.frc1719.subsystems.CameraMount;
import org.usfirst.frc1719.subsystems.Drive;
import org.usfirst.frc1719.subsystems.Motor;
import org.usfirst.frc1719.subsystems.Pneumatics;
import org.usfirst.frc1719.subsystems.Sensors;

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

	public static enum EnumAutoCMD {
		GCBD("Get containers using distance sensor", new GetCtrByDistance());
		
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
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drive drive;
    public static Pneumatics pneumatics;
    public static Motor motor;
    public static Sensors sensors;
    public static CameraMount cameraMount;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drive = new Drive();
        pneumatics = new Pneumatics();
        motor = new Motor();
        sensors = new Sensors();
        cameraMount = new CameraMount();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        //Adds radio button to choose autonomous command
        autoCMDChooser = new SendableChooser();
        for(EnumAutoCMD cmd : EnumAutoCMD.values()) {
        	autoCMDChooser.addObject(cmd.name, cmd);
        }
        SmartDashboard.putData("Autonomous Style", autoCMDChooser);
        
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
    	loopIterationNumber++;
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
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
    }
    
    //returns the number of loops the robot has gone through
    public static int getLoopIterationNumber() {
    	return loopIterationNumber;
    }
}
