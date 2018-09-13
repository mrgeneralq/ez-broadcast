package me.qintinator.ezbroadcast;


import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class Broadcaster {
	
	private static Broadcaster broadcaster;	
	private List<String> loadedMessages;
	private int messageId = 0;
	private String prefix = "";
	private int interval = 30;
	private BukkitTask task;
	
	private Broadcaster() {}
	
	public static Broadcaster getBroadcaster() {
		if(broadcaster == null)
			broadcaster = new Broadcaster();
		return broadcaster;
	}
	
	
	
	
	
	public void startBroadcaster(Main main) {
		
		messageId = 0;
		loadedMessages = main.getConfig().getStringList("messages");
		interval = main.getConfig().getInt("interval");
		prefix = main.getConfig().getString("prefix");
		
		
		if(task != null)
			task.cancel();
	
			
			task =  new BukkitRunnable() {
				
				
				@Override
				public void run() {
					
					
					for(Player p: Bukkit.getOnlinePlayers()) {
					
						
						try {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + " " + loadedMessages.get(messageId)));						
						}catch(Exception e) {
							Bukkit.getLogger().warning("Could not send message! Check format in config and reload it!");
						}

					}
					
					if(messageId == loadedMessages.size() -1) {
						messageId = 0;
					}else {
						messageId ++;
					}
						
				}
			}.runTaskTimerAsynchronously(main,0,(long) 20 * interval);		

		
	}
	
}
