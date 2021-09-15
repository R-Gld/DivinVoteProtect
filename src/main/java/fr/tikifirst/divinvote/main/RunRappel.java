package fr.tikifirst.divinvote.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RunRappel extends BukkitRunnable {

	public Main main;

	public RunRappel(Main main)
	{
		this.main = main;
	}

	@Override
	public void run() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			DataManager_SQL dm2 = new DataManager_SQL();
			DivinPlayer p2 = dm2.getDivinPlayer(p);

			String voteMsg = main.getConfig().getString("config.texts.rappelMsg");
			voteMsg = voteMsg.replace("&", "§");

			if((System.currentTimeMillis()/1000) - 5400 >= p2.getVote_1())
			{
				p.sendMessage(voteMsg);
				Title.sendTitle(p, "§r", "§aTu peux §e§lvoter §a! Fais §b§l/vote§a pour voter et gagner des §erécompenses§a!", 80);
				continue;
			}
			if((System.currentTimeMillis()/1000) - 10800 >= p2.getVote_2())
			{
				//p.sendMessage("§7§!(§a§!!§7§!) §a§lTu peux Voter sur le §b§lLien 2§e§l ! Fais §b§l/vote §a§lpour voter et gagner §e§ldes Clefs de Vote & 5000$ §a§l!");
				//Title.sendTitle(p, "§r", "§aTu peux §e§lvoter §a! Fais §b§l/vote§a pour voter et gagner des §erécompenses§a!", 80);
			}
			if((System.currentTimeMillis()/1000) - 86400 >= p2.getVote_3())
			{
				p.sendMessage(voteMsg);
				Title.sendTitle(p, "§r", "§aTu peux §e§lvoter §a! Fais §b§l/vote§a pour voter et gagner des §erécompenses§a!", 80);
				continue;
			}
			if((System.currentTimeMillis()/1000) - 86400 >= p2.getVote_4())
			{
				p.sendMessage(voteMsg);
				Title.sendTitle(p, "§r", "§aTu peux §e§lvoter §a! Fais §b§l/vote§a pour voter et gagner des §erécompenses§a!", 80);
			}
		}
	}
}
