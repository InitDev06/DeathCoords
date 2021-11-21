package org.deathcoords.net;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.deathcoords.net.color.Color;
import org.deathcoords.net.listeners.PlayerListener;
import org.deathcoords.net.nms.Controller;

@SuppressWarnings("unused")
public class DeathCoords extends JavaPlugin 
{
	
	private Class<?> cl;
	private FileConfiguration cfg;
	private FileConfiguration msg;
	private PluginManager pm;
	private Controller c;
	private String se = Bukkit.getBukkitVersion();
	private String ve = getDescription().getVersion();
	
	public String getSe()
	{
		return se;
	}
	
	public String getVe()
	{
		return ve;
	}
	
	public Controller getC()
	{
		return c;
	}
	
	@Override
	public void onEnable() 
	{
		long startTime = System.currentTimeMillis();
		try
		{
			cl = Class.forName("org.spigotmc.SpigotConfig");
			Bukkit.getConsoleSender().sendMessage(Color.tr("&7[&cDeathEvents&7] Loading Files.."));
			create("config");
			create("lang");
			cfg = getFile("config");
			msg = getFile("lang");
			String message = "&7[&cDeathEvents&7] &aFiles loaded in &e" + (System.currentTimeMillis() - startTime) + "" + " &ams!";
			message = Color.tr(message);
			Bukkit.getConsoleSender().sendMessage(message);
			Bukkit.getConsoleSender().sendMessage(Color.tr("&7[&cDeathEvents&7] Loading Components.."));
			hasCommands();
			hasListeners();
			Bukkit.getConsoleSender().sendMessage(Color.tr("&7[&cDeathEvents&7] &aLoaded components!"));
			Bukkit.getConsoleSender().sendMessage(Color.tr("&7[&cDeathEvents&7] Version &6" + ve));
			c = new Controller(this);
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public void onDisable() {}
	
	private void hasCommands()
	{
		
	}
	
	private void hasListeners()
	{
		pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListener(this), this);
	}
	
	private void create(String name)
	{
		File fl = new File(getDataFolder(), name + ".yml");
		if(!(fl.exists()))
		{
			saveResource(name + ".yml", false);
		}
	}
	
	private FileConfiguration getFile(String name)
	{
		return YamlConfiguration.loadConfiguration(new File(getDataFolder(), name + ".yml"));
	}
	
	public void reloadConf()
	{
		cfg = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
	}
	
	public void reloadLang()
	{
		msg = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "lang.yml"));
	}
	
	public void saveConf()
	{
		try
		{
			cfg.save("config.yml");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void saveLang()
	{
		try
		{
			msg.save("lang.yml");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public FileConfiguration getConfigFile()
	{
		return cfg;
	}
	
	public FileConfiguration getLangFile()
	{
		return msg;
	}
}
