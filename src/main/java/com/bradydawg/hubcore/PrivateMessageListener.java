package com.bradydawg.hubcore;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PrivateMessageListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        HubCore.saveData();
    }
}
