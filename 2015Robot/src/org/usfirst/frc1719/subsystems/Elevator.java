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
	
	public static int ELEVATOR_MOVING_FREE = 1;
	public static int ELEVATOR_MOVING_STEP = -1;
	public static int ELEVATOR_STILL = 0;
	
	//Potentiometer's position
	private double potPos = 0;
	
	//Whether the elevator is moving or not
	private boolean elevatorIsMoving = false;
	
	//Whether the elevator is moving freely or in steps
	private int moveType = ELEVATOR_STILL;
	
	private int elevatorNum;
	AnalogPotentiometer elevatorPot;
	DualimitedSpike elevatorMotor;
	
	public Elevator(AnalogPotentiometer elevatorPot, 
					Relay elevatorSpike,
					DigitalInput limitSwitchTop,
					DigitalInput limitSwitchBottom) {
		super(elevatorSpike, limitSwitchTop, limitSwitchBottom);
		
		this.elevatorPot = elevatorPot;
	}

	@Override
	public void initDefaultCommand() {
		if (elevatorNum == ELEVATOR_FRONT) {
			setDefaultCommand(new UseElevator(ELEVATOR_FRONT));
		}
		if (elevatorNum == ELEVATOR_BACK) {
			setDefaultCommand(new UseElevator(ELEVATOR_BACK));
		}
	}
	
	//Moves elevator up
	public boolean moveUp() {
		
		//If the potentiometer position is within the desired range
		if (atPotPos()) {
			setStill();
			return false;
		}
		
		
		//Extend moves it up
		elevatorMotor.extend();
		elevatorIsMoving = true;
		return true;
		
	}
	
	//Moves elevator down
	public boolean moveDown() {
				
		//If the potentiometer is within the correct range
		if (atPotPos()) {
			setStill();
			return false;
		}
		
		//Retract moves it down
		elevatorMotor.retract();
		elevatorIsMoving = true;
		
		return true;
	}
	
	void moveFreeUp() {
		
		elevatorMotor.extend();
		elevatorIsMoving = true;
	}
	
	void moveFreeDown() {
		
		elevatorMotor.retract();
		elevatorIsMoving = true;
	}
	
	public void setStill() {
		
		//We don't have to worry about tripping a limit switch because we won't be moving
		elevatorMotor.off();
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
}
