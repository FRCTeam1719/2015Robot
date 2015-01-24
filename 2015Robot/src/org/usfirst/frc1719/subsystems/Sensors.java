// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1719.subsystems;

import org.usfirst.frc1719.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
//import org.usfirst.frc1719.TEST.commands.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Sensors extends Subsystem implements Testable{
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    DigitalInput limitSwitch = RobotMap.sensorsLimitSwitch;
    Encoder quadratureEncoder1 = RobotMap.sensorsQuadratureEncoder1;
    Encoder quadratureEncoder2 = RobotMap.sensorsQuadratureEncoder2;
    AnalogInput irSensor = RobotMap.sensorsIRSensor;
    Gyro gyro = RobotMap.sensorsGyro;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    double ENCODER_CORRECTION_VALUE = 1.4D;    // Some encoders are off by a factor of 1.4
    double ENCODER_TICKS_PER_REVOLUTION = 360; // For converting Encoder rate to RPM
    double ENCODER_SECONDS_PER_MINUTE = 60;    // For converting Encoder rate to RPM also
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public double getEncoderRate(int index){
    	//returns an impossibly consistent, recognizable value if not working
    	double encoderValue = (-1337.0D);
    	
    	//reads chosen encoder
    	switch (index)
    	{
    	case 1:
    		encoderValue = quadratureEncoder1.getRate();
    		break;
    	case 2:
    		encoderValue = quadratureEncoder2.getRate();
    		break;
    	default:
    		break;
    	}
    	
    	return encoderValue;
    }
    
    public int getIRSensorValue()
    {
    	//returns infrared sensor value
    	return irSensor.getValue();
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public DigitalInput getLimitSwitch() {
    	
    	return limitSwitch;
    }
    
    public Gyro getGyro() {
    	//returns attached gyro so it can be accessed
    	return gyro;
    }

	public double getEncoderRPM(int index) {
		
		//get encoder rate
		double encoderRate = getEncoderRate(index);
		
		//covert to RPM
		double encoderRPM = encoderRate / ENCODER_TICKS_PER_REVOLUTION;
		encoderRPM *= ENCODER_SECONDS_PER_MINUTE;
		
		return encoderRPM;
	}
	
	public double correctEncoderValue(double value) {
		return value * ENCODER_CORRECTION_VALUE;
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}
}

