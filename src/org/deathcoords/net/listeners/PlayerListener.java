package org.deathcoords.net.listeners;

import java.text.NumberFormat;
import java.util.Optional;

import org.bukkit.FireworkEffect;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.deathcoords.net.DeathCoords;
import org.deathcoords.net.color.Color;
import org.deathcoords.net.utility.Utility;
import org.deathcoords.net.xseries.XSound;

public class PlayerListener implements Listener 
{
	
	private DeathCoords dc = DeathCoords.getPlugin(DeathCoords.class);
	private Sound sound;
	private int volumen;
	private int pitch;
	
	public PlayerListener(DeathCoords dc)
	{
		Optional<XSound> xs = XSound.matchXSound(dc.getConfigFile().getString("options.sounds.event"));
	    if (xs.isPresent()) 
	    {
	    	this.sound = ((XSound)xs.get()).parseSound();
	    } 
	    else 
	    {
	    	this.sound = XSound.ENTITY_ARROW_HIT.parseSound();
	    } 
	    this.volumen = dc.getConfigFile().getInt("options.sounds.volumen");
	    this.pitch = dc.getConfigFile().getInt("options.sounds.pitch");
		this.dc = dc;
	}
	
	private Sound getSound()
	{
		return sound;
	}
	
	private int getVolumen()
	{
		return volumen;
	}
	
	private int getPitch()
	{
		return pitch;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent ev)
	{
		Entity p = (Entity) ev.getEntity();
		FileConfiguration cfg = dc.getConfigFile();
		FileConfiguration msg = dc.getLangFile();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumIntegerDigits(3);
		if(cfg.getBoolean("options.enable_sounds"))
		{
			execute((Player) p);
		}
		
		if(cfg.getBoolean("options.enable_titles"))
		{
			String ti = msg.getString("general.event.death_title");
			String su = msg.getString("general.event.death_subtitle");
			
			ti = Color.tr(ti);
			su = Color.tr(su);
			
			ti = ti.replace("%coords_x%", nf.format(p.getLocation().getBlockX()));
			ti = ti.replace("%coords_y%", nf.format(p.getLocation().getBlockY()));
			ti = ti.replace("%coords_z%", nf.format(p.getLocation().getBlockZ()));
			ti = ti.replace("%coords_yaw%", p.getLocation().getYaw() + "");
			ti = ti.replace("%coords_pitch%", p.getLocation().getPitch() + "");
			ti = ti.replace("%world%", p.getWorld().getName());
			
			su = su.replace("%coords_x%", nf.format(p.getLocation().getBlockX()));
			su = su.replace("%coords_y%", nf.format(p.getLocation().getBlockY()));
			su = su.replace("%coords_z%", nf.format(p.getLocation().getBlockZ()));
			su = su.replace("%coords_yaw%", p.getLocation().getYaw() + "");
			su = su.replace("%coords_pitch%", p.getLocation().getPitch() + "");
			su = su.replace("%world%", p.getWorld().getName());
			
			int fadeIn = cfg.getInt("options.titles.fadeIn");
			int showTime = cfg.getInt("options.titles.showTime");
			int fadeOut = cfg.getInt("options.titles.fadeOut");
			
			Utility.sendTitle((Player) p, ti, su, fadeIn, showTime, fadeOut);
		}
		
		if(cfg.getBoolean("options.enable_actionbar"))
		{
			String ac = msg.getString("general.event.death_actionbar");
			
			ac = Color.tr(ac);
			ac = ac.replace("%coords_x%", nf.format(p.getLocation().getBlockX()));
			ac = ac.replace("%coords_y%", nf.format(p.getLocation().getBlockY()));
			ac = ac.replace("%coords_z%", nf.format(p.getLocation().getBlockZ()));
			ac = ac.replace("%coords_yaw%", p.getLocation().getYaw() + "");
			ac = ac.replace("%coords_pitch%", p.getLocation().getPitch() + ""); 
			ac = ac.replace("%world%", p.getWorld().getName());
			
			Utility.sendActionBar((Player) p, ac);
		}
		
		if(cfg.getBoolean("options.enable_fireworks"))
		{
			if(p.hasPermission("deathcoords.event.fireworks") || p.isOp())
			{
				String ef = cfg.getString("options.fireworks.effect");
				int pw = cfg.getInt("options.fireworks.power");
				
				Firework fi = (Firework) p.getLocation().getWorld().spawn(p.getLocation(), Firework.class);
				FireworkMeta fm = fi.getFireworkMeta();
				fm.addEffect(FireworkEffect.builder()
						.flicker(true)
						.trail(true)
						.withTrail()
						.withFlicker()
						.with(FireworkEffect.Type.valueOf(ef))
						.build());
				
				fm.setPower(pw);
				fi.setFireworkMeta(fm);
			}
		}
		
		if(cfg.getBoolean("options.event.keep_inv"))
		{
			boolean keep_inv = cfg.getBoolean("options.event.keep_inv");
			ev.setKeepInventory(keep_inv);
		}
		
		if(cfg.getBoolean("options.event.keep_lvl"))
		{
			boolean keep_lvl = cfg.getBoolean("options.event.keep_lvl");
			ev.setKeepLevel(keep_lvl);
		}
		
		if(!(cfg.getBoolean("options.event.death_message")))
		{	
			ev.setDeathMessage(null);
		}
		else
		{
			String message = msg.getString("general.event.death_broadcast");
			
			message = Color.tr(message);
			message = message.replace("%coords_x%", nf.format(p.getLocation().getBlockX()));
			message = message.replace("%coords_y%", nf.format(p.getLocation().getBlockY()));
			message = message.replace("%coords_z%", nf.format(p.getLocation().getBlockZ()));
			message = message.replace("%coords_yaw%", p.getLocation().getYaw() + "");
			message = message.replace("%coords_pitch%", p.getLocation().getPitch() + ""); 
			message = message.replace("%world%", p.getWorld().getName());
			message = message.replace("%user_name%", p.getName());
			
			ev.setDeathMessage(message);
		}
		
		int dropped_exp = cfg.getInt("options.event.drop_exp");
		int new_exp = cfg.getInt("options.event.exp");
		int new_lvl = cfg.getInt("options.event.lvl");
		
		ev.setDroppedExp(dropped_exp);
		ev.setNewExp(new_exp);
		ev.setNewLevel(new_lvl);
		
		String message = msg.getString("general.event.death_message");
		
		message = Color.tr(message);
		message = message.replace("%coords_x%", nf.format(p.getLocation().getBlockX()));
		message = message.replace("%coords_y%", nf.format(p.getLocation().getBlockY()));
		message = message.replace("%coords_z%", nf.format(p.getLocation().getBlockZ()));
		message = message.replace("%coords_yaw%", p.getLocation().getYaw() + "");
		message = message.replace("%coords_pitch%", p.getLocation().getPitch() + ""); 
		message = message.replace("%world%", p.getWorld().getName());
		
		p.sendMessage(message);
	}
	
	
	private void execute(Player p)
	{
		p.playSound(p.getLocation(), getSound(), getVolumen(), getPitch());
	}

}
