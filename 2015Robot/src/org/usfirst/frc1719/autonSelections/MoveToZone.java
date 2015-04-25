package org.usfirst.frc1719.autonSelections;

import org.usfirst.frc1719.autonomousCommands.Move;
import org.usfirst.frc1719.interfaces.IAutoSelection;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveToZone extends CommandGroup implements IAutoSelection{

	final int RIGHT = 1;
	final int LEFT = -1;
	final int NIL = 0;
	
	public MoveToZone(){
		addSequential(new Move(LEFT,NIL,NIL, false, 90));
	}
	
}
