package me.qintinator.ezbroadcast;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	

	@Override
	public void onEnable() {
		
		saveDefaultConfig();
		
		Broadcaster.getBroadcaster().startBroadcaster(this);
		Bukkit.getPluginCommand("ezbroadcast").setExecutor(new EzBroadcastCommand(this));
		
		// Added Batats
		new Metrics(this,8574);
	}
	
}
