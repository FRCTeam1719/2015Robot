package org.usfirst.frc1719.subsystems;

import org.usfirst.frc1719.Robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

public class Elevator extends DualimitedSpike implements Testable {
	
	//the Pot gives a value from 0 to 1, multiplied by this
	public static int POTENTIOMETER_SCALE_FACTOR = 100;
	public static int PERCENT_PER_LEVEL = 10;
	public static int POTENTIOMETER_TOLERANCE = 3;
	
	//For distinguishing which elevator to control
	public static int ELEVATOR_BACK  = 0;
	public static int ELEVATOR_FRONT = 1;
	
	//Potentiometer constants, MIN is the elevator's bottom, MAX means the elevator is at the top
	public static double POTENTIOMETER_MIN = 0.0D;
	public static double POTENTIOMETER_MAX = 100.0D;
	
	//Array of all of the positions for the potentiometer
	public static double POTENTIOMETER_POS[] = new double[] {
		0.0D,
		20.0D,
		40.0D,
		60.0D,
		80.0D,
		100.0D
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
	
	/*
	 * Variables used for testing
	 */
	boolean testCompletedInit = false;
	boolean movingUp = true;
	//Used for timing
	int startingIterationNumber; //The robot's loopIteration number when the test starts
	int loopIterationNumber; //Our program will loop 100 times per second
	boolean startPausing = true;
	
	public Elevator(int elevatorNum,
					AnalogPotentiometer elevatorPot, 
					Relay elevatorSpike,
					DigitalInput limitSwitchTop,
					DigitalInput limitSwitchBottom) {
		super(elevatorSpike, limitSwitchTop, limitSwitchBottom);
		
		this.elevatorPot = elevatorPot;
		
	}

	@Override
	public void initDefaultCommand() {
	}
	
	//Moves elevator up in steps
	public void moveUp() {
				
		determineElevatorPos();
		
		//Extend moves it up
		forwards();
		elevatorIsMoving = true;		
	}
	
	//Move elevator down in steps
	public void moveDown() {
		
		determineElevatorPos();
				
		//Retract moves it down
		backwards();
		elevatorIsMoving = true;
	}
	
	//Stops elevator movement
	public void setStill() {
		System.out.println("SETTING STILL");
		//We don't have to worry about tripping a limit switch because we won't be moving
		off();
		elevatorIsMoving = false;
	}

		
	
	public double getPotPos() {
		potPos = elevatorPot.get();
		
		return potPos;
	}
	
	public double getPotPerc() {
		potPos = elevatorPot.get();
		
		double percent = (potPos / POTENTIOMETER_MAX) * 100;
		
		return percent;
	}
	
	public double getPotLevel(){
		double level = getPotPerc() / PERCENT_PER_LEVEL;
		
		return level;
	}

	//Whether the elevator is at the pot pos or not
	public boolean atPotPos(int pos) {
	
		double perc = getPotPerc();
		
		if (Math.abs(POTENTIOMETER_POS[pos] - perc) < POTENTIOMETER_TOLERANCE) {
			System.out.println("AT POS");
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
		while(!testCompletedInit) {
			if (getLimitSwitchRetVal()) {
				testCompletedInit = true;
				startingIterationNumber = Robot.getLoopIterationNumber();
			}
			else {
				moveUp();
			}
		}
		while(!testCompletedInit) {
			if (getLimitSwitchRetVal()) {
				testCompletedInit = true;
				startingIterationNumber = Robot.getLoopIterationNumber();
			}
			else {
				moveDown();
			}
			
			return; //Don't do anything until the elevator is at the bottom
		}
	}
	
	
	public int getElevatorPos() {
		return elevatorPos;
	}
	
}
