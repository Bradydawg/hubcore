/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (HubCore) as your own
 * You are NOT allowed to publish this plugin (HubCore) or your modified version of this plugin (HubCore)
 */
package com.bradydawg.hubcore.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.bradydawg.hubcore.HubCore;

import java.util.UUID;

public class MessengerUser {
    private String uuid;
    private boolean socialSpyActive;

    public static MessengerUser getUser(Player p){
        for(MessengerUser u : HubCore.USER_STORAGE){
            if(u.uuid.equals(p.getUniqueId().toString())) return u;
        }

        MessengerUser u = new MessengerUser(p);

        HubCore.USER_STORAGE.add(u);

        return u;
    }

    public MessengerUser(Player p){
        this.uuid = p.getUniqueId().toString();
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(UUID.fromString(uuid));
    }

    public boolean isSocialSpyActive() {
        return socialSpyActive;
    }

    public void setSocialSpyActive(boolean socialSpyActive) {
        this.socialSpyActive = socialSpyActive;
    }
}
