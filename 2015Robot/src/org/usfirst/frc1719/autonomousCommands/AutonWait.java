package org.usfirst.frc1719.autonomousCommands;

import org.usfirst.frc1719.commands.Wait;
import org.usfirst.frc1719.interfaces.IAutoCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**Wait for a certain amount of seconds **/
public class AutonWait extends Command implements IAutoCommand {
	
	public AutonWait(int actionNum) {
		this.actionNum = actionNum;
	}
	
	int actionNum;
	
	Wait waitCommand;
	
	

	
	@Override
	protected void end() {
	}

	//Do nothing
	@Override
	protected void execute() {
	}

	@Override
	protected void initialize() {
		waitCommand = new Wait(SmartDashboard.getNumber("Wait Time " + actionNum));
		waitCommand.start();
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		return waitCommand.finished();
	}


}
