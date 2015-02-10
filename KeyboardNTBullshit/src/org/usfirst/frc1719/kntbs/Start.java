package org.usfirst.frc1719.kntbs;

import java.awt.KeyboardFocusManager;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Start {

	public static void main(String[] args) throws InterruptedException {
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("roboRIO-1719.local");
		NetworkTable table = NetworkTable.getTable("SmartDashboard");
		table.putValue("keyflags", 0x0000000000000000L);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyTracker(table));
		Start.class.wait();
	}

}
