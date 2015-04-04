package org.usfirst.frc1719.commands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IDisableable;
import org.usfirst.frc1719.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UseElevator extends Command implements IDisableable {
	
	public static final double Y_AXIS_TOLERANCE = 0.1D;

	double joystickY = 0.0D;
	double desiredPotPos = 0;
	double currentPotPos = 0;
	boolean movingToPos = false;
	Elevator elevator;

	public UseElevator() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.frontElevator);
		requires(Robot.backElevator);

		elevator = Robot.currentElevator;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.commands.add(this);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		joystickY = Robot.oi.getOperatorY();
		
		//This is in case the elevator gets toggled
		elevator = Robot.currentElevator;
		
		currentPotPos = elevator.getPotPos();
		System.out.println(currentPotPos);
		
		SmartDashboard.putNumber("PotPos", currentPotPos);
		// If the axis is within the tolerance, don't move
		if (Math.abs(joystickY) < Y_AXIS_TOLERANCE) {
			elevator.setSpeed(.5);
				
			if (Robot.oi.getElevatorPos0().get() && elevator == Robot.frontElevator) {
				desiredPotPos = 63;
				movingToPos = true;
			}
//			else if (Robot.oi.getElevatorPos1().get()) {
//				desiredPotPos = Elevator.POTENTIOMETER_POS[1];
//				movingToPos = true;
//			}
//			else if (Robot.oi.getElevatorPos2().get()) {
//				desiredPotPos = Elevator.POTENTIOMETER_POS[2];
//				movingToPos = true;
//			}
//			else if (Robot.oi.getElevatorPos3().get()) {
//				desiredPotPos = Elevator.POTENTIOMETER_POS[3];
//				movingToPos = true;
//			}
//			else if (Robot.oi.getElevatorPos4().get()) {
//				desiredPotPos = Elevator.POTENTIOMETER_POS[4];
//				movingToPos = true;
//			}
//			else if (Robot.oi.getElevatorPos5().get()) {
//				desiredPotPos = Elevator.POTENTIOMETER_POS[5];
//				movingToPos = true;
//			}
			else if (!movingToPos){
				elevator.setStill();
				desiredPotPos = currentPotPos;
			}
			
		}
		else {
			movingToPos = false;
			
			elevator.setSpeed(Math.abs(joystickY) - Y_AXIS_TOLERANCE);
			
			// User is pulling up
			if (joystickY > 0) {
				
				//The elevator will always move up
				desiredPotPos = 200;
			}

			// User is pulling down
			else if (joystickY < 0) {
				
				//The elevator will always move down
				desiredPotPos = -200;
			}

			// User isn't moving the joystick
			else if (joystickY == 0) {
				desiredPotPos = currentPotPos;
			}

		}
		if (elevator.atPotPos(desiredPotPos)) {
			movingToPos = false;
			elevator.setStill();
		}
		else if (currentPotPos < desiredPotPos) {
			System.out.println("moving down");
			elevator.moveDown();
		}
		else if (currentPotPos > desiredPotPos) {
			System.out.println("moving up");
			elevator.moveUp();
		}
		
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.commands.remove(this);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	@Override
	public void disable() {
		elevator.setSpeed(0);
		elevator.setStill();
		desiredPotPos = currentPotPos;
	}
}
