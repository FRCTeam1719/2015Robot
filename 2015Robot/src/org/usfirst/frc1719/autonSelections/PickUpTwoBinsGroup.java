package org.usfirst.frc1719.autonSelections;

import org.usfirst.frc1719.OI;
import org.usfirst.frc1719.autonomousCommands.CloseBackClaw;
import org.usfirst.frc1719.autonomousCommands.CloseFrontClaw;
import org.usfirst.frc1719.autonomousCommands.Move;
import org.usfirst.frc1719.autonomousCommands.MoveElevator;
import org.usfirst.frc1719.interfaces.IAutoSelection;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpTwoBinsGroup extends CommandGroup implements IAutoSelection {

	final double BACKWARD = 1;
	final int UP = 1;
	final double FORWARD = -1;
	final double NIL = 0;

	public PickUpTwoBinsGroup() {
		addSequential(new CloseFrontClaw());
		addSequential(new MoveElevator(UP, OI.MODE_FRONT, 300));
		addSequential(new Move(BACKWARD, NIL, NIL, false, 300));
		addSequential(new CloseBackClaw());
		addSequential(new MoveElevator(UP, OI.MODE_BACK, 300));
	}
}
