/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (HubCore) as your own
 * You are NOT allowed to publish this plugin (HubCore) or your modified version of this plugin (HubCore)
 */
package com.bradydawg.hubcore;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.bradydawg.hubcore.utils.Config;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class ChannelHandler implements PluginMessageListener {

    private static ChannelHandler INSTANCE;

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subChannel = in.readUTF();
        if (subChannel.equals("Chat")) {
            //String serverName = in.readUTF();

            short len = in.readShort();
            byte[] msgbytes = new byte[len];
            in.readFully(msgbytes);

            DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));
            String msg;
            try {
                msg = msgin.readUTF();
            } catch (IOException ex) {
                ex.printStackTrace();
                msg = "null";
            }

            HubCore.getInstance().getServer().broadcastMessage(msg);
        }
    }

    public static ChannelHandler getInstance() {
        return INSTANCE;
    }

    public static void load() {
        if (Config.BUNGEECORD.getBoolean()) {
            INSTANCE = new ChannelHandler();
            HubCore.getInstance().getServer().getMessenger().registerOutgoingPluginChannel(HubCore.getInstance(), "BungeeCord");
            HubCore.getInstance().getServer().getMessenger().registerIncomingPluginChannel(HubCore.getInstance(), "BungeeCord", INSTANCE);
        }
    }

    public void sendMessage(Player p, String message) {
        if (Config.BUNGEECORD.getBoolean()) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Forward"); // So BungeeCord knows to forward it
            out.writeUTF("ALL");
            out.writeUTF("Chat"); // The channel name to check if this your data
            //out.writeUTF("GetServer");

            ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
            DataOutputStream msgout = new DataOutputStream(msgbytes);
            try {
                msgout.writeUTF(message);
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            out.writeShort(msgbytes.toByteArray().length);
            out.write(msgbytes.toByteArray());
            p.sendPluginMessage(HubCore.getInstance(), "BungeeCord", out.toByteArray());
        }
    }

}