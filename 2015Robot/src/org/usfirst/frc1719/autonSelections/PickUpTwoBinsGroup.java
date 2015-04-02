package org.usfirst.frc1719.autonSelections;

import org.usfirst.frc1719.OI;
import org.usfirst.frc1719.autonomousCommands.CloseBackClaw;
import org.usfirst.frc1719.autonomousCommands.CloseFrontClaw;
import org.usfirst.frc1719.autonomousCommands.Move;
import org.usfirst.frc1719.autonomousCommands.MoveElevator;
import org.usfirst.frc1719.autonomousCommands.Wait;
import org.usfirst.frc1719.interfaces.IAutoSelection;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpTwoBinsGroup extends CommandGroup implements IAutoSelection {

	final double BACKWARD = 0.5D;
	final int UP = 1;
	final double FORWARD = -0.5D;
	final double NIL = 0;

	public PickUpTwoBinsGroup() {
		addSequential(new CloseFrontClaw());
		addSequential(new Wait(5, 1));
		addSequential(new MoveElevator(UP, OI.MODE_FRONT, 35));
		addSequential(new Move(NIL, BACKWARD, NIL, false, 35));
		addSequential(new Move(NIL, NIL, NIL, false, 10));
		addSequential(new CloseBackClaw());
		addSequential(new MoveElevator(UP, OI.MODE_BACK, 35));
		if(OI.getShouldStrafe()){
			addSequential(new Move(-1, NIL, NIL, false, 75));
		}
	}
}
