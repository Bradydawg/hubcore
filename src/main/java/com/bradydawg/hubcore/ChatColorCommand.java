package com.bradydawg.hubcore;

import com.bradydawg.hubcore.menu.FullMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatColorCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("ChatColor.GUI") || !(sender instanceof Player))
            sender.sendMessage(ChatColor.RED + "You lack permission to use this command.");
        new FullMenu((Player)sender);
        return true;
    }
}
