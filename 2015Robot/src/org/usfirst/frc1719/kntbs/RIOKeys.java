package org.usfirst.frc1719.kntbs;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class RIOKeys {
	private static NetworkTable table = NetworkTable.getTable("SmartDashboard");
	public static boolean isKeyPressed(int hid) {
		return (Long.valueOf(table.getString("keyflags"), 0x10) & KeyConstants.getCode(hid)) != 0;
	}
}
