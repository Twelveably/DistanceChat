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

	// DISABLE PLAYER FROM RECEIVING GLOBAL CHAT

	if (Main.instance.getConfig().getBoolean("lock") == true) {
	    e.setCancelled(true);
	    for (Player pl : Bukkit.getOnlinePlayers()) {
		e.getRecipients().remove(pl);
	    }

	    // LOCAL

	    for (Player al : Bukkit.getOnlinePlayers()) {
		e.getRecipients().remove(al);
		if (!p.hasPermission("local.bypass")) {
		    if (al.getWorld() == p.getWorld() && al.getLocation()
			    .distanceSquared(p.getLocation()) <= Main.instance.getConfigInt("distance")
				    * Main.instance.getConfigInt("distance")) {
			e.setCancelled(true);
			if (!LocalChat.admins.contains(al.getName())) {
			    e.setFormat("&8[&7LOCAL&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY
				    + "> " + ChatColor.WHITE + e.getMessage());
			    al.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
			} else {
			    e.setFormat("&8[&7LOCAL&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY
				    + "> " + ChatColor.WHITE + e.getMessage());
			    al.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
			}
		    } else {
			for (String name : LocalChat.admins) {
			    Player admin = Bukkit.getPlayer(name);
			    e.setFormat("&8[&8LOCALSPY&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY
				    + "> " + ChatColor.WHITE + e.getMessage());
			    admin.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
			}
		    }
		} else {
		    if (!LocalChat.admins.contains(al.getName())) {
			e.setFormat("&8[&4GLOBAL&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY + "> "
				+ ChatColor.WHITE + e.getMessage());
			al.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
		    } else {
			for (String name : LocalChat.admins) {
			    Player admin = Bukkit.getPlayer(name);
			    e.setFormat("&8[&4GLOBAL&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY
				    + "> " + ChatColor.WHITE + e.getMessage());
			    admin.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
			}
		    }
		}
	    }

	}

	for (

	String name : LocalChat.players) {
	    e.getRecipients().remove(Bukkit.getPlayer(name));

	}

	// IF

	if (LocalChat.players.contains(p.getName())) {
	    e.setCancelled(true);
	    for (Player pl : Bukkit.getOnlinePlayers()) {
		e.getRecipients().remove(pl);
	    }

	    // LOCAL

	    for (String al : LocalChat.players) {
		Player local = Bukkit.getPlayer(al);
		e.getRecipients().remove(Bukkit.getPlayer(al));
		if (!p.hasPermission("local.bypass")) {
		    if (local.getWorld() == p.getWorld() && local.getLocation()
			    .distanceSquared(p.getLocation()) <= Main.instance.getConfigInt("distance")
				    * Main.instance.getConfigInt("distance")) {
			e.setCancelled(true);
			if (!LocalChat.admins.contains(al)) {
			    e.setFormat("&8[&7LOCAL&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY
				    + "> " + ChatColor.WHITE + e.getMessage());
			    local.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
			} else {
			    e.setFormat("&8[&7LOCAL&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY
				    + "> " + ChatColor.WHITE + e.getMessage());
			    local.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
			}
		    } else {
			for (String name : LocalChat.admins) {
			    Player admin = Bukkit.getPlayer(name);
			    e.setFormat("&8[&8LOCALSPY&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY
				    + "> " + ChatColor.WHITE + e.getMessage());
			    admin.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
			}
		    }
		} else {
		    if (!LocalChat.admins.contains(al)) {
			e.setFormat("&8[&4GLOBAL&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY + "> "
				+ ChatColor.WHITE + e.getMessage());
			local.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
		    } else {
			for (String name : LocalChat.admins) {
			    Player admin = Bukkit.getPlayer(name);
			    e.setFormat("&8[&4GLOBAL&8] " + ChatColor.GRAY + "<" + p.getDisplayName() + ChatColor.GRAY
				    + "> " + ChatColor.WHITE + e.getMessage());
			    admin.sendMessage(ChatColor.translateAlternateColorCodes('&', e.getFormat()));
			}
		    }
		}
	    }
	}

    }

}
