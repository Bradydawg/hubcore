/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (HubCore) as your own
 * You are NOT allowed to publish this plugin (HubCore) or your modified version of this plugin (HubCore)
 */
package com.bradydawg.hubcore.nav;

import com.bradydawg.hubcore.HubCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

public class Firework implements Listener {

    @EventHandler
    public void swapHands(PlayerSwapHandItemsEvent e) {
        //Prevents players from switching the Navigator to their offhand.

        if(e.getOffHandItem() != null || e.getOffHandItem().hasItemMeta()) {
            if(e.getOffHandItem().getItemMeta().equals(HubCore.getFirework(e.getPlayer()).getItemMeta())) {
                e.setCancelled(true);
            }
        }

        return;
    }

    @EventHandler
    public void itemDrop(PlayerDropItemEvent e) {
        //Prevents the player from dropping the Navigator

        if(e.getItemDrop().getItemStack().getItemMeta().equals(HubCore.getFirework(e.getPlayer()).getItemMeta())) {
            e.setCancelled(true);
        }

        return;
    }

    @EventHandler
    public void creative(InventoryCreativeEvent e) {
        if(!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null || e.getClickedInventory() == null) {
            return;
        }

        Player p = (Player) e.getWhoClicked();

        if(e.getClickedInventory().equals(p.getInventory())) {
            if(e.getCurrentItem().getItemMeta().equals(HubCore.getFirework(p).getItemMeta())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void invInteractEvent(InventoryClickEvent e) {
        //Prevents the player from moving the Navigator in their inventory

        if(!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null || e.getClickedInventory() == null) {
            return;
        }

        Player p = (Player) e.getWhoClicked();

        if(e.getClickedInventory().equals(p.getInventory())) {
            if(e.getCurrentItem().getItemMeta().equals(HubCore.getFirework(p).getItemMeta())) {
                e.setCancelled(true);
            }
        }

        return;
    }

    @EventHandler
    public void plaInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        PlayerInventory inv = p.getInventory();

        if(e.getItem().getItemMeta().equals(HubCore.getFirework(p).getItemMeta())) {
            p.setVelocity(new Vector(0, 5, 0)) ;
            e.getPlayer().getInventory().setItem(2, HubCore.getFirework(e.getPlayer()));
        }

    }

    @EventHandler
    public void pDeath(PlayerDeathEvent e){
        e.setDeathMessage(null);
        e.setDroppedExp(0);
        e.setKeepInventory(true);
        e.setKeepLevel(true);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        p.getInventory().clear();
    }
    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        Player p = event.getPlayer();
        p.getInventory().clear();
    }
}
