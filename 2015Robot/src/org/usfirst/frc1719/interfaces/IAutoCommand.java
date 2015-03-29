package org.usfirst.frc1719.interfaces;

public interface IAutoCommand {
	
	//Make sure that any object that implements this is a command
	public void start();
	public void cancel();
}