package com.bradydawg.hubcore.menu;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

public class Click_Listeners implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder == null || !(holder instanceof Menu))
            return;
        event.setCancelled(true);
        Menu menu = (Menu)holder;
        if (event.getClick() != ClickType.LEFT || event.isShiftClick())
            return;
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR)
            return;
        menu.onClick(event);
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder == null || !(holder instanceof Menu))
            return;
        Menu menu = (Menu)holder;
        menu.close();
    }
}
