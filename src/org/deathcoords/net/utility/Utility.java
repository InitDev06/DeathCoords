package org.deathcoords.net.utility;

import org.bukkit.entity.Player;
import org.deathcoords.net.DeathCoords;

public class Utility 
{
	
	private static DeathCoords de = DeathCoords.getPlugin(DeathCoords.class);
	
	public static void sendTitle(Player p, String ti, String su, int fadeIn, int showTime, int fadeOut)
	{
		de.getC().get().sendTitle(p, ti, su, fadeIn, showTime, fadeOut);
	}
	
	public static void sendActionBar(Player p, String ac)
	{
		de.getC().get().sendActionBar(p, ac);
	}

}
