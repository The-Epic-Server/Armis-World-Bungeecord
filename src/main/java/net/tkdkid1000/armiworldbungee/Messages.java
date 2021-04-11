package net.tkdkid1000.armiworldbungee;

import java.util.concurrent.TimeUnit;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

public class Messages {

	private Configuration config;
	private ArmiWorldBungee armiworld;

	public Messages(ArmiWorldBungee armiworld, Configuration config) {
		this.armiworld = armiworld;
		this.config = config;
	}
	
	public void start() {
		Configuration messages = config.getSection("messages");
		for (String key : messages.getKeys()) {
			armiworld.getProxy().getScheduler().schedule(armiworld, new Runnable() {

				@Override
				public void run() {
					for (ProxiedPlayer player : armiworld.getProxy().getPlayers()) {
						player.sendMessage(new ComponentBuilder(
								ChatColor.translateAlternateColorCodes('&', messages.getString(key+".message")))
								.event(new ClickEvent(ClickEvent.Action.OPEN_URL, messages.getString(key+".link")))
								.create());
					}
				}
				
			}, messages.getLong(key+".time"), messages.getLong(key+".time"), TimeUnit.MINUTES);
		}
	}
}
