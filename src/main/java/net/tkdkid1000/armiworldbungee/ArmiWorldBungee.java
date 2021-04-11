package net.tkdkid1000.armiworldbungee;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ArmiWorldBungee extends Plugin {

	Configuration config;
	Plugin plugin = this;
	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerCommand(this, new CommandHub());
    	getProxy().getPluginManager().registerCommand(this, new CommandBackup(this));
    	getProxy().getPluginManager().registerCommand(this, new ProxyCommand(this));
    	getProxy().getPluginManager().registerListener(this, new ProxyCommand(this));
    	getProxy().getPluginManager().registerCommand(this, new CommandDiscord(this));
    	getProxy().getPluginManager().registerCommand(this, new CommandWebsite(this));
    	getProxy().getPluginManager().registerCommand(this, new CommandStore(this));
		getProxy().getServers().put("test", getProxy().constructServerInfo("test", new InetSocketAddress("localhost", 29394), "MOTD", false));
	}
	
	private static ArmiWorldBungee instance;
    public static ArmiWorldBungee getInstance() {
    	return instance;
    }
    
    public Configuration getConfig() {
    	try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
			return config;
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
