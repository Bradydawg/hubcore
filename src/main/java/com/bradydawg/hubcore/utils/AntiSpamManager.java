/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (Chat) as your own
 * You are NOT allowed to publish this plugin (Chat) or your modified version of this plugin (Nickname)
 */
package com.bradydawg.hubcore.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.bukkit.entity.Player;

public class AntiSpamManager {

    private static final Map<Player, Long> map = new HashMap<>();

    public static void put(Player chatter) {
        if (map.containsKey(chatter)) {
            map.remove(chatter);
        }

        map.put(chatter, System.currentTimeMillis());
    }

    public static boolean isAllowed(Player chatter) {
        if (!map.containsKey(chatter) || !Config.ANTISPAM_ENABLED.getBoolean() || chatter.hasPermission("hubcore.antispam.bypass")) {
            return true;
        }

        long lastChat = map.get(chatter) + (Config.ANTISPAM_SECONDS.getInt() * 1000);
        long current = System.currentTimeMillis();

        return current > lastChat;
    }

    public static long getRemaingSeconds(Player chatter) {
        if (isAllowed(chatter)) {
            return 0;
        }

        long lastChat = map.get(chatter) + (Config.ANTISPAM_SECONDS.getInt() * 1000);
        long current = System.currentTimeMillis();

        long diff = lastChat - current;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
        return seconds;
    }

}
