package com.bxcodes.java.tenhouapi;

import java.io.File;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		//TenhouClient client = TenhouClient.getClient();
		//client.sendString("<HELO name=\"NoName\" tid=\"f0\" sx=\"M\" />\0");
		
		File f = new File("e1.mjv.xml");
		System.out.println(f.exists());
		
		TenhouLog log = TenhouLog.getLog(f);
		
		GameState g = new GameState(log.playerName);
		
		g.game_init(log.games.get(0).get(0));
		g.printState();
	}
	

}
