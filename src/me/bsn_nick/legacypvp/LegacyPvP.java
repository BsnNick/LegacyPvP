package me.bsn_nick.legacypvp;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author bsn_nick (000Nick)
 */
public class LegacyPvP extends JavaPlugin implements Listener
{
	private String name = this.getDescription().getName();
	private String version = this.getDescription().getVersion();
	private String author = this.getDescription().getAuthors().get(0);
	
	public void onEnable()
	{
		System.out.println("Enabling " + name + " " + version + " by " + author + "...");
		
		Bukkit.getPluginManager().registerEvents(this, this);
		
		System.out.println(name + " " + version + " by " + author + " has been enabled!");
	}
	
	public void onDisable()
	{
		System.out.println(name + " " + version + " by " + author + " has been disabled!");
	}
	
	@EventHandler
	public void enableOldPvp(PlayerJoinEvent event)
	{
		try
		{
			event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).addModifier(new AttributeModifier("AttackSpeedRemover", 1.0, AttributeModifier.Operation.ADD_NUMBER));
		}
		catch (Exception e)
		{
			System.out.println("There was an error removing attack speed! Are you running Spigot 1.9 or above?");
		}
	}
	
	@EventHandler
	public void reEnableForCompat(PlayerQuitEvent event)
	{
		try
		{
			event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).removeModifier(new AttributeModifier("AttackSpeedRemover", 1.0, AttributeModifier.Operation.ADD_NUMBER));
		}
		catch (Exception e)
		{
			System.out.println("There was an error removing attack speed! Are you running Spigot 1.9 or above?");
		}
	}
}