package org.usfirst.frc1719.kntbs;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class RIOKeys {
	private static NetworkTable table = NetworkTable.getTable("SmartDashboard");
	public static boolean isKeyPressed(int hid) {
		return (Double.doubleToRawLongBits(table.getNumber("keyflags")) & KeyConstants.getCode(hid)) != 0;
	}
}
