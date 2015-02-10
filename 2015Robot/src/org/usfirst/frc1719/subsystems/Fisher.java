package org.usfirst.frc1719.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Fisher extends Subsystem /*implements ITestable*/ {

	private Solenoid fisherSolenoid;
	private Solenoid fisherAimSolenoid;
	private Relay spike;
	private DigitalInput limitSwitchDown;
	private DigitalInput limitSwitchRet;
	//private int stage = 0;
	
	public Fisher(Relay par1, DigitalInput par2, DigitalInput par3, Solenoid par4, Solenoid par5) {
		spike = par1;
		limitSwitchDown = par2;
		limitSwitchRet = par3;
		fisherSolenoid = par4;
		fisherAimSolenoid = par5;
	}
	
	//extends only if the arm is lowered, otherwise lowers the arm
	public boolean extend() {
		if(lower()){
			fisherSolenoid.set(true);
			return true;
		}
		fisherAimSolenoid.set(true);
		return false;
	}
	
	//Retract the fisher until the limit switch tells it is fully retracted
	public boolean retract() {
		fisherSolenoid.set(false);
		if(limitSwitchRet.get()) {
    		spike.set(Relay.Value.kOff);
    		return true;
    	}
    	spike.set(Relay.Value.kReverse);
    	return false;
	}
	
	//Lowers the arm, returns true only once fully lowered
	public boolean lower(){
		System.out.println("Lowering");
		fisherAimSolenoid.set(true);
		return limitSwitchDown.get();
	}
	
	//raises, but only if arm is fully retracted, otherwise retracts the arm
	public boolean raise(){
		System.out.println("Raising");
		if(retract()){
			fisherAimSolenoid.set(false);
			return true;
		}
		return false;
	}
	
	/*@Override
	//to test this class
	public void test() {
		try {
			switch(stage) {
				case 0:
					if(extend()) stage++;
					else break;
				case 1:
					if(retract()) stage++;
					else break;
				case 2:
					if(raise()) stage++;
					else break;
				case 3:
					if(lower()) stage++;
					break;
			}
		} catch(final Throwable t) {
			System.err.println("FISHER TEST FAILURE\n" + t.getClass().toString() + ": " + t.getMessage()
					+ "thrown while running FISHER.test()");
		}
	}

	@Override
	public void reset() {
		stage = 0;
	}
	
	@Override
	public String getName(){
		return "Fisher Test";
	}*/
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
