package org.usfirst.frc1719.kntbs;

import java.awt.event.KeyEvent;

public class KeyConstants {
	//Numerals
	private static final long _0 = 0x0000000000000001L;
	private static final long _1 = 0x0000000000000002L;
	private static final long _2 = 0x0000000000000004L;
	private static final long _3 = 0x0000000000000008L;
	private static final long _4 = 0x0000000000000010L;
	private static final long _5 = 0x0000000000000020L;
	private static final long _6 = 0x0000000000000040L;
	private static final long _7 = 0x0000000000000080L;
	private static final long _8 = 0x0000000000000100L;
	private static final long _9 = 0x0000000000000200L;
	
	//Letters
	private static final long A = 0x0000000000000400L;
	private static final long B = 0x0000000000000800L;
	private static final long C = 0x0000000000001000L;
	private static final long D = 0x0000000000002000L;
	private static final long E = 0x0000000000004000L;
	private static final long F = 0x0000000000008000L;
	private static final long G = 0x0000000000010000L;
	private static final long H = 0x0000000000020000L;
	private static final long I = 0x0000000000040000L;
	private static final long J = 0x0000000000080000L;
	private static final long K = 0x0000000000100000L;
	private static final long L = 0x0000000000200000L;
	private static final long M = 0x0000000000400000L;
	private static final long N = 0x0000000000800000L;
	private static final long O = 0x0000000001000000L;
	private static final long P = 0x0000000002000000L;
	private static final long Q = 0x0000000004000000L;
	private static final long R = 0x0000000008000000L;
	private static final long S = 0x0000000010000000L;
	private static final long T = 0x0000000020000000L;
	private static final long U = 0x0000000040000000L;
	private static final long V = 0x0000000080000000L;
	private static final long W = 0x0000000100000000L;
	private static final long X = 0x0000000200000000L;
	private static final long Y = 0x0000000400000000L;
	private static final long Z = 0x0000000800000000L;
	
	//Meta-keys
	private static final long SHIFT = 0x0000001000000000L;
	private static final long CTRL = 0x0000002000000000L;
	private static final long ALT = 0x0000004000000000L;
	
	//Punctuation
	private static final long COMMA = 0x0000008000000000L;
	private static final long DOT = 0x0000010000000000L;
	private static final long SLSH = 0x0000020000000000L;
	private static final long SCLN = 0x0000040000000000L;
	private static final long QUOT = 0x0000080000000000L;
	private static final long DASH = 0x0000100000000000L;
	private static final long EQLS = 0x0000200000000000L;
	private static final long OBKT = 0x0000400000000000L;
	private static final long CBKT = 0x0000800000000000L;
	private static final long BSSH = 0x0001000000000000L;
	
	private static final long ESC = 0x0002000000000000L;
	
	public static long getCode(int hid) {
		long key = 0x00000000;
		switch (hid) {
			case '0':
			case KeyEvent.VK_NUMPAD0:
				key = _0;
				break;
			case '1':
			case KeyEvent.VK_NUMPAD1:
				key = _1;
				break;
			case '2':
			case KeyEvent.VK_NUMPAD2:
				key = _2;
				break;
			case '3':
			case KeyEvent.VK_NUMPAD3:
				key = _3;
				break;
			case '4':
			case KeyEvent.VK_NUMPAD4:
				key = _4;
				break;
			case '5':
			case KeyEvent.VK_NUMPAD5:
				key = _5;
				break;
			case '6':
			case KeyEvent.VK_NUMPAD6:
				key = _6;
				break;
			case '7':
			case KeyEvent.VK_NUMPAD7:
				key = _7;
				break;
			case '8':
			case KeyEvent.VK_NUMPAD8:
				key = _8;
				break;
			case '9':
			case KeyEvent.VK_NUMPAD9:
				key = _9;
				break;
			case 'A':
				key = A;
				break;
			case 'B':
				key = B;
				break;
			case 'C':
				key = C;
				break;
			case 'D':
				key = D;
				break;
			case 'E':
				key = E;
				break;
			case 'F':
				key = F;
				break;
			case 'G':
				key = G;
				break;
			case 'H':
				key = H;
				break;
			case 'I':
				key = I;
				break;
			case 'J':
				key = J;
				break;
			case 'K':
				key = K;
				break;
			case 'L':
				key = L;
				break;
			case 'M':
				key = M;
				break;
			case 'N':
				key = N;
				break;
			case 'O':
				key = O;
				break;
			case 'P':
				key = P;
				break;
			case 'Q':
				key = Q;
				break;
			case 'R':
				key = R;
				break;
			case 'S':
				key = S;
				break;
			case 'T':
				key = T;
				break;
			case 'U':
				key = U;
				break;
			case 'V':
				key = V;
				break;
			case 'W':
				key = W;
				break;
			case 'X':
				key = X;
				break;
			case 'Y':
				key = Y;
				break;
			case 'Z':
				key = Z;
				break;
			case KeyEvent.VK_ALT:
				key = ALT;
				break;
			case KeyEvent.VK_CONTROL:
				key = CTRL;
				break;
			case KeyEvent.VK_SHIFT:
				key = SHIFT;
				break;
			case KeyEvent.VK_COMMA:
				key = COMMA;
				break;
			case KeyEvent.VK_PERIOD:
				key = DOT;
				break;
			case KeyEvent.VK_SLASH:
				key = SLSH;
				break;
			case KeyEvent.VK_SEMICOLON:
				key = SCLN;
				break;
			case KeyEvent.VK_QUOTE:
				key = QUOT;
				break;
			case KeyEvent.VK_MINUS:
				key = DASH;
				break;
			case KeyEvent.VK_EQUALS:
				key = EQLS;
				break;
			case KeyEvent.VK_OPEN_BRACKET:
				key = OBKT;
				break;
			case KeyEvent.VK_CLOSE_BRACKET:
				key = CBKT;
				break;
			case KeyEvent.VK_BACK_SLASH:
				key = BSSH;
				break;
			case KeyEvent.VK_ESCAPE:
				key = ESC;
				break;
			default:
				key = 0L;
				break;
		}
		return key;
	}
}