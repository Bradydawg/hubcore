# HubCore - Core server plugin for Spigot and Bukkit - [![pipeline status](https://gitlab.uniquedimensions.net/arcadelia/hubcore/badges/master/pipeline.svg)](https://gitlab.uniquedimensions.net/arcadelia/hubcore/-/commits/master)

## HubCore: 
Hub Core is a plugin for the core of a minecraft networks hub server. It
currenty has some good base features. Currently, the HubCore plugin has a chat
formatting feature which works with most permissions plugins to display a users
prefix, along with whatever their displayname is. It also has a name color and
chat color feature, which allows a user to change their name color or chat color
(granted that they have the correct permissions). Finally, the HubCore plugin
allows users to private message eachother with the /msg command, and allows 
administrators to monitor these conversations with the /socialspy command.

## HubCore Dependencies:
HubCore was built with weight in mind, therefore, it only relies on two plugins:
Vault, and PlaceholderAPI. Generally speaking, PlaceholderAPI is not absolutely
required, but in some cases, or with some permissions plugins, it is required.
Vault is absolutely required for HubCore to work, though it may run and some 
features may work if Vault is not installed, it may be prone to server crashes,
bugs, and unexpected errors.
**LuckPerms Users**:
If you use LuckPerms, you MUST have the LuckPerms PlaceHolderAPI expansion pack
installed for HubCore to work with LuckPerms prefixes.

## "Does HubCore not work with certain plugins?"
Yes. HubCore will not work with any chat formatting plugins, or any plugins that
modify your displayname. **Note** This only applies to plugins that modify a users displauname,
plugins that modify your prefix or suffix will NOT cause errors. HubCore will 
also not work with any plugins that use the commands listed below, such as 
private message plugins.

## "What features are you planning for HubCore?"
Currently we are planning to add a navigator compass which will allow a user
to select which server/game they want to go to. We are also working on adding
a GUI with an in-game store, a server updates section, a cosmetics menu, and a
lobby effects menu with perks for users who purchase a rank on the website.

## HubCore Commands:
```
commands:
  chat:
    description: Chat Plugin Commands
  chat clear:
    description: Clears the chat
  chat reload:
    description: Reloads the plugin
  msg:
    description: Private Message a player
    aliases: [w, whisper, tell, message, pm, pn, m]
  reply:
    description: Reply to a private message
    aliases: [r, rep, rply]
  blockmsg:
    description: Disable private messaging
    aliases: [blocktell, blockwhisper, blockpm, bmsg]
  socialspy:
    description: Spy on private messages
    aliases: [spy]
  chatcolor:
    usage: /<command>
    aliases: [color, colorchat, colourchat, chatcolour, cc, namecolor, colorname,
    namecolour, colourname, nc]
```

## HubCore Permissions
```
permissions:
  hubcore.notifyupdate:
    description: Notify if an update is needed
  hubcore.clear:
    description: Clears the chat
  hubcore.reload:
    description: Reloads the plugin
  hubcore.chat.global:
    description: Allows a user to talk in bungeecord chat
  hubcore.bypassads:
    description: Allows a user to bypass the ad blocker
  hubcore.allowchat:
    description: Allows a user to chat
  hubcore.notifyad:
    description: Notify if someone is blocked by the ad blocker
  hubcore.chat.color:
    description: Allows a user to chat with color
  hubcore.antispam.bypass:
    description: Allows a user to bypass the antispam
  ChatColor.GUI:
    description: Gives access to the /color command.
  ChatColor.*:
    description: Allows user to pick their chat color.
  Chatcolor.r:
    description: Allows a user to reset their chat color to the default color.
  NameColor.*:
    description: Allows a user to pick their name color.
  NameColor.r:
    description: Allows a user to reset their name color to the default color.
```
