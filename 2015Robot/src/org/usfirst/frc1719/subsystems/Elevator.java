package org.usfirst.frc1719.subsystems;

//import org.usfirst.frc1719.Robot;


import org.usfirst.frc1719.OI;
import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.commands.UseElevator;
import org.usfirst.frc1719.interfaces.ITestable;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem implements ITestable {
	
	//the Pot gives a value from 0 to 1, multiplied by this
	public static int POTENTIOMETER_SCALE_FACTOR = 100;
	public static double POTENTIOMETER_TOLERANCE = .5;
	
	//Potentiometer constants, MIN is the elevator's bottom, MAX means the elevator is at the top
	public static double POTENTIOMETER_MIN = 0.0D;
	public static double POTENTIOMETER_MAX = 100.0D;
	
	public static double limitBottomPotPos;
	public static double limitTopPotPos;
	
	//Array of all of the positions for the potentiometer
	public static double POTENTIOMETER_POS[] = new double[] {
		97.5D,
		94.0D,
		76.3D,
		64.3D,
		52.3D,
		32.0D
	};
	
	//Which position the elevator is at 
	private int elevatorPos = 0;
	
	//Whether the elevator is moving in free mode or step
	public static int MOVE_TYPE_FREE = 1;
	public static int MOVE_TYPE_STEP = -1;
	
	//The direction that the elevator is moving
	public static int MOVE_DIRECTION_UP = 1;
	public static int MOVE_DIRECTION_DOWN = -1;
	
	//Indicates that the elevator isn't moving at all
	public static int ELEVATOR_STILL = 0;
	
	//Potentiometer's position
	private double potPos = 0;
	
	//Whether the elevator is moving or not
	private boolean elevatorIsMoving = false;
		
	//Potentiometer
	AnalogPotentiometer elevatorPot;
	
	//Motor
	DualLimitedVictor elevatorMotor;
	
	int elevatorNum;
	
	/*
	 * Variables used for testing
	 */
	int testStage = 0;
	//Used for timing
	int startingIterationNumber; //The robot's loopIteration number when the test starts
	int currentIterationNumber;
	double timePassed = 0;
	boolean testFinished = false;
	
	
	public Elevator(int elevatorNum,
					AnalogPotentiometer elevatorPot, 
					Victor elevatorVictor,
					DigitalInput limitSwitchTop,
					DigitalInput limitSwitchBottom) {
		
		elevatorMotor = new DualLimitedVictor(elevatorVictor, limitSwitchTop, limitSwitchBottom);
		this.elevatorPot = elevatorPot;
		this.elevatorNum = elevatorNum;
		
		determineElevatorPos();
		
		elevatorMotor.setSpeed(0.5);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new UseElevator());
	}
	
	//Moves elevator up in steps
	public void moveDown() {
		
		System.out.println("MOVING DOWN");
		
		//Extend moves it up
		elevatorMotor.backward();
		elevatorIsMoving = true;		
	}
	
	//Move elevator down in steps
	public void moveUp() {
		
		System.out.println("MOVING UP");
 						
		//Retract moves it down
		elevatorMotor.forward();
		elevatorIsMoving = true;
	}
	
	//Stops elevator movement
	public void setStill() {
		//We don't have to worry about tripping a limit switch because we won't be moving
		elevatorMotor.still();
		elevatorIsMoving = false;
	}
	
	public void setSpeed(double speed) {
		
		if (speed < 0 || speed > 1) {
			return;
		}
		
		elevatorMotor.setSpeed(speed);
		
	}

		
	
	public double getPotPos() {
		potPos = elevatorPot.get();
		
		return potPos;
	}
	

	//Whether the elevator is at the pot pos or not
	public boolean atPotPos(double goalPos) {
	
		potPos = getPotPos();
		
		if (Math.abs(goalPos - potPos) < POTENTIOMETER_TOLERANCE) {
			return true;
		}
		
		//If it isn't
		return false;
	}
	
	void determineElevatorPos() {
		potPos = getPotPos();
		
		for (int i = 0; i < POTENTIOMETER_POS.length; i++) {
			if (atPotPos(i)) {
				elevatorPos = i;
				return;
			}
		}
	}
	
	//Whether the elevator is moving or not
	public boolean isMoving() {
		return elevatorIsMoving;
	}

	@Override
	public void test() {
		
		elevatorMotor.setSpeed(.5);
		
		if (testFinished) {
			setStill();
			return;
		}
		
		if (testStage == 0) {
			
			if (elevatorMotor.getLimitSwitchBackwardVal()) {
				setStill();
				testStage++;
			}
			else {
				moveDown();
			}
			
			return;
		}
		
		else if (testStage == 1) {
			
			if (elevatorMotor.getLimitSwitchForwardVal()) {
				setStill();
				testStage++;
			}
			else {
				moveUp();
			}
		}
		
		else if (testStage == 2) {
			
			if (atPotPos(50)) {
				startingIterationNumber = Robot.getLoopIterationNumber() + 1;
				setStill();
				testStage++;
			}
			else {
				moveDown();
			}
			
			return;
		}
		
		//Wait half a second
		else if (testStage == 3) {
			currentIterationNumber = Robot.getLoopIterationNumber();
			
			//If 50 loops have passed
			if (currentIterationNumber - startingIterationNumber >= 50) {
				testStage++;
			}
			else {
				System.out.println("PAUSE");
				setStill();
			}
			
			return;
		}
		
		//Move to bottom limit switch again
		else if (testStage == 4) {
			
			if (elevatorMotor.getLimitSwitchBackwardVal()) {
				testStage++;
				setStill();
			}
			else {
				moveDown();
			}
			
			return;
		}
		
		else {
			setStill();
			testFinished = true;
		}
	}

	@Override
	public void reset() {
		startingIterationNumber = 0;
		timePassed = 0;
		testStage = 0;
		testFinished = false;
	}
	
	@Override
	public String getName(){
		if (elevatorNum == OI.MODE_FRONT) {
			return "Front Elevator Test";
		}
		else if (elevatorNum == OI.MODE_BACK) {
			return "Back Elevator Test";
		}
		else {
			return "Elevator SOMETHING IS WRONG HERE";
		}
	}
	
	public int getElevatorPos() {
		return elevatorPos;
	}

}
