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
						}
				}
			}
		}
		return false;
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
