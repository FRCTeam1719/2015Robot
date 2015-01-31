package org.usfirst.frc1719.subsystems;

import org.usfirst.frc1719.commands.UseElevator;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

public class Elevator extends DualimitedSpike implements Testable {
	
	//the Pot gives a value from 0 to 1, multiplied by this
	public static int POTENTIOMETER_SCALE_FACTOR = 100;
	
	public static int POTENTIOMETER_TOLERANCE = 3;
	
	//For distinguishing which elevator to control
	public static int ELEVATOR_BACK  = 0;
	public static int ELEVATOR_FRONT = 1;
	
	//Potentiometer constants, MIN is the elevator's bottom, MAX means the elevator is at the top
	public static double POTENTIOMETER_MIN = 0.0D;
	public static double POTENTIOMETER_MAX = 100D;
	
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
	
	//Whether the elevator is moving freely or in steps
	private int moveType = ELEVATOR_STILL;
	
	//Direction of the elevator
	private int moveDirection = ELEVATOR_STILL;
	
	private int elevatorNum;
	
	AnalogPotentiometer elevatorPot;
	
	DualimitedSpike elevatorMotor;
	
	public Elevator(int elevatorNum,
					AnalogPotentiometer elevatorPot, 
					Relay elevatorSpike,
					DigitalInput limitSwitchTop,
					DigitalInput limitSwitchBottom) {
		super(elevatorSpike, limitSwitchTop, limitSwitchBottom);
		
		this.elevatorPot = elevatorPot;
		this.elevatorNum = elevatorNum;
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new UseElevator(elevatorNum) );
	}
	
	//Moves elevator up in steps
	public boolean moveUp() {
		
		//If the potentiometer position is within the desired range
		if (atPotPos()) {
			setStill();
			return false;
		}
		
		
		//Extend moves it up
		elevatorMotor.extend();
		elevatorIsMoving = true;
		moveType = MOVE_TYPE_STEP;
		moveDirection = MOVE_DIRECTION_UP;
		return true;
		
	}
	
	//Move elevator down in steps
	public boolean moveDown() {
				
		//If the potentiometer is within the correct range
		if (atPotPos()) {
			setStill();
			return false;
		}
		
		//Retract moves it down
		elevatorMotor.retract();
		moveType = MOVE_TYPE_STEP;
		moveDirection = MOVE_DIRECTION_DOWN;
		elevatorIsMoving = true;
		
		return true;
	}
	
	//Moves elevator up freely
	public void moveFreeUp() {
		
		elevatorMotor.extend();
		moveType = MOVE_TYPE_FREE;
		moveDirection = MOVE_DIRECTION_UP;
		elevatorIsMoving = true;
	}
	
	//Moves elevator down freely
	public void moveFreeDown() {
		
		elevatorMotor.retract();
		moveType = MOVE_TYPE_FREE;
		moveDirection = MOVE_DIRECTION_DOWN;
		elevatorIsMoving = true;
	}
	
	//Stops elevator movement
	public void setStill() {
		
		//We don't have to worry about tripping a limit switch because we won't be moving
		elevatorMotor.off();
		moveType = ELEVATOR_STILL;
		moveDirection = ELEVATOR_STILL;
		elevatorIsMoving = false;
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
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

	//Whether the elevator is at the
	public boolean atPotPos() {
	
		int perc = (int) (getPotPerc());
		
		if ((perc % 10) < POTENTIOMETER_TOLERANCE) {
			return true;
		}
		
		//If it isn't
		return false;
	}
	
	//Whether the elevator is moving or not
	public boolean isMoving() {
		return elevatorIsMoving;
	}
	
	//Whether the elevator is moving in free mode or step mode
	public int getMoveType() {
		return moveType;
	}
	
	//Gets the direction of the elevator's movement
	public int getDirection() {
		return moveDirection;
	}
}
