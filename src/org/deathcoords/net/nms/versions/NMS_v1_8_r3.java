package org.deathcoords.net.nms.versions;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.deathcoords.net.nms.NMS;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class NMS_v1_8_r3 implements NMS 
{

	@SuppressWarnings("rawtypes")
	@Override
	public void sendActionBar(Player p, String ac) {
		IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ac + "\"}");
	    PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
	    (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)bar);
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
