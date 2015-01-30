package org.usfirst.frc1719.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public abstract class DualimitedSpike extends Subsystem {
    
	private Relay spike;
	private DigitalInput limitSwitchExt;
	private DigitalInput limitSwitchRet;
	
	/**
	 * Initializes the subsystem
	 * @param par1 the Spike this system is based on.
	 * @param par2 the limit switch marking full extent.
	 * @param par3 the limit switch marking full retraction.
	 */
	public DualimitedSpike(Relay par1, DigitalInput par2, DigitalInput par3) {
		spike = par1;
		limitSwitchExt = par2;
		limitSwitchRet = par3;
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Moves the motor in the 'forward' direction unless 
     * the full extent switch is pressed.
     * @return true if the full extent switch has been reached, false otherwise.
     */
    public boolean extend() {
    	if(limitSwitchExt.get()) {
    		spike.set(Relay.Value.kOff);
    		return true;
    	}
    	
    	spike.set(Relay.Value.kForward);
    	return false;
    }
    
    /**
     * Moves the motor in the 'reverse' direction unless 
     * the full retraction switch is pressed.
     * @return true if the full retraction switch has been reached, false otherwise.
     */
    public boolean retract() {
    	if(limitSwitchRet.get()) {
    		spike.set(Relay.Value.kOff);
    		return true;
    	}
    	
    	spike.set(Relay.Value.kReverse);
    	return false;
    }
    
    //PlzDunkan,
    public void off() {
    	//We don't need to worry about limit switches because the motor won't be moving
    	spike.set(Relay.Value.kOff);
    }
}

