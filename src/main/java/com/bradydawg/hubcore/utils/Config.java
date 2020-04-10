/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (Chat) as your own
 * You are NOT allowed to publish this plugin (Chat) or your modified version of this plugin (Nickname)
 */
package com.bradydawg.hubcore.utils;

import com.bradydawg.hubcore.HubCore;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Config {

    CHECK_UPDATE("check-for-updates", false, "Should the plugin check for updates by itself?"),
    BUNGEECORD("bungeecord", false, "If you use bungeecord, players can chat cross-server wide with the range mode (! in front of the message)."),
    FORMAT("message-format", "%prefix%displayname%suffix &8» &7%message", "The standard message-format."),
    GLOBALFORMAT("global-message-format", "%prefix%displayname%suffix &8» &7%message", "The message-format if ranged-mode is enabled."),
    MULTIPREFIXES("multi-prefixes", false, "Should the multi-prefixes be enabled?"),
    MULTISUFFIXES("multi-suffixes", false, "Should the multi-suffixes be enabled?"),
    RANGEMODE("ranged-mode", false, "Should the ranged-mode be enabled?"),
    SHOW_NO_RECEIVER_MSG("show-no-players-near", true, "Should we check if any player would receiver your chat message?"),
    RANGE("chat-range", 100, "The range to talk to other players. Set to -1 to enable world-wide-chat"),
    LOGCHAT("logChat", false, "Should the chat be logged?"),
    LOCALE("Locale", "en-EN", "Which language do you want? (Just kidding, please speak English on our server.)"),
    ADS_ENABLED("Ads.Enabled", true, "Should we check for ads?"),
    ADS_BYPASS("Ads.Bypass", Arrays.asList("127.0.0.1", "my-domain.com"), "A list with allowed ips or domains."),
    ADS_LOG("Ads.Log", true, "Should the ads be logged in a file?"),
    ANTISPAM_SECONDS("AntiSpam.Seconds", 5, "The delay between player messages to prevent spam"),
    ANTISPAM_ENABLED("AntiSpam.Enable", true, "Should antispam be enabled?"),
    BLOCKED_WORDS("BlockedWords", Arrays.asList("shit", "@everyone"), "A list of words that should be blocked."),
    CHANGE_TABLIST_NAME("Tablist.Change", true, "Do you want to have the prefixes and suffixes in the tablist?"),
    TABLIST_FORMAT("Tablist.format", "%prefix%player%suffix", "The format of the tablist name"),
    CHANGE_JOIN_AND_QUIT("Messages.JoinAndQuit.Enabled", false, "Do you want to change the join and the quit messages?");

    private final Object value;
    private final String path;
    private final String description;
    private static YamlConfiguration cfg;
    private static final File f = new File(HubCore.getInstance().getDataFolder(), "config.yml");

    private Config(String path, Object val, String description) {
        this.path = path;
        this.value = val;
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public Object getDefaultValue() {
        return value;
    }

    public boolean getBoolean() {
        return cfg.getBoolean(path);
    }

    public double getDouble() {
        return cfg.getDouble(path);
    }

    public int getInt() {
        return cfg.getInt(path);
    }

    public String getString() {
        return Utils.replaceColors(cfg.getString(path));
    }

    public List<String> getStringList() {
        return cfg.getStringList(path);
    }

    public static void load() {
        HubCore.getInstance().getDataFolder().mkdirs();
        reload(false);
        String header = "";
        for (Config c : values()) {
            header += c.getPath() + ": " + c.getDescription() + System.lineSeparator();
            if (!cfg.contains(c.getPath())) {
                c.set(c.getDefaultValue(), false);
            }
        }
        cfg.options().header(header);
        try {
            cfg.save(f);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void set(Object value, boolean save) {
        cfg.set(path, value);
        if (save) {
            try {
                cfg.save(f);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            reload(false);
        }
    }

    public static void reload(boolean complete) {
        if (!complete) {
            cfg = YamlConfiguration.loadConfiguration(f);
            return;
        }
        load();
    }
}
