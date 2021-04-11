package net.tkdkid1000.armiworldbungee;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.api.event.ChatEvent;

public class ChatDelay implements Listener {

	private ArmiWorldBungee armiworld;
	private HashMap<ProxiedPlayer, Boolean> chatdelayplayers = new HashMap<ProxiedPlayer, Boolean>();
	private int chatdelay;
	
	public ChatDelay(ArmiWorldBungee armiworld, int chatdelay) {
		this.armiworld = armiworld;
		this.chatdelay = chatdelay;
	}
	
	@EventHandler
	public void onChat(ChatEvent event) {
		if (event.getSender() instanceof ProxiedPlayer) {
			ProxiedPlayer sender = (ProxiedPlayer) event.getSender();
			if (!sender.hasPermission("armiworldbungee.chatdelayexempt")) {
				this.chatdelayplayers.putIfAbsent(sender, false);
				if (this.chatdelayplayers.get(sender)) {
					event.setCancelled(true);
					sender.sendMessage(new ComponentBuilder("You are on chat delay! To bypass this feature purchase vip.")
							.event(new ClickEvent(ClickEvent.Action.OPEN_URL, armiworld.getConfig().getString("store")))
							.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.YELLOW + "https://cyberhubnet.tebex.io")))
							.color(ChatColor.RED)
							.create());
				} else {
					chatdelayplayers.replace(sender, true);
					armiworld.getProxy().getScheduler().schedule(armiworld, new Runnable() {

						@Override
						public void run() {
							chatdelayplayers.replace(sender, false);
						}
						
					}, this.chatdelay, TimeUnit.SECONDS);
				}
			}
		}
	}
}
