package net.tkdkid1000.armiworldbungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class CommandDiscord extends Command {

	private ArmiWorldBungee armiworld;

	public CommandDiscord(ArmiWorldBungee armiworld) {
		super("discord");
		this.armiworld = armiworld;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(new ComponentBuilder(armiworld.getConfig().getString("discord")).color(ChatColor.DARK_PURPLE).create());
	}
}
