/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (HubCore) as your own
 * You are NOT allowed to publish this plugin (HubCore) or your modified version of this plugin (HubCore)
 */
package com.bradydawg.hubcore;

import com.bradydawg.hubcore.utils.Config;
import com.bradydawg.hubcore.utils.HookManager;
import com.bradydawg.hubcore.utils.Locales;
import com.bradydawg.hubcore.utils.UpdateChecker;
import com.bradydawg.hubcore.utils.Utils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        if (Config.CHANGE_JOIN_AND_QUIT.getBoolean()) {
            String msg = Locales.PLAYER_JOIN.getString(e.getPlayer());
            e.setJoinMessage(Utils.replacePlayerPlaceholders(e.getPlayer(), msg));
        }

        if (Config.CHANGE_TABLIST_NAME.getBoolean()) {
            String name = Config.TABLIST_FORMAT.getString();

            if (HookManager.checkPlaceholderAPI()) {
                name = PlaceholderAPI.setPlaceholders(e.getPlayer(), name);
            }

            name = Utils.replacePlayerPlaceholders(e.getPlayer(), name);

            e.getPlayer().setPlayerListName(name);
        }

        if (Config.CHECK_UPDATE.getBoolean() && e.getPlayer().hasPermission("hubcore.notifyupdate") && HubCore.getInstance().getUpdateChecker() != null) {
            if (HubCore.getInstance().getUpdateChecker().getResult() == UpdateChecker.Result.UPDATE_FOUND) {
                e.getPlayer().sendMessage(Locales.UPDATE_FOUND.getString(null).replaceAll("%oldversion", HubCore.getInstance().getDescription().getVersion()).replaceAll("%newversion", HubCore.getInstance().getUpdateChecker().getVersion()));
            } else if (HubCore.getInstance().getUpdateChecker().getResult() == UpdateChecker.Result.SUCCESS) {
                e.getPlayer().sendMessage(Locales.UPDATE_DOWNLOADED.getString(null));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(final PlayerQuitEvent e) {
        if (!Config.CHANGE_JOIN_AND_QUIT.getBoolean()) {
            return;
        }
        String msg = Locales.PLAYER_QUIT.getString(e.getPlayer());
        e.setQuitMessage(Utils.replacePlayerPlaceholders(e.getPlayer(), msg));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onKick(final PlayerKickEvent e) {
        if (!Config.CHANGE_JOIN_AND_QUIT.getBoolean()) {
            return;
        }
        String msg = Locales.PLAYER_KICK.getString(e.getPlayer());
        e.setLeaveMessage(Utils.replacePlayerPlaceholders(e.getPlayer(), msg));
    }

}
