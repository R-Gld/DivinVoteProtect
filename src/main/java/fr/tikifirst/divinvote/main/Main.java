package fr.tikifirst.divinvote.main;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable(){
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new fr.tikifirst.divinvote.event.InteractGUI(this), this);
		
		getCommand("vote").setExecutor(new fr.tikifirst.divinvote.cmd.commandesVote(this));
	}
	
	public static String getApiLink(int i)
	{
		return "0";
	}
	
}
