package org.usfirst.frc1719.autonomousCommands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;

public class Move extends Command implements IAutoCommand{

	int iterationNumber;
	int desiredIterations;
	double x;
	double y;
	double rot;
	boolean PID;
	boolean done;
	double NIL = 0;
	
	public Move(double x, double y, double rot, boolean PID, int desiredIterations){
		this.x = x;
		this.y = y;
		this.rot = rot;
		this.PID = PID;
		this.desiredIterations = desiredIterations;
	}
	
	@Override
	protected void initialize() {
		requires(Robot.drive);
		//Reset things
		iterationNumber = 0;
		done = false;
	}

	@Override
	protected void execute() {
		if(iterationNumber <= desiredIterations){
		Robot.drive.moveCartesian(x, y, rot, PID);
		}else{
			done = true;
		}
		iterationNumber++;
		
	}

	@Override
	protected boolean isFinished() {
		return done;
	}

	@Override
	protected void end() {
		//Stop the drive
		Robot.drive.moveCartesian(NIL,NIL,NIL,false);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
