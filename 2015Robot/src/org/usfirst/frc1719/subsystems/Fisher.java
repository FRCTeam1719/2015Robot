package org.usfirst.frc1719.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

//TODO (CH0NC0) IMPLEMENT TESTABLE

public class Fisher extends Subsystem implements Testable {

	private Solenoid fisherSolenoid;
	private Solenoid fisherAimSolenoid;
	private Relay spike;
	private DigitalInput limitSwitchDown;
	private DigitalInput limitSwitchRet;
	
	public Fisher(Relay par1, DigitalInput par2, DigitalInput par3, Solenoid par4, Solenoid par5) {
		fisherSolenoid = par4;
		fisherAimSolenoid = par5;
	}

	public boolean extend() {
		if(lower()){
			fisherSolenoid.set(true);
			return true;
		}
		fisherAimSolenoid.set(true);
		return false;
	}
	
	public boolean retract() {
		fisherSolenoid.set(false);
		if(limitSwitchRet.get()) {
    		spike.set(Relay.Value.kOff);
    		return true;
    	}
    	
    	spike.set(Relay.Value.kReverse);
    	return false;
	}
	
	public boolean lower(){
		fisherAimSolenoid.set(true);
		return limitSwitchDown.get();
	}
	
	public boolean raise(){
		if(retract()){
			fisherAimSolenoid.set(false);
			return true;
		}
		return false;
	}
	
	@Override
	public void test() {
		while(!extend()){
			System.out.println("Attempting extension");
		}
		System.out.println("Extension complete");
		while(!retract()){
			System.out.println("Attempting retraction");
		}
		System.out.println("Retraction Complete");
		while(!raise()){
			System.out.println("Attempting to raise");
		}
		System.out.println("Raising Complete");
		while(!lower()){
			System.out.println("Attempting to lower");
		}
		System.out.println("Lowering Complete");
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
