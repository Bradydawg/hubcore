package com.bradydawg.hubcore.menu;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class Menu implements InventoryHolder {
    private static final List<Player> viewers = new ArrayList<>();

    protected final Player viewer;

    protected final Inventory inv;

    public static void onDisable() {
        viewers.forEach(HumanEntity::closeInventory);
    }

    public Menu(Player player, String title, int size) {
        viewers.add(player);
        this.viewer = player;
        this.inv = Bukkit.createInventory(this, size, title);
        build();
        open();
    }

    public final Inventory getInventory() {
        return this.inv;
    }

    public abstract void build();

    public abstract void onClick(InventoryClickEvent paramInventoryClickEvent);

    public void open() {
        this.viewer.openInventory(this.inv);
    }

    public void close() {
        viewers.remove(this.viewer);
    }
}
