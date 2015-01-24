package org.usfirst.frc1719.subsystems;

import org.usfirst.frc1719.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class Elevator extends Subsystem implements Testable{
	
	boolean LIMIT_SWITCH_ACTIVATED = false;
	
	boolean testHasStarted = false;
	
	int TOLERANCE_DEGREES = 1;
	int ELEVATOR_POSITION = 0;
	
	public static Relay.Value MOTOR_STATUS_MOVING_UP = Relay.Value.kForward;
	public static Relay.Value MOTOR_STATUS_MOVING_DOWN = Relay.Value.kReverse;
	public static Relay.Value MOTOR_STATUS_STILL = Relay.Value.kOff;
	
	//Limit switches
	DigitalInput limitSwitchTop = RobotMap.elevatorS1;
	DigitalInput limitSwitchBottom = RobotMap.elevatorS2;
	
	//Potentiometer
	//if the 1800 were 360, one turn of the potentiometer would give the full range of voltages,
	//1800 will make it need 5 rotations to go across the full range of voltages.
	Potentiometer pot = new AnalogPotentiometer(0, 1800, 0);
	
	//Motor
	Relay elevatorMotor = new Relay(0);

	@Override
	protected void initDefaultCommand() {
		
		
	}
	
	public void moveUp() {
		//If the limit switch cuts out
		if (limitSwitchTop.get() == LIMIT_SWITCH_ACTIVATED) {
			return;
		}
		
		double potentiometerPos = pot.get();
		//if the position is divisible by 360, the potentiometer is at a full turn,
		//at which point we want to stop the elevator
		if ( ((int) Math.floor(potentiometerPos)) % 360 == 0 ) {
			return;
		}
		
		elevatorMotor.set(MOTOR_STATUS_MOVING_UP);
		
	}
	
	public void moveDown() {
		//If the limit switch cuts out
		if (limitSwitchBottom.get() == LIMIT_SWITCH_ACTIVATED) {
			ELEVATOR_POSITION++;
			return;
		}
		
		elevatorMotor.set(MOTOR_STATUS_MOVING_DOWN);
		
	}
	
	public void setStill() {
		
		//We don't have to worry about tripping a limit switch because we won't be moving
		elevatorMotor.set(MOTOR_STATUS_STILL);
	}
	
	public int getPosition() {
		return ELEVATOR_POSITION;
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}
	
}
