// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1719;
    

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController driveLeftRear;
    public static SpeedController driveRightRear;
    public static SpeedController driveLeftFront;
    public static SpeedController driveRightFront;
    public static RobotDrive driveRobotDrive;
    public static Compressor pneumaticsCompressor;
    public static Solenoid pneumaticsSolenoid1;
    public static Relay motorMotorRelay;
    public static DigitalInput sensorsLimitSwitch;
    public static Encoder sensorsQuadratureEncoder1;
    public static Encoder sensorsQuadratureEncoder2;
    public static AnalogInput sensorsIRSensor;
    public static Servo cameraMountYServo;
    public static Servo cameraMountXServo;
    public static Gyro sensorsGyro;
    public static DigitalInput elevatorS1;
    public static DigitalInput elevatorS2;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveLeftRear = new Talon(0);
        LiveWindow.addActuator("Drive", "Left Rear", (Talon) driveLeftRear);
        
        driveRightRear = new Talon(1);
        LiveWindow.addActuator("Drive", "Right Rear", (Talon) driveRightRear);
        
        driveLeftFront = new Talon(2);
        LiveWindow.addActuator("Drive", "Left Front", (Talon) driveLeftFront);
        
        driveRightFront = new Talon(3);
        LiveWindow.addActuator("Drive", "Right Front", (Talon) driveRightFront);
        
        driveRobotDrive = new RobotDrive(driveLeftFront, driveLeftRear, driveRightFront, driveRightRear);
        
        driveRobotDrive.setSafetyEnabled(true);
        driveRobotDrive.setExpiration(0.1);
        driveRobotDrive.setSensitivity(0.5);
        driveRobotDrive.setMaxOutput(1.0);
        driveRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
        pneumaticsCompressor = new Compressor(0);
        
        pneumaticsSolenoid1 = new Solenoid(0, 0);
        LiveWindow.addActuator("Pneumatics", "Solenoid1", pneumaticsSolenoid1);
        
        motorMotorRelay = new Relay(0);
        LiveWindow.addActuator("Motor", "MotorRelay", motorMotorRelay);
        
        //sensorsLimitSwitch = new DigitalInput(0);
        //LiveWindow.addSensor("Sensors", "LimitSwitch", sensorsLimitSwitch);
        elevatorS1 = new DigitalInput(0);
        elevatorS2 = new DigitalInput(1);
  
        /*
        sensorsQuadratureEncoder1 = new Encoder(1, 2, false, EncodingType.k4X);
        LiveWindow.addSensor("Sensors", "Quadrature Encoder 1", sensorsQuadratureEncoder1);
        sensorsQuadratureEncoder1.setDistancePerPulse(1.0);
        sensorsQuadratureEncoder1.setPIDSourceParameter(PIDSourceParameter.kRate);
        sensorsQuadratureEncoder2 = new Encoder(3, 4, false, EncodingType.k4X);
        LiveWindow.addSensor("Sensors", "Quadrature Encoder 2", sensorsQuadratureEncoder2);
        sensorsQuadratureEncoder2.setDistancePerPulse(1.0);
        sensorsQuadratureEncoder2.setPIDSourceParameter(PIDSourceParameter.kRate);
        */
        
        sensorsIRSensor = new AnalogInput(3);
        LiveWindow.addSensor("Sensors", "IRSensor", sensorsIRSensor);
        
        cameraMountYServo = new Servo(4);
        LiveWindow.addActuator("CameraMount", "YServo", cameraMountYServo);
        
        cameraMountXServo = new Servo(5);
        LiveWindow.addActuator("CameraMount", "XServo", cameraMountXServo);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        //Creates and resets a new gyro
        sensorsGyro = new Gyro(0);
        sensorsGyro.initGyro();
        sensorsGyro.reset();
    }
}
