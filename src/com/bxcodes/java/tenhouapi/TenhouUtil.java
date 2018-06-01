package com.bxcodes.java.tenhouapi;

public class TenhouUtil {
	
	public static String tileString(int t) {
		t /= 4;
		switch(t / 9) {
		case 0:return 1 + t % 9 + "m";
		case 1:return 1 + t % 9 + "p";
		case 2:return 1 + t % 9 + "s";
		case 3:return 1 + t % 9 + "z";
		default: return "";
		}
	}
	
	public static String decodeAuth(String auth) {
		int[] table = new int[] {
			63006, 9570, 49216, 45888,
	        9822, 23121, 59830, 51114,
	        54831, 4189, 580, 5203,
	        42174, 59972, 55457, 59009,
	        59347, 64456, 8673, 52710,
	        49975, 2006, 62677, 3463,
	        17754, 5357,
		};
		
		String p1 = auth.split("-")[0];
		String p2 = auth.split("-")[1];
		int index = Integer.parseInt("2" + p1.substring(2, 8)) % (12 - Integer.parseInt(p1.substring(7, 8))) * 2;
		int a = table[index] ^ Integer.parseInt(p2.substring(0, 4), 16);
		int b = table[index + 1] ^ Integer.parseInt(p2.substring(4, 8), 16);
		String postfix = Integer.toHexString(a) + Integer.toHexString(b);
		String result = p1 + "-" + postfix;
		
		
		return result;
	}
}
