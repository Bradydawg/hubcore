package com.bradydawg.hubcore.nav;

import com.bradydawg.hubcore.HubCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Navigator implements Listener {

	public static Inventory getNavigatorInv(Player p) {
		Inventory nav = Bukkit.createInventory(null, 27, "§3§k§l::§b§lGame Menu§3§k§l::");

		ItemStack ds1 = new ItemStack(Material.TNT);
		ItemMeta ds1m = ds1.getItemMeta();
		ds1m.setDisplayName(HubCore.processString("§3§k§l::§b§lMISSILE WARS§3§k§l::", p));
		List<String> lores = new ArrayList<String>();
		if (ds1m.getLore() != null){
			lores = ds1m.getLore();
		}
		lores.add("§7Battle it out across the skies as you try,");
		lores.add("§7spawn deadly missiles,");
		lores.add("§7and destroy the other team’s portal!");
		ds1m.setLore(lores);
		ds1.setItemMeta(ds1m);

		ItemStack ds2 = new ItemStack(Material.REDSTONE);
		ItemMeta ds2m = ds2.getItemMeta();
		ds2m.setDisplayName(HubCore.processString("§4§k§l::§c§lDEATH SWAP§4§k§l::", p));
		List<String> lores1 = new ArrayList<String>();
		if (ds2m.getLore() != null){
			lores1 = ds2m.getLore();
		}
		lores1.add("§7Randomly swap with other players whilst");
		lores1.add("§7gathering the resources needed ");
		lores1.add("§7to trap and kill upon swapping.");
		ds2m.setLore(lores1);
		ds2.setItemMeta(ds2m);

		ItemStack ds3 = new ItemStack(Material.PURPUR_PILLAR);
		ItemMeta ds3m = ds3.getItemMeta();
		ds3m.setDisplayName(HubCore.processString("§5§k§l::§d§lMONSTER INDUSTRIES§5§k§l::", p));
		List<String> lores2 = new ArrayList<String>();
		if (ds3m.getLore() != null){
			lores2 = ds3m.getLore();
		}
		lores2.add("§7Choose one of two companies:");
		lores2.add("§7Ender Enterprise or Creeper Corp.");
		lores2.add("§7Sabotage your rival team’s production");
		lores2.add("§7by gathering resources and spawning");
		lores2.add("§7enemies at their headquarters.");
		ds3m.setLore(lores2);
		ds3.setItemMeta(ds3m);

		ItemStack ds4 = new ItemStack(Material.GOLD_INGOT);
		ItemMeta ds4m = ds4.getItemMeta();
		ds4m.setDisplayName(HubCore.processString("§6§k§l::§e§lGOLD RUSH§6§k§l::", p));
		List<String> lores3 = new ArrayList<String>();
		if (ds4m.getLore() != null){
			lores3 = ds4m.getLore();
		}
		lores3.add("§7Gather as much gold as possible");
		lores3.add("§7whilst simultaneously preventing the");
		lores3.add("§7other team from gathering resources!");
		ds4m.setLore(lores3);
		ds4.setItemMeta(ds4m);

		nav.setItem(10, ds1);
		nav.setItem(11, ds2);
		nav.setItem(12, ds3);
		nav.setItem(13, ds4);

		ItemStack pane = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
		ItemMeta pane_meta = pane.getItemMeta();
		pane_meta.setDisplayName(ChatColor.RESET + " ");
		pane.setItemMeta(pane_meta);

		nav.setItem(4, pane);

		ItemStack mw1 = new ItemStack(Material.FIREWORK_ROCKET);
		ItemMeta mw1m = mw1.getItemMeta();
		mw1m.setDisplayName(HubCore.processString("&cMissile Wars #1", p));
		mw1.setItemMeta(mw1m);

		ItemStack mw2 = new ItemStack(Material.FIREWORK_ROCKET);
		ItemMeta mw2m = mw2.getItemMeta();
		mw2m.setDisplayName(HubCore.processString("&cMissile Wars #2", p));
		mw2.setItemMeta(mw2m);

		ItemStack mw3 = new ItemStack(Material.FIREWORK_ROCKET);
		ItemMeta mw3m = mw3.getItemMeta();
		mw3m.setDisplayName(HubCore.processString("&cMissile Wars #3", p));
		mw3.setItemMeta(mw3m);

		ItemStack mw4 = new ItemStack(Material.FIREWORK_ROCKET);
		ItemMeta mw4m = mw4.getItemMeta();
		mw4m.setDisplayName(HubCore.processString("&cMissile Wars #4", p));
		mw4.setItemMeta(mw4m);

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
		nav.setItem(14, pane);
		nav.setItem(17, pane);
		nav.setItem(18, pane);
		nav.setItem(19, pane);
		nav.setItem(20, pane);

		ItemStack gaxe = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta gaxem = gaxe.getItemMeta();
		gaxem.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		gaxem.setDisplayName(HubCore.processString("§2§k§l::§a§lWelcome to Arcadelia!§2§k§l::", p));
		List<String> lores4 = new ArrayList<String>();
		if (gaxem.getLore() != null){
			lores4 = gaxem.getLore();
		}
		lores4.add("§7Select a gamemode to be placed");
		lores4.add("§7into the game queue!");
		gaxem.setLore(lores4);
		gaxe.setItemMeta(gaxem);

		nav.setItem(15, gaxe);

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

		nav.setItem(16, em);

		nav.setItem(21, pane);
		nav.setItem(22, pane);
		nav.setItem(23, pane);
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

		e.getPlayer().getInventory().setItem(0, HubCore.getNavigator(e.getPlayer()));
		e.getPlayer().teleport(new Location(Bukkit.getServer().getWorld("world"), 446.441 , 80.62 , -412.504 , 90.5f , 0.1f));
		return;
	}

	@EventHandler
	public void swapHands(PlayerSwapHandItemsEvent e) {
		//Prevents players from switching the Navigator to their offhand.

		if(e.getOffHandItem() != null || e.getOffHandItem().hasItemMeta()) {
			if(e.getOffHandItem().getItemMeta().equals(HubCore.getNavigator(e.getPlayer()).getItemMeta())) {
				e.setCancelled(true);
			}
		}

		return;
	}

	@EventHandler
	public void itemDrop(PlayerDropItemEvent e) {
		//Prevents the player from dropping the Navigator

		if(e.getItemDrop().getItemStack().getItemMeta().equals(HubCore.getNavigator(e.getPlayer()).getItemMeta())) {
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
			if(e.getCurrentItem().getItemMeta().equals(HubCore.getNavigator(p).getItemMeta())) {
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
			if(e.getCurrentItem().getItemMeta().equals(HubCore.getNavigator(p).getItemMeta())) {
				e.setCancelled(true);
			}
		}else if(e.getView().getTitle().equals("§3§k§l::§b§lGame Menu§3§k§l::")) {
			ItemStack i = e.getCurrentItem();

			e.setCancelled(true);

			if(i.getType().equals(Material.TNT)) {

				if(i.getItemMeta().getDisplayName().contains("§3§k§l::§b§lMISSILE WARS§3§k§l::")) {
					p.closeInventory();
					p.performCommand("say This shit isn't set up yet!");
				}

			}else if(i.getType().equals(Material.REDSTONE)) {

				if(i.getItemMeta().getDisplayName().contains("§4§k§l::§c§lDEATH SWAP§4§k§l::")) {
					p.closeInventory();
					p.performCommand("say This shit also isn't set up yet!");
				}

			}else if(i.getType().equals(Material.PURPUR_PILLAR)) {

				if(i.getItemMeta().getDisplayName().contains("§5§k§l::§d§lMONSTER INDUSTRIES§5§k§l::")) {
					p.closeInventory();
					p.performCommand("say This shit also also isn't set up yet!");
				}

			}else if(i.getType().equals(Material.GOLD_INGOT)) {

				if(i.getItemMeta().getDisplayName().contains("§6§k§l::§e§lGOLD RUSH§6§k§l::")) {
					p.closeInventory();
					p.performCommand("say This shit also also also isn't set up yet!");
				}

			}else if(i.getType().equals(Material.DIAMOND_PICKAXE)) {

				if(i.getItemMeta().getDisplayName().contains("§2§k§l::§a§lWelcome to Arcadelia!§2§k§l::")) {
				}

			}else if(i.getType().equals(Material.END_CRYSTAL)) {

				p.closeInventory();
				String message = "";
				List<String> msg_list = HubCore.instance.getConfig().getStringList("Navigator.EndCrystalMsg");

				for(String s : msg_list) {
					if(s.equals(msg_list.get(msg_list.size() - 1))) {
						message = message + HubCore.processString(s, p);
						break;
					}

					message = message + HubCore.processString(s, p) + "\n";
				}

				p.sendMessage(message);

			}else if(i.getType().equals(Material.EMERALD)) {

				if(i.getItemMeta().getDisplayName().contains("§2§k§l::§a§lArcadelia Store§2§k§l::")) {
				}

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

		if(e.getItem().getItemMeta().equals(HubCore.getNavigator(p).getItemMeta())) {
			e.setCancelled(true);
			p.openInventory(getNavigatorInv(p));
			p.openInventory(Navigator.getNavigatorInv(p));
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
