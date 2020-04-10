package com.bradydawg.hubcore.menu;

import java.util.Arrays;
import java.util.stream.IntStream;
import com.bradydawg.hubcore.HubCore;
import com.bradydawg.hubcore.HMPlayer;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class FullMenu extends Menu {
    enum MenuType {
        MAIN, NAME_COLOR, CHAT_COLOR;
    }

    private static final ChatColor[] colors = new ChatColor[] {
            ChatColor.DARK_RED, ChatColor.RED, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.DARK_GREEN, ChatColor.DARK_BLUE, ChatColor.BLUE, ChatColor.DARK_AQUA, ChatColor.AQUA,
            ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE, ChatColor.WHITE, ChatColor.GRAY, ChatColor.DARK_GRAY, };

    private static final Material[] materials = new Material[] {
            Material.REDSTONE_BLOCK, Material.RED_WOOL, Material.GOLD_BLOCK, Material.YELLOW_WOOL, Material.LIME_WOOL, Material.GREEN_WOOL, Material.LAPIS_BLOCK, Material.BLUE_WOOL, Material.CYAN_WOOL, Material.LIGHT_BLUE_WOOL,
            Material.PURPLE_WOOL, Material.PINK_WOOL, Material.WHITE_WOOL, Material.LIGHT_GRAY_WOOL, Material.GRAY_WOOL, };

    private MenuType selectedMenu;

    public FullMenu(Player player) {
        super(player, "Color Menu", 18);
        this.selectedMenu = MenuType.MAIN;
    }

    private void changeMenu(MenuType menu) {
        this.selectedMenu = menu;
        build();
    }

    public void build() {
        if (this.selectedMenu == null)
            changeMenu(MenuType.MAIN);
        switch (this.selectedMenu) {
            case MAIN:
                buildMainMenu();
                break;
            case CHAT_COLOR:
                buildColorMenu();
                break;
            case NAME_COLOR:
                buildColorMenu();
                break;
        }
    }

    public void buildMainMenu() {
        IntStream.range(0, this.inv.getSize()).forEach(i -> this.inv.setItem(i, makeItem(Material.BLACK_STAINED_GLASS_PANE, " ", new String[0])));
        this.inv.setItem(3, makeItem(Material.PURPLE_WOOL, ChatColor.DARK_PURPLE + "Chat Color Menu", new String[] { HubCore.toRainbow("Left Click to open") }));
        this.inv.setItem(5, makeItem(Material.RED_WOOL, ChatColor.RED + "Name Color Menu", new String[] { HubCore.toRainbow("Left Click to open") }));
    }

    public void buildColorMenu() {
        assert materials.length == colors.length;
        IntStream.range(0, 16-17).forEach(i -> this.inv.setItem(i, new ItemStack(Material.AIR)));
        for (int i = 0; i < colors.length; i++)
            this.inv.setItem(i, makeItem(materials[i], colors[i] + nameToDisplayName(colors[i].name()), new String[0]));
        this.inv.setItem(15, makeItem(Material.RED_WOOL, HubCore.toRainbow("Rainbow"), new String[0]));
    }

    private String nameToDisplayName(String name) {
        name = name.toLowerCase();
        name = name.replace('_', ' ');
        name = WordUtils.capitalize(name);
        return name;
    }

    private ItemStack makeItem(Material mat, String name, String... lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    public void onClick(InventoryClickEvent event) {
        ChatColor color, name_color;
        HumanEntity player = event.getWhoClicked();
        HMPlayer hmplayer = HMPlayer.getHMPlayer(player.getUniqueId());
        switch (this.selectedMenu) {
            case MAIN:
                if (event.getSlot() == 3) {
                    changeMenu(MenuType.CHAT_COLOR);
                    break;
                }
                if (event.getSlot() == 5)
                    changeMenu(MenuType.NAME_COLOR);
                break;
            case CHAT_COLOR:
                if (event.getSlot() == colors.length) {
                    if (player.hasPermission("ChatColor.r") || player.hasPermission("ChatColor.*")) {
                        hmplayer.setColor(ChatColor.RESET);
                        player.closeInventory();
                        player.sendMessage(HubCore.toRainbow(HubCore.getConfiguration().getString("Success Message")
                                .replaceAll("%chatcolor%", "")
                                .replaceAll("%color%", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()))));
                    } else {
                        player.sendMessage(toColor(HubCore.getConfiguration().getString("No Permission Message"))
                                .replaceAll("%color%", "Rainbow"));
                    }
                    return;
                }
                color = ChatColor.valueOf(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().toUpperCase().replace(' ', '_')));
                if (player.hasPermission("ChatColor." + color.getChar()) || player.hasPermission("ChatColor.*")) {
                    hmplayer.setColor(color);
                } else {
                    player.sendMessage(toColor(HubCore.getConfiguration().getString("No Permission Message")));
                    return;
                }
                player.closeInventory();
                player.sendMessage(toColor(HubCore.getConfiguration().getString("Success Message")
                        .replaceAll("%chatcolor%", color + ""))
                        .replaceAll("%color%", event.getCurrentItem().getItemMeta().getDisplayName()));
                break;
            case NAME_COLOR:
                if (event.getSlot() == colors.length) {
                    if (player.hasPermission("NameColor.r") || player.hasPermission("ChatColor.*")) {
                        hmplayer.setNameColor(ChatColor.RESET);
                        player.closeInventory();
                        player.sendMessage(HubCore.toRainbow(HubCore.getConfiguration().getString("Name Color Success Message")
                                .replaceAll("%namecolor%", "")
                                .replaceAll("%color%", ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()))));
                    } else {
                        player.sendMessage(toColor(HubCore.getConfiguration().getString("No Permission Message"))
                                .replaceAll("%color%", "Rainbow"));
                    }
                    return;
                }
                name_color = ChatColor.valueOf(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().toUpperCase().replace(' ', '_')));
                if (player.hasPermission("NameColor." + name_color.getChar()) || player.hasPermission("ChatColor.*")) {
                    hmplayer.setNameColor(name_color);
                } else {
                    player.sendMessage(toColor(HubCore.getConfiguration().getString("No Permission Message")));
                    return;
                }
                player.closeInventory();
                player.sendMessage(toColor(HubCore.getConfiguration().getString("Name Color Success Message")
                        .replaceAll("%namecolor%", name_color + ""))
                        .replaceAll("%color%", event.getCurrentItem().getItemMeta().getDisplayName()));
                break;
        }
    }

    public String toColor(String colored) {
        return ChatColor.translateAlternateColorCodes('&', colored);
    }
}
