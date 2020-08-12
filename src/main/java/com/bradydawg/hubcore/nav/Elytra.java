/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (HubCore) as your own
 * You are NOT allowed to publish this plugin (HubCore) or your modified version of this plugin (HubCore)
 */
package com.bradydawg.hubcore.nav;

import com.bradydawg.hubcore.HubCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Elytra implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PlayerInventory inv = p.getInventory();
        /*
         * Gives the player a Navigator in their inventory.
         * `4` is the 5th slot in the players hotbar.
         */

        e.getPlayer().getInventory().setItem(6, HubCore.getElytra(e.getPlayer()));
        inv.setChestplate(new ItemStack(Material.AIR));
        return;
    }

    @EventHandler
    public void swapHands(PlayerSwapHandItemsEvent e) {
        //Prevents players from switching the Navigator to their offhand.

        if(e.getOffHandItem() != null || e.getOffHandItem().hasItemMeta()) {
            if(e.getOffHandItem().getItemMeta().equals(HubCore.getElytra(e.getPlayer()).getItemMeta())) {
                e.setCancelled(true);
            }
        }

        return;
    }

    @EventHandler
    public void itemDrop(PlayerDropItemEvent e) {
        //Prevents the player from dropping the Navigator

        if(e.getItemDrop().getItemStack().getItemMeta().equals(HubCore.getElytra(e.getPlayer()).getItemMeta())) {
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
            if(e.getCurrentItem().getItemMeta().equals(HubCore.getElytra(p).getItemMeta())) {
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
            if(e.getCurrentItem().getItemMeta().equals(HubCore.getElytra(p).getItemMeta())) {
                e.setCancelled(true);
            }
        }

        return;
    }

    @EventHandler
    public void plaInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        PlayerInventory inv = p.getInventory();


        if(e.getItem() == null || !e.getItem().getType().equals(Material.ELYTRA)
                || !(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            return;
        }
        //Allows the player to trigger the Elytra
        ItemStack item = null;
        String message = null;
        if(inv.getChestplate() == null || inv.getChestplate().getType().equals(Material.AIR)) {
            if(p.hasPermission("hubcore.elytra")) {
                e.getPlayer().getInventory().setItem(6, HubCore.UngetElytra(e.getPlayer()));
                e.getPlayer().getInventory().setItem(2, HubCore.getFirework(e.getPlayer()));
                item = new ItemStack(Material.ELYTRA);
                message = "§a[Arcadelia] §bElytra Equipped";
            }
            else{
                p.sendMessage("§a[Arcadelia] §cElytra can only be used by users with Diamond rank!");
            }
        }
        else if(inv.getChestplate().getType().equals(Material.ELYTRA)) {
            if(p.hasPermission("hubcore.elytra")) {
                e.getPlayer().getInventory().setItem(6, HubCore.getElytra(e.getPlayer()));
                e.getPlayer().getInventory().setItem(2, new ItemStack(Material.AIR));
                item = new ItemStack(Material.AIR);
                message = "§a[Arcadelia] §bElytra Unequiped";
            }
            else{
                p.sendMessage("§a[Arcadelia] §cElytra can only be used by users with Diamond rank!");
            }
        }

        if(item == null) {
            p.sendMessage("§a[Arcadelia] §cYou must unequip your armor first!");
        }

        else if (p.hasPermission("hubcore.elytra")) {
            inv.setChestplate(item);
            p.sendMessage(message);
        }
        else{
            p.sendMessage("§a[Arcadelia] §cElytra can only be used by users with Diamond rank!");
        }

        e.setCancelled(true);

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
