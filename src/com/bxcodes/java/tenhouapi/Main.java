package com.bxcodes.java.tenhouapi;

import java.io.File;
import java.util.Arrays;

import org.w3c.dom.Node;

public class Main {

	public static void main(String[] args) {
		//TenhouClient client = TenhouClient.getClient();
		//client.sendString("<HELO name=\"NoName\" tid=\"f0\" sx=\"M\" />\0");
		
		File f = new File("e1.mjv.xml");
		
		TenhouLog log = TenhouLog.getLog(f);
		
		GameState g = new GameState(log.playerName);
		
		g.game_init(log.games.get(1).get(0));
		g.printState();
		
		for(Node n : log.games.get(1)) {
			g.nextMove(n);
		}
		
		
		
		
		/*
		System.out.println(TenhouUtil.tileString(68));
		
		System.out.println(Integer.toBinaryString(42031));
		
		System.out.println((42031&0x0018)>>3);
		System.out.println((42031&0x0060)>>5);
		System.out.println((42031&0x0180)>>7);
		System.out.println((42031&0xfc00)>>10);
		
		*/
	}
	

}
