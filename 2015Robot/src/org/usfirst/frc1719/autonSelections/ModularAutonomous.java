package org.usfirst.frc1719.autonSelections;

import java.util.ArrayList;

import org.usfirst.frc1719.Robot;
import org.usfirst.frc1719.interfaces.IAutoSelection;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class ModularAutonomous extends CommandGroup implements IAutoSelection {
	
	ArrayList<Command> autoCommands;

	public ModularAutonomous() {		
	
	}
	
	@Override
	public void start() {
		
		autoCommands = new ArrayList<Command>();
		for (SendableChooser itr : Robot.modularAutoActionChoosers) {
			autoCommands.add( (Command) itr.getSelected());
		}
		
		for (Command itr : autoCommands) {
			addSequential( itr);
		}
		super.start();
	}
}
