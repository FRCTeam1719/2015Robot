package org.usfirst.frc1719.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class Elevator extends Subsystem{
	
	boolean LIMIT_SWITCH_ACTIVATED = false;
	
	Relay.Value MOTOR_STATUS_MOVING_UP = Relay.Value.kForward;
	Relay.Value MOTOR_STATUS_MOVING_DOWN = Relay.Value.kReverse;
	Relay.Value MOTOR_STATUS_STILL = Relay.Value.kOff;
	
	//Limit switches
	DigitalInput limitSwitchTop = new DigitalInput(0);
	DigitalInput limitSwitchBottom = new DigitalInput(0);
	
	//Potentiometer
	Potentiometer pot = new AnalogPotentiometer(0, 36, 0);
	
	//Motor
	Relay elevatorMotor = new Relay(0);

	@Override
	protected void initDefaultCommand() {
		
		
	}
	
	void moveUp() {
		if (limitSwitchTop.get() == LIMIT_SWITCH_ACTIVATED) {
			
			return;
		}
		
		if (pot)
		elevatorMotor.set(MOTOR_STATUS_MOVING_UP);
	}
	
	void moveDown() {
		if (limitSwitchBottom.get() == LIMIT_SWITCH_ACTIVATED) {
			return;
		}
	}
	
}
