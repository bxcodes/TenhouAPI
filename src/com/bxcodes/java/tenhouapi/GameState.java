package com.bxcodes.java.tenhouapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

public class GameState {
	
	public GameState(String[] names) {
		for(int i = 0; i < 4; i++) {
			players[i] = new Player();
			players[i].name = names[i];
			players[i].point = 250;
		}
	}
	
	public void game_init(Node n) {
		
		doraList.clear();
		for(Player p : players) p.init();
		
		String[] seed = n.getAttributes().getNamedItem("seed").getNodeValue().split(",");
		ba = Integer.parseInt(seed[0]);
		honba = Integer.parseInt(seed[1]);
		doraList.add(Integer.parseInt(seed[5]));
		
		String[] ten = n.getAttributes().getNamedItem("ten").getNodeValue().split(",");
		players[0].point = Integer.parseInt(ten[0]);
		players[1].point = Integer.parseInt(ten[1]);
		players[2].point = Integer.parseInt(ten[2]);
		players[3].point = Integer.parseInt(ten[3]);
		
		String[] hai0 = n.getAttributes().getNamedItem("hai0").getNodeValue().split(",");
		for(String s : hai0) players[0].hand.add(Integer.parseInt(s));
		String[] hai1 = n.getAttributes().getNamedItem("hai1").getNodeValue().split(",");
		for(String s : hai1) players[1].hand.add(Integer.parseInt(s));
		String[] hai2 = n.getAttributes().getNamedItem("hai2").getNodeValue().split(",");
		for(String s : hai2) players[2].hand.add(Integer.parseInt(s));
		String[] hai3 = n.getAttributes().getNamedItem("hai3").getNodeValue().split(",");
		for(String s : hai3) players[3].hand.add(Integer.parseInt(s));
		
		for(Player p : players) {
			Collections.sort(p.hand);
		}
	}
	
	public void printState() {
		System.out.println(ba + ":" + honba);
		System.out.println("dora:" + doraList);
		for(Player p : players) {
			System.out.println(p.name);
			p.hand.stream().map(TenhouUtil::tileString).forEach(System.out::print);
			System.out.println();
		}
	}
	
	
	public int ba;
	public int honba;
	public Player[] players = new Player[4];
	public List<Integer> doraList = new ArrayList<Integer>();
	
	
	
	
	class Player{
		
		void init() {
			riichi = false;
			furiten = false;
			hand.clear();
			fuuroList.clear();
			discardList.clear();
			tedashiList.clear();
		}
		
		String name;
		int point = 0;
		boolean riichi;
		boolean furiten;
		List<Integer> hand = new ArrayList<Integer>();
		List<List<Integer>> fuuroList = new ArrayList<List<Integer>>();
		List<Integer> discardList = new ArrayList<Integer>();
		List<Integer> tedashiList = new ArrayList<Integer>();
	}
}
