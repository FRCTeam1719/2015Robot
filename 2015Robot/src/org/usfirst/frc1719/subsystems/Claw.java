package org.usfirst.frc1719.subsystems;

import org.usfirst.frc1719.RobotMap;
import org.usfirst.frc1719.interfaces.IDisableable;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem implements IDisableable {
	
	public static final boolean CLAW_OPEN = false;
	public static final boolean CLAW_CLOSED = true;
	
	boolean currentState;
	
	Solenoid clawSolenoid;
	
	public Claw(Solenoid solenoid) {
		clawSolenoid = solenoid;
		clawSolenoid.set(CLAW_OPEN);
		currentState = CLAW_OPEN;
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	//Open the claw
	public void open() {
		clawSolenoid.set(CLAW_OPEN);
		currentState = CLAW_OPEN;
	}
	
	public void close() {
		clawSolenoid.set(CLAW_CLOSED);
		currentState = CLAW_CLOSED;
	}
	
	public boolean getState() {
		return currentState;
	}

	@Override
	public void disable() {
		RobotMap.frontClawSolenoid.set(false);
		RobotMap.backClawSolenoid.set(false);
	}

}
