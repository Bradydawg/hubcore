/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (Chat) as your own
 * You are NOT allowed to publish this plugin (Chat) or your modified version of this plugin (Nickname)
 */
package com.bradydawg.hubcore.plugins;

import com.bradydawg.hubcore.utils.Config;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Vault implements PermissionsPlugin {

    private static Chat chat = null;

    @Override
    public String getPrefix(Player p) {
        if (!Config.MULTIPREFIXES.getBoolean()) {
            return chat.getPlayerPrefix(p.getWorld().getName(), p);
        }
        String finalPrefix = "";
        int i = 0;
        for (String group : chat.getPlayerGroups(p)) {
            String groupPrefix = chat.getGroupPrefix(p.getWorld(), group);
            if (groupPrefix != null && !groupPrefix.isEmpty()) {
                if (i > 1) {
                    finalPrefix += " ";
                }
                finalPrefix += groupPrefix;
                i++;
            }
        }
        return finalPrefix;
    }

    @Override
    public String getSuffix(Player p) {
        if (!Config.MULTIPREFIXES.getBoolean()) {
            return chat.getPlayerSuffix(p.getWorld().getName(), p);
        }
        String finalSuffix = "";
        int i = 0;
        for (String group : chat.getPlayerGroups(p)) {
            String groupSuffix = chat.getGroupSuffix(p.getWorld(), group);
            if (groupSuffix != null && !groupSuffix.isEmpty()) {
                if (i > 1) {
                    finalSuffix += " ";
                }
                i++;
                finalSuffix += groupSuffix;
            }
        }
        return finalSuffix;
    }

    @Override
    public String[] getGroupNames(Player p) {
        return chat.getPlayerGroups(p.getWorld().getName(), p);
    }

    @Override
    public String getName() {
        return chat.getName();
    }

    @Override
    public String getMessageFormat(Player p) {
        return Config.FORMAT.getString();
    }

    @Override
    public String getGlobalMessageFormat(Player p) {
        return Config.GLOBALFORMAT.getString();
    }

    protected static boolean setupChat() {
        try {
            RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
            if (chatProvider != null && chatProvider.getProvider() != null) {
                chat = chatProvider.getProvider();
                return chat != null && chat.isEnabled();
            }
            return false;
        } catch (Throwable e) {
            return false;
        }
    }
}
