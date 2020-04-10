package com.bradydawg.hubcore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerChatListener implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (HMPlayer.getHMPlayer(event.getPlayer().getUniqueId()).getColor() == ChatColor.RESET) {
            event.setMessage(HubCore.toRainbow(event.getMessage()));
        } else {
            event.setMessage(HMPlayer.getHMPlayer(event.getPlayer().getUniqueId()).getColor() + event.getMessage());
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        HMPlayer player = new HMPlayer(event.getPlayer().getUniqueId());
        Player p = Bukkit.getPlayer(player.getUniqueId());
        if (player.getNameColor() == ChatColor.RESET) {
            p.setDisplayName(HubCore.toRainbow(p.getName()) + ChatColor.RESET);
        } else {
            p.setDisplayName(player.getNameColor() + p.getName() + ChatColor.RESET);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        HMPlayer.getPlayers().remove(HMPlayer.getHMPlayer(event.getPlayer().getUniqueId()));
    }
}
