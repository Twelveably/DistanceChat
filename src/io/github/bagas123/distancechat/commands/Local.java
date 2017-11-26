package io.github.bagas123.distancechat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.bagas123.distancechat.Main;
import io.github.bagas123.distancechat.arrays.LocalChat;
import net.md_5.bungee.api.ChatColor;

public class Local implements CommandExecutor {
    
    LocalChat LocalChat = new LocalChat();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	Player p = (Player) sender;

	if (sender instanceof Player) {
	    if (!LocalChat.contains(p.getName())) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfigString("enabled")));
		LocalChat.add(p.getName());
		return true;
	    } else {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfigString("disabled")));
		LocalChat.remove(p.getName());
		return true;
	    }
	}

	return false;
    }

}
