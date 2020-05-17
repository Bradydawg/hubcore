package com.bradydawg.hubcore.nav;

import com.bradydawg.hubcore.HubCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.*;

public class Shop implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        /*
         * Gives the player a Navigator in their inventory.
         * `4` is the 5th slot in the players hotbar.
         */

        e.getPlayer().getInventory().setItem(1, HubCore.getShop(e.getPlayer()));
        return;
    }

    @EventHandler
    public void swapHands(PlayerSwapHandItemsEvent e) {
        //Prevents players from switching the Navigator to their offhand.

        if(e.getOffHandItem() != null || e.getOffHandItem().hasItemMeta()) {
            if(e.getOffHandItem().getItemMeta().equals(HubCore.getShop(e.getPlayer()).getItemMeta())) {
                e.setCancelled(true);
            }
        }

        return;
    }

    @EventHandler
    public void itemDrop(PlayerDropItemEvent e) {
        //Prevents the player from dropping the Navigator

        if(e.getItemDrop().getItemStack().getItemMeta().equals(HubCore.getShop(e.getPlayer()).getItemMeta())) {
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
            if(e.getCurrentItem().getItemMeta().equals(HubCore.getShop(p).getItemMeta())) {
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
            if(e.getCurrentItem().getItemMeta().equals(HubCore.getShop(p).getItemMeta())) {
                e.setCancelled(true);
            }
        }

        return;
    }

    @EventHandler
    public void plaInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        //Allows the player to trigger the Navigator

        if((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK) && (e.getAction() != Action.LEFT_CLICK_AIR) && (e.getAction() != Action.LEFT_CLICK_BLOCK)) {
            return;
        }

        if(e.getItem() == null) {
            return;
        }

        if(e.getItem().getItemMeta().equals(HubCore.getShop(p).getItemMeta())) {
            e.setCancelled(true);
            p.closeInventory();
            p.performCommand("say The store isn't set up yet!");
        }

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
