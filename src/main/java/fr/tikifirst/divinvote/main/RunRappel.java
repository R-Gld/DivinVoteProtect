package fr.tikifirst.divinvote.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RunRappel extends BukkitRunnable {
		
		public Main main;
		
		public RunRappel(Main main)
		{
			this.main = main;
		}
		 		
		@Override
		public void run()
		{

			for(Player p : Bukkit.getOnlinePlayers())
			{
				DataManager dm = new DataManager(main);
				
				FileConfiguration fcp = dm.getPlayerConfigurationFile(p);
				
				String voteMsg = main.getConfig().getString("config.texts.rappelMsg");
				voteMsg = voteMsg.replace("&", "§");
				
				if((System.currentTimeMillis()/1000) - 5400 >= fcp.getLong("datas.lastVotedOn." + 1))
				{
					p.sendMessage(voteMsg);
					Title.sendTitle(p, "§r", "§aTu peux §e§lvoter §a! Fais §b§l/vote§a pour voter et gagner des §erécompenses§a!", 80);
					continue;
				} 
				if((System.currentTimeMillis()/1000) - 10800 >= fcp.getLong("datas.lastVotedOn." + 2))
				{
					//p.sendMessage("§7§!(§a§!!§7§!) §a§lTu peux Voter sur le §b§lLien 2§e§l ! Fais §b§l/vote §a§lpour voter et gagner §e§ldes Clefs de Vote & 5000$ §a§l!");
					//Title.sendTitle(p, "§r", "§aTu peux §e§lvoter §a! Fais §b§l/vote§a pour voter et gagner des §erécompenses§a!", 80);
				} 
				if((System.currentTimeMillis()/1000) - 86400 >= fcp.getLong("datas.lastVotedOn." + 3))
				{
					p.sendMessage(voteMsg);
					Title.sendTitle(p, "§r", "§aTu peux §e§lvoter §a! Fais §b§l/vote§a pour voter et gagner des §erécompenses§a!", 80);
					continue;
				}
				if((System.currentTimeMillis()/1000) - 86400 >= fcp.getLong("datas.lastVotedOn." + 4))
				{
					p.sendMessage(voteMsg);
					Title.sendTitle(p, "§r", "§aTu peux §e§lvoter §a! Fais §b§l/vote§a pour voter et gagner des §erécompenses§a!", 80);
					continue;
				}
			}
		}

}
