package com.bxcodes.java.tenhouapi;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		TenhouClient client = TenhouClient.getClient();
		client.sendString("<HELO name=\"NoName\" tid=\"f0\" sx=\"M\" />\0");
		
	}

}
