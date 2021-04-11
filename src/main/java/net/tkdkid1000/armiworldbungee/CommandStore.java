package net.tkdkid1000.armiworldbungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class CommandStore extends Command {

	private ArmiWorldBungee armiworld;

	public CommandStore(ArmiWorldBungee armiworld) {
		super("store");
		this.armiworld = armiworld;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(new ComponentBuilder(armiworld.getConfig().getString("store")).color(ChatColor.DARK_PURPLE).create());
	}

}
