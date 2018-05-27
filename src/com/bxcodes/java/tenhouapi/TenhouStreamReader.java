package com.bxcodes.java.tenhouapi;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TenhouStreamReader {
	
	public TenhouStreamReader(InputStream in) {
		dis = new DataInputStream(in);
		sb = new StringBuilder();
	}
	
	public String nextLine() throws IOException {
		sb.setLength(0);
		
		while(true) {
			char c = (char)dis.readByte();
			if(c == 0) break;
			sb.append(c);
		}
		
		return sb.toString();
	}
	
	private DataInputStream dis;
	private StringBuilder sb;
}
