/*
 * All rights by Bradydawg (2020)
 * You are NOT allowed to modify this code unless you talk to Bradydawg beforehand
 * You are NOT allowed to claim this plugin (HubCore) as your own
 * You are NOT allowed to publish this plugin (HubCore) or your modified version of this plugin (HubCore)
 */
package com.bradydawg.hubcore.utils;

import java.util.regex.Pattern;

public class Util {
    public static boolean containsLink(String message){
        return Pattern.compile("^((https?|ftp):\\/\\/|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([\\/?].*)?$").matcher(message).find();
    }
}
