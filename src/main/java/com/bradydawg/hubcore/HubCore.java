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
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

import com.bradydawg.hubcore.nav.*;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class HubCore extends JavaPlugin {

    private static HubCore INSTANCE;
    private UpdateChecker updatechecker = null;

    public static ArrayList<MessengerUser> USER_STORAGE;
    
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

    public static HubCore instance;
    public static boolean papi_enabled = false;

    public static ItemStack getNavigator(Player p) {
        ItemStack nav = new ItemStack(Material.COMPASS);
        ItemMeta nav_meta = nav.getItemMeta();
        nav_meta.setDisplayName(processConfigString("Navigator.Name", p));
        List<String> navlore = new ArrayList<String>();
        if (nav_meta.getLore() != null){
            navlore = nav_meta.getLore();
        }
        navlore.add("§7Click here to choose which game you");
        navlore.add("§7would like to play on Arcadelia!");
        navlore.add("§7We hope you have fun!");
        nav_meta.setLore(navlore);
        nav.setItemMeta(nav_meta);

        return nav;
    }

    public static ItemStack getShop(Player p) {
        ItemStack shop = new ItemStack(Material.EMERALD);
        ItemMeta shop_meta = shop.getItemMeta();
        shop_meta.setDisplayName(processConfigString("Shop.Name", p));
        List<String> shoplore = new ArrayList<String>();
        if (shop_meta.getLore() != null){
            shoplore = shop_meta.getLore();
        }
        shoplore.add("§7Purchase premium ranks");
        shoplore.add("§7on our server here!");
        shop_meta.setLore(shoplore);
        shop.setItemMeta(shop_meta);

        return shop;
    }

    public static ItemStack getElytra(Player p) {
        ItemStack elytra = new ItemStack(Material.ELYTRA);
        ItemMeta elytra_meta = elytra.getItemMeta();
        elytra_meta.setDisplayName(processConfigString("Elytra.Name", p));
        List<String> elytralore = new ArrayList<String>();
        if (elytra_meta.getLore() != null){
            elytralore = elytra_meta.getLore();
        }
        elytralore.add("§7Right click anywhere to equip");
        elytralore.add("§aPurchase §bDiamond §arank at");
        elytralore.add("§6store.arcadelia.net §ain order to use!");
        elytra_meta.setLore(elytralore);
        elytra.setItemMeta(elytra_meta);

        return elytra;
    }

    public static ItemStack UngetElytra(Player p) {
        ItemStack uelytra = new ItemStack(Material.ELYTRA);
        ItemMeta uelytra_meta = uelytra.getItemMeta();
        uelytra_meta.setDisplayName(processConfigString("UElytra.Name", p));
        uelytra_meta.addEnchant(Enchantment.DURABILITY , 1 , false);
        uelytra_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> uelytralore = new ArrayList<String>();
        if (uelytra_meta.getLore() != null){
            uelytralore = uelytra_meta.getLore();
        }
        uelytralore.add("§7Right click anywhere to unequip");
        uelytralore.add("§aPurchase §bDiamond §arank at");
        uelytralore.add("§6store.arcadelia.net §ain order to use!");
        uelytra_meta.setLore(uelytralore);
        uelytra.setItemMeta(uelytra_meta);

        return uelytra;
    }

    public static ItemStack getEffects(Player p) {
        ItemStack effects = new ItemStack(Material.SPLASH_POTION);
        ItemMeta effects_meta = effects.getItemMeta();
        effects_meta.setDisplayName(processConfigString("Effects.Name", p));
        effects_meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        List<String> effectslore = new ArrayList<String>();
        if (effects_meta.getLore() != null){
            effectslore = effects_meta.getLore();
        }
        effectslore.add("§7Click to open lobby effects menu!");
        effects_meta.setLore(effectslore);
        effects.setItemMeta(effects_meta);

        return effects;
    }

    public static ItemStack getLobby(Player p) {
        ItemStack lobby = new ItemStack(Material.BEACON);
        ItemMeta lobby_meta = lobby.getItemMeta();
        lobby_meta.setDisplayName(processConfigString("Lobby.Name", p));
        List<String> lobbylore = new ArrayList<String>();
        if (lobby_meta.getLore() != null){
            lobbylore = lobby_meta.getLore();
        }
        lobbylore.add("§7Click to open lobby menu!");
        lobby_meta.setLore(lobbylore);
        lobby.setItemMeta(lobby_meta);

        return lobby;
    }

    public static ItemStack getFirework(Player p) {
        ItemStack firework = new ItemStack(Material.FIREWORK_ROCKET, 64);
        ItemMeta firework_meta = firework.getItemMeta();
        firework_meta.setDisplayName(processConfigString("Firework.Name", p));
        List<String> fireworklore = new ArrayList<String>();
        if (firework_meta.getLore() != null){
            fireworklore = firework_meta.getLore();
        }
        fireworklore.add("§7Click to be launched into the air!");
        firework_meta.setLore(fireworklore);
        firework.setItemMeta(firework_meta);

        return firework;
    }

    public static String processString(String s, Player p) {
        if(papi_enabled == true && p != null) {
            return PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', s));
        }else {
            return ChatColor.translateAlternateColorCodes('&', s);
        }
    }


    public static String processConfigString(String s, Player p) {
        return processString(HubCore.instance.getConfig().getString(s), p);
    }

    @Override
    public void onEnable() {

        getLogger().log(Level.INFO, "Arcadelia Hub Core has begun enabling...");
        
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
        

        instance = this;

        //Configuration Value Check
        if(getConfig().getString("Navigator.Name") == null) {
            getConfig().set("Navigator.Name", "&3&k&l::&b&lGame Menu&3&k&l::");
        }

        if(getConfig().getString("Shop.Name") == null) {
            getConfig().set("Shop.Name", "§2§k§l::§a§lArcadelia Store§2§k§l::");
        }

        if(getConfig().getString("Elytra.Name") == null) {
            getConfig().set("Elytra.Name", "§7§k§l::§f§lEquip Elytra§7§k§l::");
        }

        if(getConfig().getString("UElytra.Name") == null) {
            getConfig().set("UElytra.Name", "§7§k§l::§f§lUnEquip Elytra§7§k§l::");
        }

        if(getConfig().getString("Effects.Name") == null) {
            getConfig().set("Effects.Name", "§6§k§l::§e§lLobby Effects§6§l§k::");
        }

        if(getConfig().getString("Lobby.Name") == null) {
            getConfig().set("Lobby.Name", "§1§k§l::§9§lLobbies§1§k§l::");
        }

        if(getConfig().getString("Navigator.DiscordLink") == null) {
            getConfig().set("Navigator.DiscordLink", "&7https://discord.gg/invitecodehere");
        }

        if(getConfig().getString("Navigator.ForumsLink") == null) {
            getConfig().set("Navigator.ForumsLink", "&7https://arcadelia.net");
        }

        if(getConfig().getString("Navigator.GoldenAxeName") == null) {
            getConfig().set("Navigator.GoldenAxeName", "&aWelcome to Arcadelia!");
        }

        if(getConfig().getString("Firework.Name") == null) {
            getConfig().set("Firework.Name", "&aFly firework!");
        }

        if(getConfig().getStringList("Navigator.GoldenAxeMsg") == null || getConfig().getStringList("Navigator.GoldenAxeMsg").isEmpty()) {
            List<String> gam = new ArrayList<String>();
            gam.add("&7Select a gamemode to be placed into the game queue!");
            getConfig().set("Navigator.GoldenAxeMsg", gam);
        }

        if(getConfig().getStringList("Navigator.EndCrystalMsg") == null || getConfig().getStringList("Navigator.EndCrystalMsg").isEmpty()) {
            List<String> ecm = new ArrayList<String>();
            ecm.add("&9Welcome to Arcadelia!");
            ecm.add("&eDiscord: &7https://discord.gg/invitecodehere");
            ecm.add("&eForums: &7https://arcadelia.net");
            getConfig().set("Navigator.EndCrystalMsg", ecm);
        }

        if(getConfig().getString("Navigator.EmeraldName") == null) {
            getConfig().set("Navigator.EmeraldName", "&2&k&l::&a&lArcadelia Store&2&k&l::");
        }

        if(getConfig().getStringList("Navigator.EmeraldMsg") == null || getConfig().getStringList("Navigator.EmeraldMsg").isEmpty()) {
            List<String> em = new ArrayList<String>();
            em.add("&aCheck out our server store @ §6store.arcadelia.net");
            getConfig().set("Navigator.EmeraldMsg", em);
        }

        if(getConfig().getBoolean("Hub.BlocksFade") != true && getConfig().getBoolean("Hub.BlocksFade") != false) {
            getConfig().set("Hub.BlocksFade", false);
        }

        if(getConfig().getBoolean("Hub.BlocksGrow") != true && getConfig().getBoolean("Hub.BlocksGrow") != false) {
            getConfig().set("Hub.BlocksGrow", false);
        }

        if(getConfig().getBoolean("Hub.BlocksSpread") != true && getConfig().getBoolean("Hub.BlocksSpread") != false) {
            getConfig().set("Hub.BlocksSpread", false);
        }

        //Save Changes
        saveConfig();
        reloadConfig();

        //Check if PlaceholderAPI is enabled
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            papi_enabled = true;
        }

        //Register events
        Bukkit.getPluginManager().registerEvents(new Navigator(), this);
        Bukkit.getPluginManager().registerEvents(new Shop(), this);
        Bukkit.getPluginManager().registerEvents(new Elytra(), this);
        Bukkit.getPluginManager().registerEvents(new Effects(), this);
        Bukkit.getPluginManager().registerEvents(new Lobby(), this);
        Bukkit.getPluginManager().registerEvents(new Firework(), this);

        //Register commands
        getCommand("navigator").setExecutor(new NavigatorCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());

        getLogger().log(Level.INFO, "Arcadelia Hub Core is done enabling!");

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