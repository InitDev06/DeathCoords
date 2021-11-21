package org.deathcoords.net.nms;

import org.bukkit.entity.Player;

public interface NMS 
{

	void sendActionBar(Player p, String ac);
	
	void sendTitle(Player p, String ti, String su, int fadeIn, int showTime, int fadeOut);
	
}
