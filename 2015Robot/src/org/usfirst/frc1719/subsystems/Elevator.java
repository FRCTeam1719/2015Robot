package org.usfirst.frc1719.subsystems;

import org.usfirst.frc1719.commands.UseElevator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class Elevator extends DualimitedSpike implements Testable {
	
	public static int POTENTIOMETER_SCALE_FACTOR = 100;
	public static int POTENTIOMETER_TOLERANCE = 3;
	public static int ELEVATOR_BACK  = 0;
	public static int ELEVATOR_FRONT = 1;
	
	public static double POTENTIOMETER_MIN = 0.0D;
	public static double POTENTIOMETER_MAX = 100D;
	
	private double potPos = 0;
	
	private int elevatorNum;
	DigitalInput limitSwitchTop;;
	DigitalInput limitSwitchBottom;
	Potentiometer elevatorPot;
	DualimitedSpike elevatorMotor;
	
	public Elevator(Potentiometer elevatorPot, 
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
		
		return true;
	}
	
	public void setStill() {
		//We don't have to worry about tripping a limit switch because we won't be moving
		elevatorMotor.off();
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

	public boolean atPotPos() {
	
		int perc = (int) (getPotPerc());
		
		if ((perc % 10) < 2) {
			return true;
		}
		
		//If it isn't
		return false;
	}
}
