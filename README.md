# HubCore - Core server plugin for Spigot and Bukkit - [![pipeline status](https://gitlab.uniquedimensions.net/arcadelia/hubcore/badges/master/pipeline.svg)](https://gitlab.uniquedimensions.net/arcadelia/hubcore/-/commits/master)

**HubCore Latest Version:** - [![version](https://img.shields.io/badge/version-v1.0-blue)](https://gitlab.uniquedimensions.net/arcadelia/hubcore/-/releases/v1.0)

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
modify a users displayname. **Note** This only applies to plugins that modify a 
users displayname, plugins that modify a users prefix or suffix will NOT cause 
errors. HubCore will also not work with any plugins that use the commands listed
below, such as private message plugins.

## "What features are you planning for HubCore?"
Nothing at the moment! Stay tuned.

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
  navigator:
    usage: /<command>
    description: Opens the navigation GUI.
  spawn:
    usage: /<command>
    description: Teleports you to the spawn.
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
  hubcore.elytra:
    description: Allows the use of the equipable elytra
  ChatColor.GUI:
    description: Gives access to the /color command
  ChatColor.*:
    description: Allows user to use all chat colors
  ChatColor.DARK_RED:
    description: Allows user to use the dark red chat color
  ChatColor.RED:
    description: Allows user to use the red chat color
  ChatColor.GOLD:
    description: Allows user to use the gold chat color
  ChatColor.YELLOW:
    description: Allows user to use the yellow chat color
  ChatColor.GREEN:
    description: Allows user to use the green chat color
  ChatColor.DARK_GREEN:
    description: Allows user to use the dark green chat color
  ChatColor.DARK_BLUE:
    description: Allows user to use the dark blue chat color
  ChatColor.BLUE:
    description: Allows user to use the blue chat color
  ChatColor.DARK_AQUA:
    description: Allows user to use the dark aqua chat color
  ChatColor.AQUA:
    description: Allows user to use the aqua chat color
  ChatColor.DARK_PURPLE:
    description: Allows user to use the purple chat color
  ChatColor.LIGHT_PURPLE:
    description: Allows user to use the light purple chat color
  ChatColor.WHITE:
    description: Allows user to use the white chat color
  ChatColor.GRAY:
    description: Allows user to use the gray chat color
  ChatColor.DARK_GRAY:
    description: Allows user to use the dark gray chat color
  ChatColor.BLACK:
    description: Allows user to use the black chat color
  Chatcolor.r:
    description: Allows a user to reset their chat color to the default color.
  NameColor.*:
    description: Allows a user to use any name color
  NameColor.DARK_RED:
    description: Allows user to use the dark red name color
  NameColor.RED:
    description: Allows user to use the red name color
  NameColor.GOLD:
    description: Allows user to use the gold name color
  NameColor.YELLOW:
    description: Allows user to use the yellow name color
  NameColor.GREEN:
    description: Allows user to use the green name color
  NameColor.DARK_GREEN:
    description: Allows user to use the dark green name color
  NameColor.DARK_BLUE:
    description: Allows user to use the dark blue name color
  NameColor.BLUE:
    description: Allows user to use the blue name color
  NameColor.DARK_AQUA:
    description: Allows user to use the dark aqua name color
  NameColor.AQUA:
    description: Allows user to use the aqua name color
  NameColor.DARK_PURPLE:
    description: Allows user to use the purple name color
  NameColor.LIGHT_PURPLE:
    description: Allows user to use the light purple name color
  NameColor.WHITE:
    description: Allows user to use the white name color
  NameColor.GRAY:
    description: Allows user to use the gray name color
  NameColor.DARK_GRAY:
    description: Allows user to use the dark gray name color
  NameColor.BLACK:
    description: Allows user to use the black name color
  NameColor.r:
    description: Allows a user to reset their name color to the default color.
```
