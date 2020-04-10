/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (HubCore) as your own
 * You are NOT allowed to publish this plugin (HubCore) or your modified version of this plugin (HubCore)
 */
package com.bradydawg.hubcore;

import com.bradydawg.hubcore.utils.Locales;
import com.bradydawg.hubcore.plugins.PluginManager;
import com.bradydawg.hubcore.utils.ChatLogger;
import com.bradydawg.hubcore.utils.Config;
import com.bradydawg.hubcore.utils.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.bradydawg.hubcore.user.MessengerUser;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.bradydawg.hubcore.menu.Click_Listeners;
import com.bradydawg.hubcore.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.bradydawg.hubcore.utils.PermissionNode;

public class HubCore extends JavaPlugin {

    private static HubCore INSTANCE;
    private UpdateChecker updatechecker = null;

    public static ArrayList<MessengerUser> USER_STORAGE;

    private static HubCore instance;
    public static ArrayList<Player> BLOCK_MSG = new ArrayList<Player>();
    public static HashMap<Player, Player> REPLY = new HashMap<Player, Player>();
    public static ArrayList<CommandSender> SOCIAL_SPY = new ArrayList<CommandSender>();

    public static final Gson GSON = new Gson();

    private static String path;

    private static String dataPath;

    private static FileConfiguration config;

    public static String getDataPath() {
        return dataPath;
    }

    public static FileConfiguration getConfiguration() {
        return config;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;

        Config.load();
        Locales.load();
        PluginManager.load();
        ChatLogger.load();

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("chat").setExecutor(new CommandHandler());

        if (Config.CHECK_UPDATE.getBoolean()) {
            updatechecker = new UpdateChecker(this, 00000);
        }

        ChannelHandler.load();

        getLogger().info("is now enabled!");

        path = getInstance().getDataFolder().getAbsolutePath() + (getInstance().getDataFolder().getAbsolutePath().endsWith("/") ? "" : "/") + "userData.json";

        registerCommands();
        registerListeners();
        saveDefaultConfig();

        getConfig().addDefault("cmd.msg.format.meTo","&6[&bMe &6→ &a%displayname%&6] &f%message%");
        getConfig().addDefault("cmd.msg.format.toMe","&6[&a%displayname% &6→ &bMe&6] &f%message%");
        getConfig().addDefault("cmd.msg.mayNotSendLinks","&cYou are not allowed to send links via private message.");
        getConfig().addDefault("cmd.msg.usage","&c/%label% <User> <Message>");

        getConfig().addDefault("cmd.reply.usage","&c/%label% <Message>");
        getConfig().addDefault("cmd.reply.noMessageSent","&cYou have to send a message before you can use /reply");

        getConfig().addDefault("cmd.blockmsg.activated","&cYou can no longer receive private messages!");
        getConfig().addDefault("cmd.blockmsg.deactivated","&aYou can now receive private messages again!");

        getConfig().addDefault("cmd.socialspy.on","&aSocial-Spy is now active!");
        getConfig().addDefault("cmd.socialspy.off","&cSocial-Spy is no longer active!");
        getConfig().addDefault("cmd.socialspy.msg","&6[&b%player1% &6→ &a%player2%&6] &f%message%");

        getConfig().addDefault("player.notOnline","&cPlayer '%player%' is offline!");
        getConfig().addDefault("player.noPermission","&cYou do not have permission!");
        getConfig().addDefault("player.blockingMessages","&c%displayname% is blocking private messages!");

        try {
            File file = new File(path);
            if(file.exists()){
                USER_STORAGE = GSON.fromJson(new JsonReader(new FileReader(path)),new TypeToken<ArrayList<MessengerUser>>(){}.getType());
            } else {
                file.createNewFile();
            }
        } catch(Exception e){
            System.out.println("An error occurred trying to read userData.json, defaulting to empty storage.");
        }

        if(USER_STORAGE == null) USER_STORAGE = new ArrayList<MessengerUser>();

        config = getConfig();
        saveDefaultConfig();
        dataPath = getDataFolder().getPath() + "/data";
        dataPath = getDataPath();
        Bukkit.getOnlinePlayers().forEach(player -> new HMPlayer(player.getUniqueId()));
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new Click_Listeners(), (Plugin)this);
        getCommand("chatcolor").setExecutor(new ChatColorCommand());

    }

    @Override
    public void onDisable() {
        ChatLogger.close();
        getServer().getScheduler().cancelTasks(this);
        getLogger().info("is now disabled!");
        saveData();
        Menu.onDisable();
    }

    public static void saveData(){
        try(Writer writer = new FileWriter(path)){
            GSON.toJson(USER_STORAGE,writer);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static HubCore getInstance() {
        return INSTANCE;
    }

    public UpdateChecker getUpdateChecker() {
        return this.updatechecker;
    }

    private void registerCommands(){
        new PrivateMessageExecutor();
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new PrivateMessageListener(),this);
    }

    public boolean maySendMessage(Player p, Player p2){
        boolean b = true;

        if(!p.hasPermission(PermissionNode.CMD_BLOCKMSG_EXEMPT)){
            if(BLOCK_MSG.contains(p2)){
                b = false;
            }
        }

        return b;
    }

    public static String toRainbow(String message) {
        String ret = "";
        ChatColor[] rainbowColors = { ChatColor.RED, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.AQUA, ChatColor.LIGHT_PURPLE };
        int colorCount = 0;
        for (int i = 0; i < message.length(); i++) {
            ret = ret + rainbowColors[colorCount % rainbowColors.length] + "" + message.charAt(i);
            if (!Character.isWhitespace(message.charAt(i)))
                colorCount++;
        }
        return ret;
    }
}