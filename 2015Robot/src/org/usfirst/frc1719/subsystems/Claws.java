package org.usfirst.frc1719.subsystems;

import org.usfirst.frc1719.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Claws extends Subsystem{
	final int FRONT_CLAW = 1;
	final int BACK_CLAW = 2;
	final boolean OPEN = false;
	final boolean CLOSED = true;
	boolean frontStatus = OPEN;
	boolean backStatus = OPEN;
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

}
