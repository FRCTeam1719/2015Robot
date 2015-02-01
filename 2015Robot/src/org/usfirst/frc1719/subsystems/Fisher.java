package org.usfirst.frc1719.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;

public class Fisher extends DualimitedSpike {

	private Solenoid fisherSolenoid;
	
	public Fisher(Relay par1, DigitalInput par2, DigitalInput par3, Solenoid par4) {
		super(par1, par2, par3);
		fisherSolenoid = par4;
	}

	@Override
	public boolean forwards() {
		fisherSolenoid.set(true);
		return super.forwards();
	}
	
	public boolean store() {
		if(backwards()) {
			fisherSolenoid.set(false);
			return true;
		} else return false;
	}
}
