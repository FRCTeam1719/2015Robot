package org.usfirst.frc1719.subsystems;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.RobotMap;
import org.usfirst.frc1719.interfaces.ITestable;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Claws extends Subsystem implements ITestable {
	final int FRONT_CLAW = 1;
	final int BACK_CLAW = 2;
	final boolean OPEN = false;
	final boolean CLOSED = true;
	boolean frontStatus = OPEN;
	boolean backStatus = OPEN;
	
	/* Test variables */
	private int stage = 0;
	private boolean testIsFinished = false;
	private int startLoopIterationNumber;
	private int currentLoopIterationNumber;
	
	public void toggleClaw(int claw){
		
		if(claw==FRONT_CLAW){
			RobotMap.frontClawSolenoid.set(!frontStatus);
			frontStatus = !frontStatus;
		}else if (claw==BACK_CLAW){
			RobotMap.backClawSolenoid.set(!backStatus);
			backStatus = !backStatus;
		}
	}
	
	
	
	@Override
	protected void initDefaultCommand() {
		
	}



	@Override
	public void test() {
		
		if (testIsFinished) {
			return;
		}
		
		//Close front claw
		if (stage == 0) {
			
			RobotMap.frontClawSolenoid.set(true);
			startLoopIterationNumber = Robot.getLoopIterationNumber() + 1;
			stage++;
			
			return;
		}
		
		//Wait half a second, if half a second has passed, open it again
		else if (stage == 1) {
			
			currentLoopIterationNumber = Robot.getLoopIterationNumber();
			
			//If half a second has passed
			if (currentLoopIterationNumber - startLoopIterationNumber >= 50) {
				RobotMap.frontClawSolenoid.set(false);
				stage++;
			}
			
			return;
		}
		
		//Close back claw
		else if (stage == 2) {
			
			RobotMap.backClawSolenoid.set(true);
			startLoopIterationNumber = Robot.getLoopIterationNumber() + 1;
			stage++;
			
			return;
		}
		
		//Wait half a second, if half a second has passed, open it again
		else if (stage == 3) {
			
			currentLoopIterationNumber = Robot.getLoopIterationNumber();
			
			if (currentLoopIterationNumber - startLoopIterationNumber >= 50) {
				RobotMap.backClawSolenoid.set(false);
				stage++;
			}
			
			return;
		}
		
		else {
			testIsFinished = true;
		}
	}



	@Override
	public void reset() {
		
		stage = 0;
		testIsFinished = false;
		
		RobotMap.frontClawSolenoid.set(false);
		RobotMap.backClawSolenoid.set(false);
		
	}
	
	@Override
	public String getName() {
		return "Claws Test";
	}

}
