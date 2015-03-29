package org.usfirst.frc1719.autonSelections;

import org.usfirst.frc1719.OI;
import org.usfirst.frc1719.autonomousCommands.CloseFrontClaw;
import org.usfirst.frc1719.autonomousCommands.MoveElevator;
import org.usfirst.frc1719.interfaces.IAutoSelection;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickupOneBin extends CommandGroup implements IAutoSelection{

	final int DESIRED_TIME = 50;
	
	public PickupOneBin(){
		addSequential(new CloseFrontClaw());
		addSequential(new MoveElevator(1, OI.MODE_FRONT, DESIRED_TIME));
	}
	
	
}
