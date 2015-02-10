package org.usfirst.frc1719.kntbs;

import java.awt.KeyboardFocusManager;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Start {

	public static void main(String[] args) throws InterruptedException {
		(new javax.swing.JFrame()).setVisible(true); //Make a blank window so the user knows this is active
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("roboRIO-1719.local");
		NetworkTable table = NetworkTable.getTable("SmartDashboard");
		table.putValue("keyflags", 0x0000000000000000L);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyTracker(table));
	}

}
