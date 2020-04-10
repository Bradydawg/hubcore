package com.bradydawg.hubcore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class HMPlayer {
    private static List<HMPlayer> players = new ArrayList<>();

    private UUID uuid;

    private ChatColor color;

    private ChatColor name_color;

    private File file;

    private YamlConfiguration config;

    public HMPlayer(UUID uuid) {
        this.uuid = uuid;
        this.file = new File(HubCore.getDataPath(), uuid.toString() + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
        this.config.options().copyDefaults(true);
        this.config.addDefault("color", Character.valueOf('7'));
        this.config.addDefault("name-color", Character.valueOf('7'));
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.color = ChatColor.getByChar(this.config.getString("color"));
        this.name_color = ChatColor.getByChar(this.config.getString("name-color"));
        players.add(this);
    }

    public void setColor(ChatColor color) {
        this.color = color;
        this.config.set("color", Character.valueOf(color.getChar()));
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNameColor(ChatColor color) {
        this.name_color = color;
        this.config.set("name-color", Character.valueOf(color.getChar()));
        Player player = Bukkit.getPlayer(getUniqueId());
        if (color == ChatColor.RESET) {
            player.setDisplayName(HubCore.toRainbow(player.getName()) + ChatColor.RESET);
        } else {
            player.setDisplayName(color + player.getName() + ChatColor.RESET);
        }
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HMPlayer getHMPlayer(UUID uuid) {
        for (HMPlayer player : players) {
            if (player.getUniqueId().equals(uuid))
                return player;
        }
        return null;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public ChatColor getNameColor() {
        return this.name_color;
    }

    public UUID getUniqueId() {
        return this.uuid;
    }

    public static List<HMPlayer> getPlayers() {
        return players;
    }
}
