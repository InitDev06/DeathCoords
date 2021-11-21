package org.deathcoords.net.nms;

import org.bukkit.Bukkit;
import org.deathcoords.net.DeathCoords;
import org.deathcoords.net.nms.versions.NMS_v1_10_r1;
import org.deathcoords.net.nms.versions.NMS_v1_11_r1;
import org.deathcoords.net.nms.versions.NMS_v1_12_r1;
import org.deathcoords.net.nms.versions.NMS_v1_13_r2;
import org.deathcoords.net.nms.versions.NMS_v1_14_r1;
import org.deathcoords.net.nms.versions.NMS_v1_15_r1;
import org.deathcoords.net.nms.versions.NMS_v1_16_r3;
import org.deathcoords.net.nms.versions.NMS_v1_17_r1;
import org.deathcoords.net.nms.versions.NMS_v1_8_r3;
import org.deathcoords.net.nms.versions.NMS_v1_9_r2;

public class Controller 
{
	
	private NMS nms;
	private DeathCoords dc = DeathCoords.getPlugin(DeathCoords.class);
	
	public Controller(DeathCoords dc)
	{
		this.dc = dc;
		setupVersion();
	}
	
	private void setupVersion()
	{
		String ve = null;
		
		try
		{
			ve = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			ex.printStackTrace();
			dc.getLogger().severe("=================== NMS ===================");
			dc.getLogger().severe("An occurred a error to load the required NMS class");
			dc.getLogger().severe("You server version is " + Bukkit.getBukkitVersion());
			dc.getLogger().severe("The plugin can't running without a stable version!");
			dc.getLogger().severe("=================== NMS ===================");
			Bukkit.getPluginManager().disablePlugin(dc);
		}
		
		switch(ve)
		{
			case "v1_8_R1":
				Bukkit.getScheduler().cancelTasks(dc);
				Bukkit.getPluginManager().disablePlugin(dc);
				return;
			case "v1_8_R2":
				Bukkit.getScheduler().cancelTasks(dc);
				Bukkit.getPluginManager().disablePlugin(dc);
				return;
			case "v1_8_R3":
				nms = new NMS_v1_8_r3();
				return;
			case "v1_9_R1":
				Bukkit.getScheduler().cancelTasks(dc);
				Bukkit.getPluginManager().disablePlugin(dc);
				return;
			case "v1_9_R2":
				nms = new NMS_v1_9_r2();
				return;
			case "v1_10_R1":
				nms = new NMS_v1_10_r1();
				return;
			case "v1_11_R1":
				nms = new NMS_v1_11_r1();
				return;
			case "v1_12_R1":
				nms = new NMS_v1_12_r1();
				return;
			case "v1_13_R1":
				Bukkit.getScheduler().cancelTasks(dc);
				Bukkit.getPluginManager().disablePlugin(dc);
				return;
			case "v1_13_R2":
				nms = new NMS_v1_13_r2();
				return;
			case "v1_14_R1":
				nms = new NMS_v1_14_r1();
				return;
			case "v1_15_R1":
				nms = new NMS_v1_15_r1();
				return;
			case "v1_16_R1":
				Bukkit.getScheduler().cancelTasks(dc);
				Bukkit.getPluginManager().disablePlugin(dc);
				return;
			case "v1_16_R2":
				Bukkit.getScheduler().cancelTasks(dc);
				Bukkit.getPluginManager().disablePlugin(dc);
				return;
			case "v1_16_R3":
				nms = new NMS_v1_16_r3();
				return;
			case "v1_17_R1":
				nms = new NMS_v1_17_r1();
				return;
		}
		dc.getLogger().severe("=================== Compatibility Problem ===================");
		dc.getLogger().severe("DeathCoords requires the version 1.8.8 to 1.17.1 to function.");
		dc.getLogger().severe("You server version is " + Bukkit.getBukkitVersion());
		dc.getLogger().severe("The plugin can't running on this version, so will deactived.");
		dc.getLogger().severe("=================== Compatibility Problem ===================");
		Bukkit.getPluginManager().disablePlugin(dc);
	}
	
	public NMS get()
	{
		return nms;
	}

}
