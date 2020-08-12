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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Effects implements Listener {

    public static Inventory getEffectsInv(Player p) {
        Inventory nav = Bukkit.createInventory(null, 45, "§6§k§l::§e§lLobby Effects§6§l§k::");

        ItemStack ds1 = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta ds1m = ds1.getItemMeta();
        ds1m.setDisplayName(HubCore.processString("§6§k§l::§e§lSpeed I§6§l§k::", p));
        ds1m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lores = new ArrayList<String>();
        if (ds1m.getLore() != null){
            lores = ds1m.getLore();
        }
        lores.add("§7Right click to enable");
        lores.add("§7Left click to disable");
        ds1m.setLore(lores);
        ds1.setItemMeta(ds1m);

        ItemStack ds2 = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta ds2m = ds2.getItemMeta();
        ds2m.setDisplayName(HubCore.processString("§6§k§l::§e§lSpeed II§6§l§k::", p));
        ds2m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lores1 = new ArrayList<String>();
        if (ds2m.getLore() != null){
            lores1 = ds2m.getLore();
        }
        lores1.add("§7Right click to enable");
        lores1.add("§7Left click to disable");
        ds2m.setLore(lores1);
        ds2.setItemMeta(ds2m);

        ItemStack ds3 = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta ds3m = ds3.getItemMeta();
        ds3m.setDisplayName(HubCore.processString("§6§k§l::§e§lSpeed III§6§l§k::", p));
        ds3m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lores2 = new ArrayList<String>();
        if (ds3m.getLore() != null){
            lores2 = ds3m.getLore();
        }
        lores2.add("§7Right click to enable");
        lores2.add("§7Left click to disable");
        ds3m.setLore(lores2);
        ds3.setItemMeta(ds3m);

        ItemStack ds4 = new ItemStack(Material.IRON_BOOTS);
        ItemMeta ds4m = ds4.getItemMeta();
        ds4m.setDisplayName(HubCore.processString("§6§k§l::§e§lSpeed IV§6§l§k::", p));
        ds4m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lores3 = new ArrayList<String>();
        if (ds4m.getLore() != null){
            lores3 = ds4m.getLore();
        }
        lores3.add("§7Right click to enable");
        lores3.add("§7Left click to disable");
        ds4m.setLore(lores3);
        ds4.setItemMeta(ds4m);

        ItemStack ds5 = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta ds5m = ds5.getItemMeta();
        ds5m.setDisplayName(HubCore.processString("§6§k§l::§e§lSpeed V§6§l§k::", p));
        ds5m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lores4 = new ArrayList<String>();
        if (ds5m.getLore() != null){
            lores4 = ds5m.getLore();
        }
        lores4.add("§7Right click to enable");
        lores4.add("§7Left click to disable");
        ds5m.setLore(lores4);
        ds5.setItemMeta(ds5m);

        nav.setItem(11, ds1);
        nav.setItem(12, ds2);
        nav.setItem(13, ds3);
        nav.setItem(14, ds4);
        nav.setItem(15, ds5);

        ItemStack pane = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta pane_meta = pane.getItemMeta();
        pane_meta.setDisplayName(ChatColor.RESET + " ");
        pane.setItemMeta(pane_meta);

        nav.setItem(4, pane);

        ItemStack mw1 = new ItemStack(Material.LIME_DYE);
        ItemMeta mw1m = mw1.getItemMeta();
        mw1m.setDisplayName(HubCore.processString("§7Toggle Friend Requests", p));
        mw1.setItemMeta(mw1m);

        ItemStack mw2 = new ItemStack(Material.LIME_DYE);
        ItemMeta mw2m = mw2.getItemMeta();
        mw2m.setDisplayName(HubCore.processString("§7Toggle Party Requests", p));
        mw2.setItemMeta(mw2m);

        ItemStack mw3 = new ItemStack(Material.LIME_DYE);
        ItemMeta mw3m = mw3.getItemMeta();
        mw3m.setDisplayName(HubCore.processString("§7Toggle Messages", p));
        mw3.setItemMeta(mw3m);

        nav.setItem(21, mw1);
        nav.setItem(22, mw2);
        nav.setItem(23, mw3);

        ItemStack se1 = new ItemStack(Material.LIME_TERRACOTTA);
        ItemMeta se1m = se1.getItemMeta();
        se1m.setDisplayName(HubCore.processString("§7See everyone", p));
        se1.setItemMeta(se1m);

        ItemStack se2 = new ItemStack(Material.YELLOW_TERRACOTTA);
        ItemMeta se2m = se2.getItemMeta();
        se2m.setDisplayName(HubCore.processString("§7See friends and staff", p));
        se2.setItemMeta(se2m);

        ItemStack se3 = new ItemStack(Material.RED_TERRACOTTA);
        ItemMeta se3m = se3.getItemMeta();
        se3m.setDisplayName(HubCore.processString("§7See nobody but staff members", p));
        se3.setItemMeta(se3m);

        nav.setItem(30, se1);
        nav.setItem(31, se2);
        nav.setItem(32, se3);

        ItemStack qu1 = new ItemStack(Material.QUARTZ_SLAB);
        ItemMeta qu1m = qu1.getItemMeta();
        qu1m.setDisplayName(HubCore.processString("§7Toggle Normal Jump", p));
        qu1.setItemMeta(qu1m);

        ItemStack qu2 = new ItemStack(Material.QUARTZ_STAIRS);
        ItemMeta qu2m = qu2.getItemMeta();
        qu2m.setDisplayName(HubCore.processString("§7Toggle Double Jump", p));
        qu2.setItemMeta(qu2m);

        ItemStack qu3 = new ItemStack(Material.FEATHER);
        ItemMeta qu3m = qu3.getItemMeta();
        qu3m.setDisplayName(HubCore.processString("§7Toggle Fly", p));
        qu3.setItemMeta(qu3m);

        nav.setItem(0, pane);
        nav.setItem(1, pane);
        nav.setItem(2, pane);
        nav.setItem(3, pane);
        nav.setItem(4, pane);
        nav.setItem(5, pane);
        nav.setItem(6, pane);
        nav.setItem(7, pane);
        nav.setItem(8, pane);
        nav.setItem(9, pane);
        nav.setItem(10, pane);
        nav.setItem(16, pane);
        nav.setItem(17, pane);
        nav.setItem(18, pane);
        nav.setItem(19, pane);
        nav.setItem(20, pane);
        nav.setItem(24, pane);
        nav.setItem(25, pane);
        nav.setItem(26, pane);
        nav.setItem(27, pane);
        nav.setItem(28, pane);
        nav.setItem(29, pane);
        nav.setItem(33, pane);
        nav.setItem(34, pane);
        nav.setItem(35, pane);
        nav.setItem(36, pane);
        nav.setItem(37, pane);
        nav.setItem(38, pane);
        nav.setItem(39, pane);
        nav.setItem(40, pane);
        nav.setItem(41, pane);
        nav.setItem(42, pane);
        nav.setItem(43, pane);
        nav.setItem(44, pane);


        ItemStack ec = new ItemStack(Material.END_CRYSTAL);
        ItemMeta ecm = ec.getItemMeta();
        ecm.setDisplayName(HubCore.processString("&9Welcome to Arcadelia!", p));
        List<String> ecml = new ArrayList<String>();
        ecml.add(HubCore.processString("&eDiscord: ", p) + HubCore.processConfigString("Navigator.DiscordLink", p));
        ecml.add(HubCore.processString("&eForums: ", p) + HubCore.processConfigString("Navigator.ForumsLink", p));
        ecm.setLore(ecml);
        ec.setItemMeta(ecm);


        ItemStack em = new ItemStack(Material.EMERALD);
        ItemMeta emm = em.getItemMeta();
        emm.setDisplayName(HubCore.processString("§2§k§l::§a§lArcadelia Store§2§k§l::", p));
        List<String> lores5 = new ArrayList<String>();
        if (emm.getLore() != null){
            lores5 = emm.getLore();
        }
        lores5.add("§aCheck out our server store @");
        lores5.add("§6store.arcadelia.net");
        emm.setLore(lores5);
        em.setItemMeta(emm);

        nav.setItem(24, pane);
        nav.setItem(25, pane);
        nav.setItem(26, pane);

        return nav;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        /*
         * Gives the player a Navigator in their inventory.
         * `4` is the 5th slot in the players hotbar.
         */

        e.getPlayer().getInventory().setItem(7, HubCore.getEffects(e.getPlayer()));
        return;
    }

    @EventHandler
    public void swapHands(PlayerSwapHandItemsEvent e) {
        //Prevents players from switching the Navigator to their offhand.

        if(e.getOffHandItem() != null || e.getOffHandItem().hasItemMeta()) {
            if(e.getOffHandItem().getItemMeta().equals(HubCore.getEffects(e.getPlayer()).getItemMeta())) {
                e.setCancelled(true);
            }
        }

        return;
    }

    @EventHandler
    public void itemDrop(PlayerDropItemEvent e) {
        //Prevents the player from dropping the Navigator

        if(e.getItemDrop().getItemStack().getItemMeta().equals(HubCore.getEffects(e.getPlayer()).getItemMeta())) {
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
            if(e.getCurrentItem().getItemMeta().equals(HubCore.getEffects(p).getItemMeta())) {
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
            if(e.getCurrentItem().getItemMeta().equals(HubCore.getEffects(p).getItemMeta())) {
                e.setCancelled(true);
            }
        }else if(e.getView().getTitle().equals("§6§k§l::§e§lLobby Effects§6§l§k::")) {
            ItemStack i = e.getCurrentItem();

            e.setCancelled(true);

            if(i.getType().equals(Material.LEATHER_BOOTS)) {

                if(i.getItemMeta().getDisplayName().contains("§6§k§l::§e§lSpeed I§6§l§k::")) {
                    p.closeInventory();
                    p.performCommand("say Speed I isn't set up yet!");
                }

            }else if(i.getType().equals(Material.CHAINMAIL_BOOTS)) {

                if(i.getItemMeta().getDisplayName().contains("§6§k§l::§e§lSpeed II§6§l§k::")) {
                    p.closeInventory();
                    p.performCommand("say Speed II isn't set up yet!");
                }

            }else if(i.getType().equals(Material.GOLDEN_BOOTS)) {

                if(i.getItemMeta().getDisplayName().contains("§6§k§l::§e§lSpeed III§6§l§k::")) {
                    p.closeInventory();
                    p.performCommand("say Speed III isn't set up yet!");
                }

            }else if(i.getType().equals(Material.IRON_BOOTS)) {

                if(i.getItemMeta().getDisplayName().contains("§6§k§l::§e§lSpeed IV§6§l§k::")) {
                    p.closeInventory();
                    p.performCommand("say Speed IV isn't set up yet!");
                }

            }else if(i.getType().equals(Material.DIAMOND_BOOTS)) {

                if(i.getItemMeta().getDisplayName().contains("§6§k§l::§e§lSpeed V§6§l§k::")) {
                    p.closeInventory();
                    p.performCommand("say Speed V isn't set up yet!");
                }

            }else if(i.getType().equals(Material.LIME_DYE)) {

                if(i.getItemMeta().getDisplayName().contains("§7Toggle Friend Requests")) {
                    p.closeInventory();
                    p.performCommand("say Friend requests aren't set up yet!");
                }
                else if(i.getItemMeta().getDisplayName().contains("§7Toggle Party Requests")) {
                    p.closeInventory();
                    p.performCommand("say Party requests aren't set up yet!");
                }
                else if(i.getItemMeta().getDisplayName().contains("§7Toggle Messages")) {
                    p.closeInventory();
                    p.performCommand("say Message toggling aren't set up yet!");
                }

            }else if(i.getType().equals(Material.LIME_TERRACOTTA)) {

                if(i.getItemMeta().getDisplayName().contains("§7See everyone")) {
                    p.closeInventory();
                    p.performCommand("say Toggling the see feature isn't set up yet!");
                }

            }else if(i.getType().equals(Material.YELLOW_TERRACOTTA)) {

                if(i.getItemMeta().getDisplayName().contains("§7See friends and staff")) {
                    p.closeInventory();
                    p.performCommand("say Toggling the see feature isn't set up yet!");
                }

            }else if(i.getType().equals(Material.RED_TERRACOTTA)) {

                if(i.getItemMeta().getDisplayName().contains("§7See nobody but staff members")) {
                    p.closeInventory();
                    p.performCommand("say Toggling the see feature isn't set up yet!");
                }

            }else if(i.getType().equals(Material.QUARTZ_SLAB)) {

                if(i.getItemMeta().getDisplayName().contains("§7Toggle Normal Jump")) {
                    p.closeInventory();
                    p.performCommand("say Toggling normal jump isn't set up yet!");
                }

            }else if(i.getType().equals(Material.QUARTZ_STAIRS)) {

                if(i.getItemMeta().getDisplayName().contains("§7Toggle Double Jump")) {
                    p.closeInventory();
                    p.performCommand("say Toggling double jump isn't set up yet!");
                }

            }else if(i.getType().equals(Material.FEATHER)) {

                if(i.getItemMeta().getDisplayName().contains("§7Toggle Fly")) {
                    p.closeInventory();
                    p.performCommand("say Toggling fly isn't set up yet!");
                }

            }
        }

        return;
    }

    @EventHandler
    public void plaInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        PlayerInventory inv = p.getInventory();

        //Allows the player to trigger the Navigator

        if((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK) && (e.getAction() != Action.LEFT_CLICK_AIR) && (e.getAction() != Action.LEFT_CLICK_BLOCK)) {
            return;
        }

        if(e.getItem() == null) {
            return;
        }

        if(e.getItem().getItemMeta().equals(HubCore.getEffects(p).getItemMeta())) {
            e.setCancelled(true);
            p.openInventory(getEffectsInv(p));
            p.openInventory(Effects.getEffectsInv(p));
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
