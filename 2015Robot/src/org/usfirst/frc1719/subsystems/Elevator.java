package org.usfirst.frc1719.subsystems;

//import org.usfirst.frc1719.Robot;


import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem /*implements ITestable */{
	
	//the Pot gives a value from 0 to 1, multiplied by this
	public static int POTENTIOMETER_SCALE_FACTOR = 100;
	public static int PERCENT_PER_LEVEL = 10;
	public static double POTENTIOMETER_TOLERANCE = .5;
	
	//Constants for elevator
	public static int ELEVATOR_FRONT = 0;
	public static int ELEVATOR_BACK = 1;
	//Potentiometer constants, MIN is the elevator's bottom, MAX means the elevator is at the top
	public static double POTENTIOMETER_MIN = 0.0D;
	public static double POTENTIOMETER_MAX = 100.0D;
	
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
	
	/*
	 * Variables used for testing
	 */
	int testStage = 0;
	//Used for timing
	double startingIterationNumber; //The robot's loopIteration number when the test starts
	double timePassed = 0;
	boolean testFinished = false;
	
	
	public Elevator(int elevatorNum,
					AnalogPotentiometer elevatorPot, 
					Victor elevatorVictor,
					DigitalInput limitSwitchTop,
					DigitalInput limitSwitchBottom) {
		
		elevatorMotor = new DualLimitedVictor(elevatorVictor, limitSwitchTop, limitSwitchBottom);
		this.elevatorPot = elevatorPot;
		
		determineElevatorPos();
		
		elevatorMotor.setSpeed(0.5);
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new UseElevator());
	}
	
	//Moves elevator up in steps
	public void moveUp() {
				
		determineElevatorPos();
		
		//Extend moves it up
		elevatorMotor.backward();
		elevatorIsMoving = true;		
	}
	
	//Move elevator down in steps
	public void moveDown() {
		
		determineElevatorPos();
				
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

	/*@Override
	public void test() {
		
		if (testFinished) {
			return;
		}
		
		//This stage moves the elevator to the tippity top
		if (testStage == 0) {
			
			//If the elevator is at the bottom, move to the next stage
			if (elevatorMotor.getLimitSwitchForwardVal()) {
				
				System.out.println("No value From Limit Switch");
				
				setStill(); //We probably don't need this, but redundancy is good
				testStage++; //Move to the next stage

			}
			
			//If it isn't at the top, move up
			else {
				moveUp();
			}
			
			//Exit so that no other stages get run
			return;
		}
		
		
		//This stage steps the elevator halfway
		else if (testStage == 1) {
			
			//If we have hit the position
			if (atPotPos(2)) {
				System.out.println("At potentiometer position");
				
				//Get how much time has passed since the last time the motor was moving
				//in this stage
				timePassed = (Robot.getLoopIterationNumber() - startingIterationNumber) / 100;
				
				//If less than half a second has passed
				if (timePassed < .25) {
					setStill();
				}
				//If more than half a second has passed
				else {
					//Move to the next stage, and exit
					testStage++; 
					return;
				}
			}
			
			//If we are still moving down
			else {
				moveDown();
				
				//Get the starting potentiometer position, if this condition isn't
				//true on the next loop, then the elevator is at pos 2, and we don't
				//want this to be updated
				startingIterationNumber = Robot.getLoopIterationNumber();
				timePassed = 0;
			}
		}
		
		else if (testStage == 2) {
			
			//If the elevator is at the bottom
			if (elevatorMotor.getLimitSwitchBackwardVal()) {
				setStill();
				
				//Report and move to the next stage
				System.out.println("No value from limit switch");
				
				testStage++;
			}
			
			//If the elevator isn't at the bottom, move down
			else {
				moveDown();
			}
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
		return "Elevator Test";
	}*/
	
	public int getElevatorPos() {
		return elevatorPos;
	}

}
