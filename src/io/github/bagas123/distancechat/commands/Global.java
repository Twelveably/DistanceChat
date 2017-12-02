package io.github.bagas123.distancechat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.bagas123.distancechat.Main;
import io.github.bagas123.distancechat.arrays.LocalChat;
import net.md_5.bungee.api.ChatColor;

public class Global implements CommandExecutor {

    LocalChat LocalChat = new LocalChat();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	Player p = (Player) sender;

	if (sender instanceof Player) {
	    if (args.length < 1) {
		if (Main.instance.getConfig().getBoolean("lock") == false) {
		    if (!LocalChat.contains(p.getName())) {
			p.sendMessage(
				ChatColor.translateAlternateColorCodes('&', Main.instance.getConfigString("enabled")));
			LocalChat.add(p.getName());
			return true;
		    } else {
			p.sendMessage(
				ChatColor.translateAlternateColorCodes('&', Main.instance.getConfigString("disabled")));
			LocalChat.remove(p.getName());
			return true;
		    }
		} else {
		    p.sendMessage(
			    ChatColor.translateAlternateColorCodes('&', Main.instance.getConfigString("lockmessage")));
		    return true;
		}
	    } else {
		if (args[0].equalsIgnoreCase("reload")) {
		    if (p.hasPermission("local.reload")) {
			p.sendMessage(
				ChatColor.translateAlternateColorCodes('&', "&8[&3DistanceChat&8] &aConfig reloaded."));
			Main.instance.reloadConfig();
			return true;
		    } else {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have permission!"));
			return true;
		    }
		}
		if (args[0].equalsIgnoreCase("admin")) {
		    if (p.hasPermission("local.admin")) {
			if (!LocalChat.admins.contains(p.getName())) {
			    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aAdmin mode enabled."));
			    LocalChat.admins.add(p.getName());
			    return true;
			} else {
			    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aAdmin mode disabled."));
			    LocalChat.admins.remove(p.getName());
			    return true;
			}
		    } else {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have permission!"));
			return true;
		    }
		}
	    }
	}

	return false;
    }

}
