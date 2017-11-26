package io.github.bagas123.distancechat;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.bagas123.distancechat.commands.Local;
import io.github.bagas123.distancechat.listener.ChatEvent;

public class Main extends JavaPlugin {
    
    private File files;
    public static Main instance;
    
    @Override
    public void onEnable() {
	instance = this;
	saveResource("config.yml", false);
	this.files = new File(getDataFolder(), "config.yml");
	YamlConfiguration.loadConfiguration(this.files);
	
	Bukkit.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
	this.getCommand("local").setExecutor(new Local());
    }

    @Override
    public void onDisable() {

    }
    
    public String getConfigString(String arg) {
	return getConfig().getString(arg);	
    }
    
    public Integer getConfigInt(String arg) {
	return getConfig().getInt(arg);	
    }
    

}
