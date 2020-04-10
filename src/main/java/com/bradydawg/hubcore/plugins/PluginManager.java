/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (Chat) as your own
 * You are NOT allowed to publish this plugin (Chat) or your modified version of this plugin (Nickname)
 */
package com.bradydawg.hubcore.plugins;

import com.bradydawg.hubcore.HubCore;
import com.bradydawg.hubcore.utils.HookManager;
import com.bradydawg.hubcore.utils.Utils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PluginManager implements PermissionsPlugin {

    private static PermissionsPlugin handler;
    private static PluginManager INSTANCE;

    public static PermissionsPlugin getInstance() {
        return INSTANCE;
    }

    public static void load() {
        INSTANCE = new PluginManager();
        if (HookManager.checkVault() && Vault.setupChat()) {
            handler = new Vault();
        } else {
            handler = new Nothing();
        }
        HubCore.getInstance().getLogger().info("Successfully hooked into: " + handler.getName());
    }

    @Override
    public String getName() {
        if (!HookManager.checkPlaceholderAPI()) {
            return handler.getName();
        } else {
            return handler.getName() + ", PlaceholderAPI";
        }
    }

    @Override
    public String getPrefix(Player p) {
        return handler.getPrefix(p);
    }

    @Override
    public String getSuffix(Player p) {
        return handler.getSuffix(p);
    }

    @Override
    public String[] getGroupNames(Player p) {
        return handler.getGroupNames(p);
    }

    @Override
    public String getMessageFormat(Player p) {
        if (!HookManager.checkPlaceholderAPI()) {
            return Utils.replaceColors(handler.getMessageFormat(p));
        } else {
            return Utils.replaceColors(PlaceholderAPI.setPlaceholders(p, handler.getMessageFormat(p)));
        }
    }

    @Override
    public String getGlobalMessageFormat(Player p) {
        if (!HookManager.checkPlaceholderAPI()) {
            return Utils.replaceColors(handler.getGlobalMessageFormat(p));
        } else {
            return Utils.replaceColors(PlaceholderAPI.setPlaceholders(p, handler.getGlobalMessageFormat(p)));
        }
    }
}
