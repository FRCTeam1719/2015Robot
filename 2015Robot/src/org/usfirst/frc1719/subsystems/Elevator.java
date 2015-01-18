package org.usfirst.frc1719.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class Elevator extends Subsystem{
	
	boolean LIMIT_SWITCH_ACTIVATED = false;
	
	boolean testHasStarted = false;
	
	int TOLERANCE_DEGREES = 1;
	int ELEVATOR_POSITION = 0;
	
	Relay.Value MOTOR_STATUS_MOVING_UP = Relay.Value.kForward;
	Relay.Value MOTOR_STATUS_MOVING_DOWN = Relay.Value.kReverse;
	Relay.Value MOTOR_STATUS_STILL = Relay.Value.kOff;
	
	//Limit switches
	DigitalInput limitSwitchTop = new DigitalInput(0);
	DigitalInput limitSwitchBottom = new DigitalInput(0);
	
	//Potentiometer
	//if the 72 were 360, one turn of the potentiometer would give the full range of voltages,
	//72 will make it 1/5 of that, so 5 turns will give us the full scale of voltages
	Potentiometer pot = new AnalogPotentiometer(0, 72, 0);
	
	//Motor
	Relay elevatorMotor = new Relay(0);

	@Override
	protected void initDefaultCommand() {
		
		
	}
	
	void moveUp() {
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
	
	void moveDown() {
		//If the limit switch cuts out
		if (limitSwitchBottom.get() == LIMIT_SWITCH_ACTIVATED) {
			ELEVATOR_POSITION++;
			return;
		}
		
		double potentiometerPos = pot.get();
		//if the position is divisible by 360, the potentiometer is at a full turn,
		//at which point we want to stop the elevator
		if ( ((int) Math.floor(potentiometerPos)) % 360 == 0 ) {
			ELEVATOR_POSITION--;
			return;
		}
		
		elevatorMotor.set(MOTOR_STATUS_MOVING_DOWN);
		
	}
	
	void test() {
		boolean movingUp;
		if (ELEVATOR_POSITION != 0 && !testHasStarted) {
			moveDown();
			return;
		}
		else {
			testHasStarted = true;
			movingUp = true;
		}
		
		//If the elevator is supposed to move up
		if (movingUp && ELEVATOR_POSITION < 5) {
			moveUp();			
		}
		
		//If the elevator is at the top
		else if (ELEVATOR_POSITION >= 5) {
			movingUp = false;
		}
		
		//If the elevator is moving down
		else if (!(movingUp) && ELEVATOR_POSITION > 0) {
			moveDown();
		}		
		
	}
	
}
