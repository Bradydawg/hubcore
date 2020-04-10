/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (Chat) as your own
 * You are NOT allowed to publish this plugin (Chat) or your modified version of this plugin (Nickname)
 */
package com.bradydawg.hubcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class HookManager {

    public static boolean checkVault() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Vault");
        return plugin != null && plugin.isEnabled();
    }

    public static boolean checkPlaceholderAPI() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI");
        return plugin != null && plugin.isEnabled();
    }

}
