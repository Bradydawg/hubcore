/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (Chat) as your own
 * You are NOT allowed to publish this plugin (Chat) or your modified version of this plugin (Nickname)
 */
package com.bradydawg.hubcore.plugins;

import org.bukkit.entity.Player;

public interface PermissionsPlugin {

    public String getName();

    public String getPrefix(Player p);

    public String getSuffix(Player p);

    public String[] getGroupNames(Player p);

    public String getMessageFormat(Player p);

    public String getGlobalMessageFormat(Player p);
}
