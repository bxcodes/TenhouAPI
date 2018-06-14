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
	
	public static int[] decodeM(int val) {
		int fromWho = val & 0x3;
		
		if((val & 0x4) != 0) {
			// CHI
	        int t0 = (val >> 3) & 0x3;
	        int t1 = (val >> 5) & 0x3;
	        int t2 = (val >> 7) & 0x3;
	        int baseAndCalled = val >> 10;
	        int base = (int) Math.floor(baseAndCalled / 3);

	        base = (int) (Math.floor(base / 7) * 9 + base % 7);
	        int tile0 = t0 + 4 * (base + 0);
	        int tile1 = t1 + 4 * (base + 1);
	        int tile2 = t2 + 4 * (base + 2);

	        return new int[] {tile0, tile1, tile2};
		} else if((val & 0x18) != 0) {
			int t4 = (val >> 5) & 0x3;
			int t0 = new int[]{1, 0, 0, 0}[t4];
			int t1 = new int[]{2, 2, 1, 1}[t4];
			int t2 = new int[]{3, 3, 3, 2}[t4];
			int baseAndCalled = val >> 9;
			int base = (int) Math.floor(baseAndCalled / 3);

	        if ((val & 0x8) != 0) {
	        	int tile0 = t0 + 4 * base;
	        	int tile1 = t1 + 4 * base;
	        	int tile2 = t2 + 4 * base;

	            return new int[]{tile0, tile1, tile2};
	        } else {
	        	int tile0 = t0 + 4 * base;
	        	int tile1 = t1 + 4 * base;
	        	int tile2 = t2 + 4 * base;
	        	int tile3 = t4 + 4 * base;

	            return new int[]{tile0, tile1, tile2, tile3};
	        }
		} else if((val & 0x20) != 0) {
			
		} else {
			int baseAndCalled = val >> 8;
			int base = (int) Math.floor(baseAndCalled / 4);

	        int tile0 = 4 * base;
	        int tile1 = 4 * base + 1;
	        int tile2 = 4 * base + 2;
	        int tile3 = 4 * base + 3;

	        return new int[]{tile0, tile1, tile2, tile3};
	    
		}
		
		
		
		
		return null;
	}
}
