package me.jonasxpx.badwords;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import me.jonasxpx.badwords.listener.PlayerChatEventManager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class BadWords extends JavaPlugin{
	
	
	private static FileConfiguration fc = null;
	private static File config = null;
	private static List<String> badWords = null;
	private static List<String> channels = null;
	public static int defaultTime = 0;


	@Override
	public void onEnable() {
		if(getServer().getPluginManager().getPlugin("Legendchat") == null){
			getLogger().log(Level.SEVERE, "Dependencia necessária: LegendChat");
			this.getPluginLoader().disablePlugin(this);
			return;
		}
		getConfig().options().copyDefaults(true);
		saveConfig();
		config = new File(this.getDataFolder() + "/players.yml");
		if(!config.exists()){
			try {
				config.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		fc = YamlConfiguration.loadConfiguration(config);
		loadBadWords();
		loadIgnoredChannels();
		defaultTime = getConfig().getInt("TempoMinimo");
		getServer().getPluginManager().registerEvents(new PlayerChatEventManager(), this);
		getCommand("badwords").setExecutor(new CommandReload(this));
	}
	public static FileConfiguration getConfigPlayer(){
		return fc;
	}
	
	public static void saveConfigPlayer(){
		try {
			fc.save(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadBadWords(){
		badWords = getConfig().getStringList("BadWords");
	}
	
	private void loadIgnoredChannels(){
		channels = getConfig().getStringList("IgnoredChannels");
	}
	
	public static List<String> getBadWords(){
		return badWords;
	}
	
	public static List<String> getIgnoredChannels(){
		return channels;
	}

	protected void forceReload(){
		reloadConfig();
		loadBadWords();
		loadIgnoredChannels();
		defaultTime = getConfig().getInt("TempoMinimo");
	}
}
