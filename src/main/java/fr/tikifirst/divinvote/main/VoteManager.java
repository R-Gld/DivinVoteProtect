package fr.tikifirst.divinvote.main;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class VoteManager {
	
	public Main main;
	
	public VoteManager(Main main) {
		this.main = main;
	}
	
	public void challUnlock(Player p, int chall, int voteNeeded)
	{
		DataManager dm = new DataManager(main);
		
		FileConfiguration fcp = dm.getPlayerConfigurationFile(p);
		
		fcp.set("datas.challenges", chall);
		dm.saveData(dm.getPlayerFile(p), fcp);
		
		List<String> awards = main.getConfig().getStringList("config.paliers." + voteNeeded + ".cmd");
		
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		for(String cmds : awards)
		{
			cmds = cmds.replaceAll("%p", p.getName());
			Bukkit.dispatchCommand(console, cmds);
		}
		
		p.sendMessage("§7(§a!§7) §aBravo ! Tu as débloqué le §e§lPalier n°" + chall);
		Bukkit.broadcastMessage("§7(§a!§7) §aFélicitations à §b" + p.getName() + "§aqui vient de débloquer le §epalier de vote n°" + chall);
		
	}
	
	public void awardBestVoters()
	{
		 File playerDatasFile = new File("plugins/DivinVote/PlayerDatas");
			
			if(!playerDatasFile.exists())
			{
				playerDatasFile.mkdir();
			}
			
			File[] listFiles = playerDatasFile.listFiles();
			
			for(int i = 0; i < listFiles.length; i++)
			{
				File pFile = listFiles[i];
				String id = pFile.getName();
				id = id.replace(".yml", "");
				
				FileConfiguration fcp = YamlConfiguration.loadConfiguration(pFile);
				
				int count = fcp.getInt("datas.count.month");
				
				OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(id));

				if(count >= 5 && count < 10)
				{
					List<String> awards1 = main.getConfig().getStringList("config.recompensestopvoteurs.5");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					
					for(String cmds : awards1)
					{
						cmds = cmds.replaceAll("%p", pl.getName());
						Bukkit.dispatchCommand(console, cmds);
					}
				}
				
				if(count >= 10 && count < 15)
				{
					List<String> awards2 = main.getConfig().getStringList("config.recompensestopvoteurs.10");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					
					for(String cmds : awards2)
					{
						cmds = cmds.replaceAll("%p", pl.getName());
						Bukkit.dispatchCommand(console, cmds);
					}
				}
				
				if(count >= 15 && count < 20)
				{
					List<String> awards3 = main.getConfig().getStringList("config.recompensestopvoteurs.15");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					
					for(String cmds : awards3)
					{
						cmds = cmds.replaceAll("%p", pl.getName());
						Bukkit.dispatchCommand(console, cmds);
					}
				}
				
				if(count >= 20 && count < 25)
				{
					List<String> awards4 = main.getConfig().getStringList("config.recompensestopvoteurs.20");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					
					for(String cmds : awards4)
					{
						cmds = cmds.replaceAll("%p", pl.getName());
						Bukkit.dispatchCommand(console, cmds);
					}
				}
				
				if(count >= 25 && count < 30)
				{
					List<String> awards5 = main.getConfig().getStringList("config.recompensestopvoteurs.25");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					
					for(String cmds : awards5)
					{
						cmds = cmds.replaceAll("%p", pl.getName());
						Bukkit.dispatchCommand(console, cmds);
					}
				}
				
				if(count >= 30)
				{
					List<String> awards6 = main.getConfig().getStringList("config.recompensestopvoteurs.30");
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					
					for(String cmds : awards6)
					{
						cmds = cmds.replaceAll("%p", pl.getName());
						Bukkit.dispatchCommand(console, cmds);
					}
				}
			}
	}
	
	public boolean canPlayerVoteAgain(Player p, int link)
	{
		OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
		DataManager dm = new DataManager(main);
		
		FileConfiguration fcp = dm.getPlayerConfigurationFile(op);
		
		if(link == 1)
		{
			if((System.currentTimeMillis()/1000) - 5400 >= fcp.getLong("datas.lastVotedOn." + link))
			{
				return true;
			} else 
			{
				return false;
			}
		} else if (link == 2){
			if((System.currentTimeMillis()/1000) - 10800 >= fcp.getLong("datas.lastVotedOn." + link))
			{
				return true;
			} else 
			{
				return false;
			}
		} else if (link == 3){
			if((System.currentTimeMillis()/1000) - 86400 >= fcp.getLong("datas.lastVotedOn." + link))
			{
				return true;
			} else 
			{
				return false;
			}
		} else {
			if((System.currentTimeMillis()/1000) - 86400 >= fcp.getLong("datas.lastVotedOn." + link))
			{
				return true;
			} else 
			{
				return false;
			}
		}	

	}
	
	public String timeUntileCDIsOver(Player p, int link)
	{	
		OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
		DataManager dm = new DataManager(main);
		
		FileConfiguration fcp = dm.getPlayerConfigurationFile(op);
		
		long timeLeft = fcp.getLong("datas.lastVotedOn." + link);
		int secondsLeft = (int) ((timeLeft + 86400) - (System.currentTimeMillis()/1000));
		
		if(link == 1)
		{
			secondsLeft = (int) ((timeLeft + 5400) - (System.currentTimeMillis()/1000));
		} else if(link == 2)
		{
			secondsLeft = (int) ((timeLeft + 86400) - (System.currentTimeMillis()/1000));
		}
		
		int days;
		int hours;
		int minutes;
		int seconds;
		
		days = secondsLeft / 86400;
		hours = secondsLeft / 3600;
		minutes = (secondsLeft % 3600) / 60;
		seconds = secondsLeft % 60;

		
		String dayF = "§edans " + days + " jours, " + hours + "h, " + minutes + "min, " + seconds + "se";
		String hoursF = "§edans " + hours + "h, " + minutes + "min, " + seconds + "s";
		String minutesF = "§edans " + minutes + "min, " + seconds + "s";
		String secondsF = "§edans " + seconds + "s"; 
		
		if(days >= 1)
		{
			return dayF;
		}
		
		if(hours >= 1)
		{
			return hoursF;
		}
		
		if(minutes >= 1)
		{
			return minutesF;
		}
		
		if(seconds >= 1)
		{
			return secondsF;
		}
		
		return "§a maintenant";
	}
	
	
}
