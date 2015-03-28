package org.usfirst.frc1719.autonSelections;

import org.usfirst.frc1719.interfaces.IAutoCommand;
import org.usfirst.frc1719.interfaces.IAutoSelection;

import edu.wpi.first.wpilibj.command.Command;

public class DoNothing extends Command implements IAutoSelection, IAutoCommand {
	

	@Override
	protected void end() {
		
	}

	@Override
	protected void execute() {
		
		
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void interrupted() {
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
