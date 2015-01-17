package org.usfirst.frc1719.autonomous;

public interface ICommandOption {
	/**
	 * Called repeatedly during autonomous when selected.
	 */
	public void doCMD();
	/**
	 * Checks whether the command is finished running.
	 * @return true if calls should cease, false otherwise.
	 */
	public boolean done();
}
