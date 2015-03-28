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

import java.util.ArrayList;

import org.usfirst.frc1719.RobotMap;
import org.usfirst.frc1719.customSensors.LIDAR;
import org.usfirst.frc1719.customSensors.MB1220UltrasonicAnalog;
import org.usfirst.frc1719.interfaces.ITestable;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * 
 */
public class Sensors extends Subsystem implements ITestable {
	
	public static final int NUM_ENCODERS = 4;
	
	//TODO: get the correct value for this
	public static final double WHEEL_CIRCUMFRENCE_FEET = 1.57;
	
	//TODO: set these to the correct values, for some encoders it will be 256
	public static final double ENC_FRONT_LEFT_TOTAL_PULSES = 360;
	public static final double ENC_FRONT_RIGHT_TOTAL_PULSES = 360;
	public static final double ENC_BACK_LEFT_TOTAL_PULSES = 360;
	public static final double ENC_BACK_RIGHT_TOTAL_PULSES = 360;
	
	
	DigitalInput limitSwitch = RobotMap.sensorsLimitSwitch;
    Encoder encoder10 = RobotMap.sensorsQuadratureEncoder1;
    Encoder quadratureEncoder2 = RobotMap.sensorsQuadratureEncoder2;
    Gyro gyro = RobotMap.sensorsGyro;
    LIDAR lidar = RobotMap.sensorsLIDAR;
    MB1220UltrasonicAnalog ultrasonic = RobotMap.sensorsUltrasonic;
 
    Encoder frontLeftEncoder = RobotMap.frontLeftEncoder;
    Encoder frontRightEncoder = RobotMap.frontRightEncoder;
    Encoder backLeftEncoder = RobotMap.backLeftEncoder;
    Encoder backRightEncoder = RobotMap.backRightEncoder;
    
    ArrayList<Encoder> functioningEncoders;
    
    double ENCODER_CORRECTION_VALUE = 1.4D;    // Some encoders are off by a factor of 1.4
    double ENCODER_TICKS_PER_REVOLUTION = 360; // For converting Encoder rate to RPM
    double ENCODER_SECONDS_PER_MINUTE = 60;    // For converting Encoder rate to RPM also
    
    double distance = 0;
    double speed = 0;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Sensors() {
    	//Set the Correct DPP for each encoder
    	frontLeftEncoder.setDistancePerPulse(WHEEL_CIRCUMFRENCE_FEET / ENC_FRONT_LEFT_TOTAL_PULSES);
    	frontRightEncoder.setDistancePerPulse(WHEEL_CIRCUMFRENCE_FEET / ENC_FRONT_RIGHT_TOTAL_PULSES);
    	backLeftEncoder.setDistancePerPulse(WHEEL_CIRCUMFRENCE_FEET / ENC_BACK_LEFT_TOTAL_PULSES);
    	backRightEncoder.setDistancePerPulse(WHEEL_CIRCUMFRENCE_FEET / ENC_BACK_RIGHT_TOTAL_PULSES);
    	
    	functioningEncoders.add(frontLeftEncoder);
    	functioningEncoders.add(frontRightEncoder);
    	functioningEncoders.add(backLeftEncoder);
    	functioningEncoders.add(backRightEncoder);
    }
    /**
     * 
     * @return the distance sensed in centimeters
     */
    public int getLIDARDistanceCM() {
    	return lidar.getDistance();
    }
    
    public double getLIDARDistanceM() {
    	return ((double) getLIDARDistanceCM()) * 0.01D;
    }
    
    public long getUltrasonicDistanceCM() {
    	return ultrasonic.getDistanceCM();
    }
    
    public double getUltrasonicDistanceM() {
    	return ultrasonic.getDistanceM();
    }
 
    /** NOTE: Does not work with rotation, only works with movement in straight lines
 	*/
    public double getDistance() {
    	
    	double avgDistance = 0;
    	
    	for (Encoder itr : functioningEncoders) {
    		avgDistance += itr.getDistance();
    	}
    	
    	avgDistance /= functioningEncoders.size();
    	
    	return avgDistance;
    }
    
    /** NOTE: Does not work with rotation, only works with movement in straight lines
 	*/
    public double getSpeed() {
    	
    	double avgSpeed = 0;
    	
    	for (Encoder itr : functioningEncoders) {
    		avgSpeed += itr.getRate();
    	}
    	
    	avgSpeed /= functioningEncoders.size();
    	
    	return avgSpeed;
    }
    
    public void resetEncoders() {
    	frontLeftEncoder.reset();
    	frontRightEncoder.reset();
    	backLeftEncoder.reset();
    	backRightEncoder.reset();
    	
    	distance = 0;
    	speed = 0;
    }
    
    public double getEncoderRate(int index){
    	//returns an impossibly consistent, recognizable value if not working
    	double encoderValue = (-1337.0D);
    	
    	//reads chosen encoder
    	switch (index)
    	{
    	case 1:
    		encoderValue = encoder10.getRate();
    		break;
    	case 2:
    		encoderValue = quadratureEncoder2.getRate();
    		break;
    	default:
    		break;
    	}
    	
    	return encoderValue;
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

    public double getEncoder10Distance() {
    	return encoder10.getDistance();
    }
    
    public void resetEncoder10() {
    	encoder10.reset();
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
	
	public double getPotentiometerValue(int index){
		if(index == 1){
			return RobotMap.frontElevatorPot.get();
		}
		if(index == 2){
			return RobotMap.backElevatorPot.get();
		}
		return -1;
	}

	

;	@Override
	public void test() {
		try {
			System.out.println("LIDAR Distance (cm): " + getLIDARDistanceCM());
			System.out.println("Ultrasonic Distance (cm): " + getUltrasonicDistanceCM());
			System.out.println("Encoder speeds (RPM): " + getEncoderRPM(1));
			System.out.println("Potentiometer Values (0 bottom to 100 top): " + getPotentiometerValue(1) + " | " + getPotentiometerValue(2));
			System.out.println("");
		} catch(final Throwable t) {
			System.err.println("SENSOR TEST FAILURE\n" + t.getClass().toString() + ": " + t.getMessage() + "thrown.");
		}
	}

	@Override
	public void reset() {}
	
	public String getName(){
		return "Sensors Test";
	}
}
