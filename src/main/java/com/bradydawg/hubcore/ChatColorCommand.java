package com.bradydawg.hubcore;

import com.bradydawg.hubcore.menu.FullMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatColorCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("chatcolor.gui"))
            new FullMenu((Player)sender);
        else if (sender.hasPermission("chatcolor.gui") == false)
            sender.sendMessage(ChatColor.RED + "You lack permission to use this command.");
        return true;
    }
}
