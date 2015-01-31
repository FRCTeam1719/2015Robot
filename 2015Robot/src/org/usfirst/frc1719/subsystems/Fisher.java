package org.usfirst.frc1719.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;

//TODO (CH0NC0) IMPLEMENT TESTABLE

public class Fisher extends DualimitedSpike implements Testable {

	private Solenoid fisherSolenoid;
	private Solenoid fisherGearboxSolenoid;
	
	public Fisher(Relay par1, DigitalInput par2, DigitalInput par3, Solenoid par4, Solenoid par5) {
		super(par1, par2, par3);
		fisherSolenoid = par4;
		fisherGearboxSolenoid = par5;
	}

	@Override
	public boolean extend() {
		fisherSolenoid.set(true);
		fisherGearboxSolenoid.set(false);
		return true;
	}
	
	@Override
	public boolean retract() {
		fisherGearboxSolenoid.set(true);
		if(super.retract()) {
			fisherSolenoid.set(false);
			return true;
		} else return false;
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
	}
}
