/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (HubCore) as your own
 * You are NOT allowed to publish this plugin (HubCore) or your modified version of this plugin (HubCore)
 */
package com.bradydawg.hubcore.nav;

import com.bradydawg.hubcore.HubCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Lobby implements Listener {

    public static Inventory getLobbyInv(Player p) {
        Inventory nav = Bukkit.createInventory(null, 9, "§1§k§l::§9§lLobbies§1§k§l::");

        ItemStack ds1 = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
        ItemMeta ds1m = ds1.getItemMeta();
        ds1m.setDisplayName(HubCore.processString("§3§k§l::§b§lPREMIUM LOBBY§3§k§l::", p));
        List<String> lores = new ArrayList<String>();
        if (ds1m.getLore() != null){
            lores = ds1m.getLore();
        }
        lores.add("§7Click to join the Premium Lobby!");
        lores.add("§7This lobby can only be joined");
        lores.add("§7by players with a premium rank!");
        ds1m.setLore(lores);
        ds1.setItemMeta(ds1m);

        ItemStack ds2 = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta ds2m = ds2.getItemMeta();
        ds2m.setDisplayName(HubCore.processString("§2§k§l::§a§lLOBBY 1§2§k§l::", p));
        List<String> lores1 = new ArrayList<String>();
        if (ds2m.getLore() != null){
            lores1 = ds2m.getLore();
        }
        lores1.add("§7Click to join Lobby 1!");
        ds2m.setLore(lores1);
        ds2.setItemMeta(ds2m);

        nav.setItem(3, ds1);
        nav.setItem(5, ds2);

        ItemStack pane = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta pane_meta = pane.getItemMeta();
        pane_meta.setDisplayName(ChatColor.RESET + " ");
        pane.setItemMeta(pane_meta);

        nav.setItem(0, pane);
        nav.setItem(1, pane);
        nav.setItem(2, pane);
        nav.setItem(6, pane);
        nav.setItem(7, pane);
        nav.setItem(8, pane);

        ItemStack gaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta gaxem = gaxe.getItemMeta();
        gaxem.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        gaxem.setDisplayName(HubCore.processString("§2§k§l::§a§lWelcome to Arcadelia!§2§k§l::", p));
        List<String> lores4 = new ArrayList<String>();
        if (gaxem.getLore() != null){
            lores4 = gaxem.getLore();
        }
        lores4.add("§7Select one of the lobbies");
        lores4.add("§7to be teleported!");
        gaxem.setLore(lores4);
        gaxe.setItemMeta(gaxem);

        nav.setItem(4, gaxe);

        return nav;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        /*
         * Gives the player a Navigator in their inventory.
         * `4` is the 5th slot in the players hotbar.
         */

        e.getPlayer().getInventory().setItem(8, HubCore.getLobby(e.getPlayer()));
        return;
    }

    @EventHandler
    public void swapHands(PlayerSwapHandItemsEvent e) {
        //Prevents players from switching the Navigator to their offhand.

        if(e.getOffHandItem() != null || e.getOffHandItem().hasItemMeta()) {
            if(e.getOffHandItem().getItemMeta().equals(HubCore.getLobby(e.getPlayer()).getItemMeta())) {
                e.setCancelled(true);
            }
        }

        return;
    }

    @EventHandler
    public void itemDrop(PlayerDropItemEvent e) {
        //Prevents the player from dropping the Navigator

        if(e.getItemDrop().getItemStack().getItemMeta().equals(HubCore.getLobby(e.getPlayer()).getItemMeta())) {
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
            if(e.getCurrentItem().getItemMeta().equals(HubCore.getLobby(p).getItemMeta())) {
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
            if(e.getCurrentItem().getItemMeta().equals(HubCore.getLobby(p).getItemMeta())) {
                e.setCancelled(true);
            }
        }else if(e.getView().getTitle().equals("§1§k§l::§9§lLobbies§1§k§l::")) {
            ItemStack i = e.getCurrentItem();

            e.setCancelled(true);

            if (i.getType().equals(Material.LIGHT_BLUE_CONCRETE)) {

                if (i.getItemMeta().getDisplayName().contains("§3§k§l::§b§lPREMIUM LOBBY§3§k§l::")) {
                    p.closeInventory();
                    p.performCommand("say The Premium Lobby doesn't exist yet!");
                }

            } else if (i.getType().equals(Material.LIME_CONCRETE)) {

                if (i.getItemMeta().getDisplayName().contains("§2§k§l::§a§lLOBBY 1§2§k§l::")) {
                    p.closeInventory();
                    p.performCommand("server lobby-1");
                }
            }
        }

        return;
    }

    @EventHandler
    public void plaInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        //Allows the player to trigger the lobby bullshittery

        if((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK) && (e.getAction() != Action.LEFT_CLICK_AIR) && (e.getAction() != Action.LEFT_CLICK_BLOCK)) {
            return;
        }

        if(e.getItem() == null) {
            return;
        }

        if(e.getItem().getItemMeta().equals(HubCore.getLobby(p).getItemMeta())) {
            e.setCancelled(true);
            p.openInventory(getLobbyInv(p));
            p.openInventory(Lobby.getLobbyInv(p));
            if(e.getAction().equals(InventoryAction.PLACE_ALL)) {
                e.setCancelled(true);
            }
        }

        return;
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
