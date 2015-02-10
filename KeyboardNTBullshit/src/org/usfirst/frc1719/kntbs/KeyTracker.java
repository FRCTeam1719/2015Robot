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
		switch(e.getID()) {
			case KeyEvent.KEY_PRESSED:
				table.putValue("keyflags", ((long) table.getValue("keyflags"))
						| KeyConstants.getCode(e.getKeyCode()));
				break;
			case KeyEvent.KEY_RELEASED:
				table.putValue("keyflags", ((long) table.getValue("keyflags"))
						& ~KeyConstants.getCode(e.getKeyCode()));
				break;
		}
		return false;
	}
}
