package org.deathcoords.net.cmds;

import java.util.Optional;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.deathcoords.net.DeathCoords;
import org.deathcoords.net.color.Color;
import org.deathcoords.net.utility.Utility;
import org.deathcoords.net.xseries.XSound;

public class MainCMD implements CommandExecutor 
{
	
	private DeathCoords dc = DeathCoords.getPlugin(DeathCoords.class);
	private Sound sound;
	private Sound sound2;
	private int volumen;
	private int pitch;
	
	public MainCMD(DeathCoords dc)
	{
		Optional<XSound> xs = XSound.matchXSound(dc.getConfigFile().getString("options.sounds.permission"));
		Optional<XSound> xs2 = XSound.matchXSound(dc.getConfigFile().getString("options.sounds.reload"));
	    if (xs.isPresent()) 
	    {
	    	this.sound = ((XSound)xs.get()).parseSound();
	    	this.sound2 = ((XSound)xs2.get()).parseSound();
	    } 
	    else 
	    {
	    	this.sound = XSound.ENTITY_ITEM_BREAK.parseSound();
	    	this.sound2 = XSound.ENTITY_IRON_GOLEM_REPAIR.parseSound();
	    } 
	    this.volumen = dc.getConfigFile().getInt("options.sounds.volumen");
	    this.pitch = dc.getConfigFile().getInt("options.sounds.pitch");
		this.dc = dc;
		dc.getCommand("deathcoords").setExecutor(this);
	}
	
	private Sound getSound()
	{
		return sound;
	}
	
	private Sound getSecondarySound()
	{
		return sound2;
	}
	
	private int getVolumen()
	{
		return volumen;
	}
	
	private int getPitch()
	{
		return pitch;
	}
	
