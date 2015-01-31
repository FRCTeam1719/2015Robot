package org.usfirst.frc1719.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;

//TODO (CH0NC0) IMPLEMENT TESTABLE

public class Fisher extends DualimitedSpike {

	private Solenoid fisherSolenoid;
	
	public Fisher(Relay par1, DigitalInput par2, DigitalInput par3, Solenoid par4) {
		super(par1, par2, par3);
		fisherSolenoid = par4;
	}

	@Override
	public boolean extend() {
		fisherSolenoid.set(true);
		return super.extend();
	}
	
	public boolean store() {
		if(retract()) {
			fisherSolenoid.set(false);
			return true;
		} else return false;
	}
}
