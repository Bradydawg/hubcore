package com.bradydawg.hubcore.utils;

public class PermissionNode {

	public static final String CMD_MSG;
	public static final String CMD_MSG_COLOR;
	public static final String CMD_REPLY;
	public static final String CMD_BLOCKMSG;
	public static final String CMD_BLOCKMSG_EXEMPT;
	public static final String CMD_SOCIALSPY;
	public static final String CMD_CHATRELOAD;
	public static final String CMD_MSG_SEND_LINKS;
	
	static {
		CMD_MSG = "privatemessage.cmd.msg";
		CMD_MSG_COLOR = "privatemessage.cmd.msg.color";
		CMD_REPLY = "privatemessage.cmd.reply";
		CMD_BLOCKMSG = "privatemessage.cmd.blockmsg";
		CMD_BLOCKMSG_EXEMPT = "privatemessage.cmd.blockmsg.exempt";
		CMD_SOCIALSPY = "privatemessage.cmd.socialspy";
		CMD_CHATRELOAD = "privatemessage.cmd.chatreload";
		CMD_MSG_SEND_LINKS = "privatemessage.sendLinks";
	}

}
