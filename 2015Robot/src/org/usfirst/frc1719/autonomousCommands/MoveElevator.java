package org.usfirst.frc1719.autonomousCommands;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator extends Command implements IAutoCommand {

	public final int SET_STILL = 0;
	//TODO Switch move up and move down for production robot
	public final int MOVE_UP = 1;
	public final int MOVE_DOWN = 2;
	int direction;
	int elevator;
	boolean done;
	int iterationNumber;
	int time;

	public MoveElevator(int direction, int elevator, int time) {
		requires(Robot.frontElevator);
		requires(Robot.backElevator);
		requires(Robot.currentElevator);
		this.direction = direction;
		this.elevator = elevator;
		this.time = time;
	}

	@Override
	protected void initialize() {
		// Reset things
		iterationNumber = 0;
		done = false;
		// Use the correct elevator
		Robot.switchElevator(elevator);
	}

	@Override
	protected void execute() {
		if (iterationNumber < time) {
			if (direction == SET_STILL) {
				Robot.currentElevator.setStill();
			} else if (direction == MOVE_UP) {
				Robot.currentElevator.moveUp();
			} else if (direction == MOVE_DOWN) {
				Robot.currentElevator.moveDown();
			}
		} else {
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
		Robot.currentElevator.setStill();

	}

	@Override
	protected void interrupted() {
		Robot.currentElevator.setStill();

	}

}
