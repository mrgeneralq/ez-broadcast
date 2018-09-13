package me.qintinator.ezbroadcast;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class EzBroadcastCommand implements CommandExecutor {

	private final Main main;
	
	public EzBroadcastCommand(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(args.length == 0 || (args.length == 1 && !args[0].equalsIgnoreCase("reload"))) {
			sender.sendMessage(ChatColor.GOLD + "There is only one command available right now!");
			sender.sendMessage(ChatColor.GOLD + "/ezbroadcast reload");
			return false;
		}
			
		
		if(!sender.hasPermission("ezbroadcast.reload")) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to that command!");
			return false;
		}
		
		main.reloadConfig();
		sender.sendMessage(ChatColor.GREEN + "Ezbroadcast config reloaded!");
		Broadcaster.getBroadcaster().startBroadcaster(main);	
		
		
		return false;
	}

}
