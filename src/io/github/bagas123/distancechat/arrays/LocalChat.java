package io.github.bagas123.distancechat.arrays;

import java.util.HashSet;
import java.util.Set;

public class LocalChat {
    public static Set<String> players = new HashSet<String>();

    public boolean contains(String playerName) {
	
	return players.contains(playerName);
    }
    
    public void add(String playerName) {
	players.add(playerName);
    }
    
    public void remove(String playerName) {
	players.remove(playerName);
    }
}
