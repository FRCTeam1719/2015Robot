package org.usfirst.frc1719.kntbs;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class KeyTracker implements KeyEventDispatcher {

	private NetworkTable table;
	
	public KeyTracker(NetworkTable par1) {
		table = par1;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
	    long flags = Long.valueOf(table.getString("keyflags"), 0x10);
		switch(e.getID()) {
			case KeyEvent.KEY_PRESSED:
			    flags |= KeyConstants.getCode(e.getKeyCode());
				break;
			case KeyEvent.KEY_RELEASED:
				flags &= ~KeyConstants.getCode(e.getKeyCode());
				break;
		}
		table.putString("keyflags", Long.toHexString(flags));
		return false;
	}
}