	@Override
	public boolean onCommand(CommandSender se, Command cmd, String la, String[] args) {
		FileConfiguration cfg = dc.getConfigFile();
		FileConfiguration msg = dc.getLangFile();
		String pr = msg.getString("general.prefix");
		if(se instanceof Player)
		{
			Player p = (Player) se;
			if(p.hasPermission("deathcoords.command.admin") || p.isOp())
			{
				if(args.length == 0)
				{
					p.sendMessage(Color.tr(pr + msg.getString("general.command.usage")));
					return true;
				}
				
				long reloadTime = System.currentTimeMillis();
				switch(args[0])
				{
					case "reload":
						if(args.length == 1)
						{
							dc.reloadConf();
							dc.reloadLang();
							if(cfg.getBoolean("options.enable_titles"))
							{
								String ti = msg.getString("general.command.title_reload_all");
								String su = msg.getString("general.command.subtitle_reload_all");
								
								ti = Color.tr(ti);
								su = Color.tr(su);
								ti = ti.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
								su = su.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
								
								int fadeIn = cfg.getInt("options.titles.fadeIn");
								int showTime = cfg.getInt("options.titles.showTime");
								int fadeOut = cfg.getInt("options.titles.fadeOut");
								
								Utility.sendTitle(p, ti, su, fadeIn, showTime, fadeOut);
							}
							
							if(cfg.getBoolean("options.enable_sounds"))
							{
								executeSecondary(p);
							}
							
							if(cfg.getBoolean("options.enable_actionbar"))
							{
								String ac = msg.getString("general.command.actionbar_reload_all");
								
								ac = Color.tr(ac);
								ac = ac.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
								
								Utility.sendActionBar(p, ac);
							}
							
							String message = msg.getString("general.command.reload_all");
							
							message = message.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
							
							p.sendMessage(Color.tr(pr + message));
							return true;
						}
						
						if(args[1].equalsIgnoreCase("config"))
						{
							if(cfg.getBoolean("options.enable_titles"))
							{
								String ti = msg.getString("general.command.title_reload_config");
								String su = msg.getString("general.command.subtitle_reload_config");
								
								ti = Color.tr(ti);
								su = Color.tr(su);
								ti = ti.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
								su = su.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
								
								int fadeIn = cfg.getInt("options.titles.fadeIn");
								int showTime = cfg.getInt("options.titles.showTime");
								int fadeOut = cfg.getInt("options.titles.fadeOut");
								
								Utility.sendTitle(p, ti, su, fadeIn, showTime, fadeOut);
							}
							
							if(cfg.getBoolean("options.enable_sounds"))
							{
								executeSecondary(p);
							}
							
							if(cfg.getBoolean("options.enable_actionbar"))
							{
								String ac = msg.getString("general.command.actionbar_reload_config");
								
								ac = Color.tr(ac);
								ac = ac.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
								
								Utility.sendActionBar(p, ac);
							}
							
							dc.reloadConf();
							String message = msg.getString("general.command.reload_config");
							
							message = message.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
							
							p.sendMessage(Color.tr(pr + message));
							return true;
						}
						
						if(args[1].equalsIgnoreCase("lang"))
						{
							if(cfg.getBoolean("options.enable_titles"))
							{
								String ti = msg.getString("general.command.title_reload_lang");
								String su = msg.getString("general.command.subtitle_reload_lang");
								
								ti = Color.tr(ti);
								su = Color.tr(su);
								ti = ti.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
								su = su.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
								
								int fadeIn = cfg.getInt("options.titles.fadeIn");
								int showTime = cfg.getInt("options.titles.showTime");
								int fadeOut = cfg.getInt("options.titles.fadeOut");
								
								Utility.sendTitle(p, ti, su, fadeIn, showTime, fadeOut);
							}
							
							if(cfg.getBoolean("options.enable_sounds"))
							{
								executeSecondary(p);
							}
							
							if(cfg.getBoolean("options.enable_actionbar"))
							{
								String ac = msg.getString("general.command.actionbar_reload_lang");
								
								ac = Color.tr(ac);
								ac = ac.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
								
								Utility.sendActionBar(p, ac);
							}
							
							dc.reloadLang();
							String message = msg.getString("general.command.reload_lang");
							
							message = message.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
							
							p.sendMessage(Color.tr(pr + message));
							return true;
						}
						
						String message = msg.getString("general.unknown_file");
						
						message = message.replace("%file%", args[1]);
						
						p.sendMessage(Color.tr(pr + message));
						return true;
					case "info":
						p.sendMessage(Color.tr(pr + "Server is running the version &e" + dc.getVe()));
						return true;
					case "help":
						help(se);
						return true;
				}
				
				String message = msg.getString("general.unknown_sub_command");
				
				message = message.replace("%scmd%", args[0]);
				
				p.sendMessage(Color.tr(pr + message));
				return false;
			}
			else
			{
				if(cfg.getBoolean("options.enable_titles"))
				{
					String ti = msg.getString("general.title_perm");
					String su = msg.getString("general.subtitle_perm");
					
					ti = Color.tr(ti);
					su = Color.tr(su);
					ti = ti.replace("%perm%", "deathcoords.command.admin");
					su = su.replace("%perm%", "deathcoords.command.admin");
					
					int fadeIn = cfg.getInt("options.titles.fadeIn");
					int showTime = cfg.getInt("options.titles.showTime");
					int fadeOut = cfg.getInt("options.titles.fadeOut");
					
					Utility.sendTitle(p, ti, su, fadeIn, showTime, fadeOut);
				}
				
				if(cfg.getBoolean("options.enable_sounds"))
				{
					execute(p);
				}
				
				String message = msg.getString("general.cannot_perm");
				
				message = message.replace("%perm%", args[0]);
				
				p.sendMessage(Color.tr(pr + message));
				return false;
			}
		}
		else
		{
			if(se.hasPermission("deathcoords.command.admin") || se.isOp())
			{
				if(args.length == 0)
				{
					se.sendMessage(Color.tr(pr + msg.getString("general.command.usage")));
					return true;
				}
				
				long reloadTime = System.currentTimeMillis();
				switch(args[0])
				{
					case "reload":
						if(args.length == 1)
						{
							dc.reloadConf();
							dc.reloadLang();
							String message = msg.getString("general.command.reload_all");
							
							message = message.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
							
							se.sendMessage(Color.tr(pr + message));
							return true;
						}
						
						if(args[1].equalsIgnoreCase("config"))
						{						
							dc.reloadConf();
							String message = msg.getString("general.command.reload_config");
							
							message = message.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
							
							se.sendMessage(Color.tr(pr + message));
							return true;
						}
						
						if(args[1].equalsIgnoreCase("lang"))
						{						
							dc.reloadLang();
							String message = msg.getString("general.command.reload_lang");
							
							message = message.replace("%reloadTime%", (System.currentTimeMillis() - reloadTime) + "");
							
							se.sendMessage(Color.tr(pr + message));
							return true;
						}
						
						String message = msg.getString("general.unknown_file");
						
						message = message.replace("%file%", args[1]);
						
						se.sendMessage(Color.tr(pr + message));
						return true;
					case "info":
						se.sendMessage(Color.tr(pr + "Server is running the version &e" + dc.getVe()));
						return true;
					case "help":
						help(se);
						return true;
				}
				
				String message = msg.getString("general.unknown_sub_command");
				
				message = message.replace("%scmd%", args[0]);
				
				se.sendMessage(Color.tr(pr + message));
				return false;
			}
			else
			{			
				String message = msg.getString("general.cannot_perm");
				
				message = message.replace("%perm%", args[0]);
				
				se.sendMessage(Color.tr(pr + message));
				return false;
			}
		}
	}
	
	private void help(CommandSender se)
	{
		FileConfiguration msg = dc.getLangFile();
		String pr = msg.getString("general.prefix");
		se.sendMessage(Color.tr(pr + "&8| &7By &6SimpleCoder"));
		se.sendMessage(Color.tr(""));
		se.sendMessage(Color.tr("&e/deathcoords | /dc &aMain command."));
		se.sendMessage(Color.tr("&e/dc <help> &aShow this message."));
		se.sendMessage(Color.tr("&e/dc <reload> [file] &aReload the plugin."));
		se.sendMessage(Color.tr("&e/dc <info> &aShow plugin information."));
	}

	private void execute(Player p)
	{
		p.playSound(p.getLocation(), getSound(), getVolumen(), getPitch());
	}
	
	private void executeSecondary(Player p)
	{
		p.playSound(p.getLocation(), getSecondarySound(), getVolumen(), getPitch());
	}
	
}
