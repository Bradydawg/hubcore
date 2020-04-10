/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (HubCore) as your own
 * You are NOT allowed to publish this plugin (HubCore) or your modified version of this plugin (HubCore)
 */
package com.bradydawg.hubcore;

import com.bradydawg.hubcore.plugins.PluginManager;
import com.bradydawg.hubcore.utils.AntiSpamManager;
import com.bradydawg.hubcore.utils.ChatLogger;
import com.bradydawg.hubcore.utils.Config;
import com.bradydawg.hubcore.utils.Locales;
import com.bradydawg.hubcore.utils.Utils;
import java.util.UnknownFormatConversionException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onChat(final AsyncPlayerChatEvent event) {
        if (!event.getPlayer().hasPermission("hubcore.allowchat")) {
            String msg = Locales.COMMAND_RESULT_NO_PERM.getString(event.getPlayer()).replaceAll("%perm", "hubcore.allowchat");
            event.getPlayer().sendMessage(msg);
            event.setCancelled(true);
            return;
        }

        if (!AntiSpamManager.isAllowed(event.getPlayer())) {
            event.getPlayer().sendMessage(Locales.ANTI_SPAM_DENIED.getString(event.getPlayer()).replaceAll("%time%", AntiSpamManager.getRemaingSeconds(event.getPlayer()) + ""));
            event.setCancelled(true);
            return;
        }
        AntiSpamManager.put(event.getPlayer());

        String format = PluginManager.getInstance().getMessageFormat(event.getPlayer());
        Player player = event.getPlayer();
        String chatMessage = event.getMessage();

        if (Utils.checkForAds(chatMessage, player)) {
            event.getPlayer().sendMessage(Locales.MESSAGES_AD.getString(null).replaceAll("%perm", "hubcore.bypassads"));
            event.setCancelled(true);
            return;
        }

        if (Utils.checkForBlocked(event.getMessage())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Locales.MESSAGES_BLOCKED.getString(null));
            return;
        }

        boolean global = false;
        if (Config.RANGEMODE.getBoolean() || Config.BUNGEECORD.getBoolean()) {
            if (chatMessage.startsWith("!")) {
                if (player.hasPermission("hubcore.chat.global")) {
                    chatMessage = chatMessage.replaceFirst("!", "");
                    format = PluginManager.getInstance().getGlobalMessageFormat(event.getPlayer());
                    global = true;
                } else {
                    player.sendMessage(Locales.COMMAND_RESULT_NO_PERM.getString(player).replaceAll("%perm", "hubcore.chat.global"));
                    event.setCancelled(true);
                    return;
                }
            } else {
                if (Config.RANGEMODE.getBoolean()) {
                    global = false;
                    event.getRecipients().clear();
                    if (Utils.getLocalRecipients(player).size() == 1 && Config.SHOW_NO_RECEIVER_MSG.getBoolean()) {
                        player.sendMessage(Locales.NO_LISTENING_PLAYERS.getString(player));
                        event.setCancelled(true);
                        return;
                    } else {
                        event.getRecipients().addAll(Utils.getLocalRecipients(player));
                    }
                }
            }
        }

        if (global && Config.BUNGEECORD.getBoolean()) {
            String msgToSend = Utils.replacePlayerPlaceholders(player, format.replaceAll("%message", Utils.translateColorCodes(chatMessage, player)));
            ChannelHandler.getInstance().sendMessage(player, msgToSend);
        }

        format = format.replace("%message", "%2$s");
        format = Utils.replacePlayerPlaceholders(player, format);

        try {
            event.setFormat(format);
        } catch (UnknownFormatConversionException ex) {
            HubCore.getInstance().getLogger().severe("Placeholder in format is not allowed!");
            format = format.replaceAll("%\\\\?.*?%", "");
            event.setFormat(format);
        }

        event.setMessage(Utils.translateColorCodes(chatMessage, player));
        ChatLogger.writeToFile(event.getPlayer(), event.getMessage());
    }

}
