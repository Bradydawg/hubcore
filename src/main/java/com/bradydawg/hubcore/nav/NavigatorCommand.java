package com.bradydawg.hubcore.nav;

import com.bradydawg.hubcore.HubCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NavigatorCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("navigator")) {
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("reload") && p.hasPermission("navigator.reload")) {
					HubCore.instance.reloadConfig();
					p.sendMessage(ChatColor.GREEN + "Navigator config.yml reloaded!");
					
					return true;
				}
			}
			
			p.openInventory(Navigator.getNavigatorInv(p));
		}

		return true;

	}

}
