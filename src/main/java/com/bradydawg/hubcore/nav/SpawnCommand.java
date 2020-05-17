package com.bradydawg.hubcore.nav;

import com.bradydawg.hubcore.HubCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("spawn")) {
            p.sendMessage("§a[Arcadelia] §bTeleporting to the spawn!");
            p.teleport(new Location(Bukkit.getServer().getWorld("world"), 446.441 , 80.62 , -412.504 , 90.5f , 0.1f));
        }

        return true;
    }

}
