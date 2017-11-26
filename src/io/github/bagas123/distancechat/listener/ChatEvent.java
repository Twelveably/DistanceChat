package io.github.bagas123.distancechat.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.github.bagas123.distancechat.Main;
import io.github.bagas123.distancechat.arrays.LocalChat;

public class ChatEvent implements Listener {

    LocalChat LocalChat = new LocalChat();

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

	// PLAYER

	Player p = e.getPlayer();

	// DISTANCE CODE
	
	for (String name : LocalChat.players) {
	    e.getRecipients().remove(Bukkit.getPlayer(name));

	}


	if (LocalChat.players.contains(p.getName())) {
	    e.setCancelled(true);
	    for (Player pl : Bukkit.getOnlinePlayers()) {
		e.getRecipients().remove(pl);
	    }
	    for (String al : LocalChat.players) {
		Player local = Bukkit.getPlayer(al);
		e.getRecipients().remove(Bukkit.getPlayer(al));
		if (local.getWorld() == p.getWorld() && local.getLocation()
			.distanceSquared(p.getLocation()) <= Main.instance.getConfigInt("distance")
				* Main.instance.getConfigInt("distance")) {
		    e.setCancelled(true);
		    e.setFormat("&8[&7LOCAL&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY + "> "
			    + ChatColor.WHITE + e.getMessage());
		    local.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
		}
	    }
	}

    }

}
