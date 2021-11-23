package org.deathcoords.net.nms.versions;

import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.deathcoords.net.nms.NMS;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.Packet;
import net.minecraft.server.v1_13_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_13_R2.PlayerConnection;

public class NMS_v1_13_r2 implements NMS 
{
	
	@Override
	public void sendActionBar(Player p, String ac) {
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(ac).create());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void sendTitle(Player p, String ti, String su, int fadeIn, int showTime, int fadeOut) {
		 PlayerConnection pConn = (((CraftPlayer)p).getHandle()).playerConnection;
		 PacketPlayOutTitle pTitleInfo = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, showTime, fadeOut);
		 pConn.sendPacket((Packet)pTitleInfo);
		 if (su != null) 
		 {
			 IChatBaseComponent iComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + su + "\"}");
			 PacketPlayOutTitle pSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, iComp);
			 pConn.sendPacket((Packet)pSubtitle);
		 } 
		 if (ti != null) 
		 {
		     IChatBaseComponent iComp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ti + "\"}");
		     PacketPlayOutTitle pTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, iComp);
		     pConn.sendPacket((Packet)pTitle);
		 } 
	}

}
