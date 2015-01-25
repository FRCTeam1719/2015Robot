package org.usfirst.frc1719.subsystems;

import org.usfirst.frc1719.RobotMap;
import org.usfirst.frc1719.commands.UseElevator;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem implements Testable {
	
	public static boolean LIMIT_SWITCH_ACTIVATED = true;
	public static int POTENTIOMETER_SCALE_FACTOR = 3600;
	public static int POTENTIOMETER_DEGREES_PER_TURN = 360;
	public static int POTENTIOMETER_TOLERANCE = 20;
	public static double DESIRED_POT_POS = 500;
	public static int ELEVATOR_BACK  = 0;
	public static int ELEVATOR_FRONT = 1;
	
	public static double POTENTIOMETER_MIN = 0.0D;
	public static double POTENTIOMETER_MAX = 3610D;
	
	private double potPos = 0;
	
	private int elevatorNum;
	public static Relay.Value MOTOR_STATUS_MOVING_UP = Relay.Value.kForward;
	public static Relay.Value MOTOR_STATUS_MOVING_DOWN = Relay.Value.kReverse;
	public static Relay.Value MOTOR_STATUS_STILL = Relay.Value.kOff;

	DigitalInput limitSwitchTop;;
	DigitalInput limitSwitchBottom;
	AnalogPotentiometer elevatorPot;
	Relay elevatorMotor;
	
	public Elevator(int elevatorNum) {
		this.elevatorNum = elevatorNum;
		
		if (elevatorNum == ELEVATOR_BACK) {
			limitSwitchTop = RobotMap.backElevatorSwitchTop;
			limitSwitchBottom = RobotMap.backElevatorSwitchBottom;
			elevatorPot = RobotMap.backElevatorPot;
			elevatorMotor = RobotMap.backElevatorMotor;
		}
		
		else if (elevatorNum == ELEVATOR_FRONT) {
			limitSwitchTop = RobotMap.frontElevatorSwitchTop;
			limitSwitchBottom = RobotMap.frontElevatorSwitchBottom;
			elevatorPot = RobotMap.frontElevatorPot;
			elevatorMotor = RobotMap.frontElevatorMotor;
		}
		
	}

	@Override
	protected void initDefaultCommand() {
		if (elevatorNum == ELEVATOR_FRONT) {
			setDefaultCommand(new UseElevator(ELEVATOR_FRONT));
		}
		if (elevatorNum == ELEVATOR_BACK) {
			setDefaultCommand(new UseElevator(ELEVATOR_BACK));
		}
	}
	
	public boolean moveUp() {
		
		//If the limit switch cuts out
		if (atTop() ) {
			setStill();
			return false;
		}
		
		potPos = elevatorPot.get();
		
		//If the potentiometer position is within the desired range
		if (potPos < DESIRED_POT_POS + 50 && potPos >  (DESIRED_POT_POS)) {
			setStill();
			return false;
		}
		
		elevatorMotor.set(MOTOR_STATUS_MOVING_UP);
		return true;
		
	}
	
	public boolean moveDown() {
		
		//If the limit switch's limit is reached
		if (atBottom()) {
			setStill();
			return false;
		}				
		
		potPos = elevatorPot.get();
		
		//If the potentiometer is within the correct range
		if (potPos > DESIRED_POT_POS && potPos < (DESIRED_POT_POS + 50)) {
			setStill();
			return false;
		}

		elevatorMotor.set(MOTOR_STATUS_MOVING_DOWN);
		
		return true;
	}
	
	private boolean atTop() {
		return limitSwitchTop.get() == LIMIT_SWITCH_ACTIVATED;
	}

	public boolean atBottom() {
		return limitSwitchBottom.get() == LIMIT_SWITCH_ACTIVATED;
	}
	
	public void setStill() {
		//We don't have to worry about tripping a limit switch because we won't be moving
		elevatorMotor.set(MOTOR_STATUS_STILL);
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
		
		double percent = (POTENTIOMETER_MAX / potPos) * 100;
		
		return percent;
	}

}
