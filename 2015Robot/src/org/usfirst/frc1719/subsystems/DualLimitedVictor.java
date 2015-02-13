package org.usfirst.frc1719.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DualLimitedVictor extends Subsystem {
    
	
	private Victor victor;
	private DigitalInput limitForward;
	private DigitalInput limitBackward;
	
	private double absoluteSpeed;
	
	public DualLimitedVictor (Victor victor, DigitalInput limitForward, DigitalInput limitBackward) {
		this.victor = victor;
		this.limitForward = limitForward;
		this.limitBackward = limitBackward;
		
		absoluteSpeed = 1;
	}
	
	//Spin the motor forward
	public void forward() {
		
		//If the limit switch is off or pressed
		if (limitForward.get()) {
			System.out.println("FORWARD LIMIT");
			//Turn the motor off and exit
			still();
			return;
		}
		
		victor.set(1);
		System.out.println("MOVING UP @: " + absoluteSpeed);
	}
	
	//Spin the motor backward
	public void backward() {
		
		//If the limit switch is off or pressed
		if (limitBackward.get()) {
			System.out.println("LIMIT BACK");
			//Turn the motor off and exit
			still();
			return;
		}
		
		victor.set(-1);
		System.out.println("GOING BACKWARD @: " + -absoluteSpeed);
	}
	
	public void setSpeed(double speed) {
		if (speed < 0 || speed > 1) {
			System.out.println("ERR: Invalid Speed");
			return;
		}
		
		absoluteSpeed = speed;
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //Turn the motor off
    public void still() {
    	victor.set(0);
    }
    
    public boolean getLimitSwitchForwardVal() {
    	return limitForward.get();
    }
    
    public boolean getLimitSwitchBackwardVal() {
    	return limitBackward.get();
    }
    
    
}

