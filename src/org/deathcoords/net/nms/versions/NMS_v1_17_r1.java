package org.deathcoords.net.nms.versions;

import org.bukkit.entity.Player;
import org.deathcoords.net.nms.NMS;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class NMS_v1_17_r1 implements NMS 
{

	@Override
	public void sendActionBar(Player p, String ac) {
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ac).create());
	}

	@Override
	public void sendTitle(Player p, String ti, String su, int fadeIn, int showTime, int fadeOut) {
		p.sendTitle(ti, su, fadeIn, showTime, fadeOut);
	}

}
