package fr.tikifirst.divinvote.main;

import fr.tikifirst.divinvote.cmd.commandsVote;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable(){
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new fr.tikifirst.divinvote.event.InteractGUI(this), this);
		
		getCommand("vote").setExecutor(new commandsVote(this));
	}
	
}
