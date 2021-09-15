package fr.tikifirst.divinvote.main;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class VoteManager {

	public Main main;

	public VoteManager(Main main) {
		this.main = main;
	}

	public void challUnlock(Player p, int chall, int voteNeeded) {
		DataManager_SQL dm2 = new DataManager_SQL();
		dm2.setChall(p, chall);

		List<String> awards = main.getConfig().getStringList("config.paliers." + voteNeeded + ".cmd");

		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

		for(String cmds : awards)
		{
			cmds = cmds.replaceAll("%p", p.getName());
			Bukkit.dispatchCommand(console, cmds);
		}

		p.sendMessage("§7(§a!§7) §aBravo ! Tu as débloqué le §e§lPalier n°" + chall);
		Bukkit.broadcastMessage("§7(§a!§7) §aFélicitations à §b" + p.getName() + "§a qui vient de débloquer le §epalier de vote n°" + chall);

	}

	public void awardBestVoters()
	{
		DataManager_SQL dm = new DataManager_SQL();
		for(DivinPlayer p : dm.getAllPlayers()) {
			long count = p.getCount_months();
			OfflinePlayer pl = Bukkit.getOfflinePlayer(p.getUuid());

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
		DataManager_SQL dm2 = new DataManager_SQL();
		DivinPlayer dp = dm2.getDivinPlayer(p);
		if(link == 1) {
			return (System.currentTimeMillis() / 1000) - 5400 >= dp.getVote_1();
		} else if (link == 2){
			return (System.currentTimeMillis() / 1000) - 7200 >= dp.getVote_2();
		} else if (link == 3){
			return (System.currentTimeMillis() / 1000) - 86400 >= dp.getVote_3();
		} else {
			return (System.currentTimeMillis() / 1000) - 86400 >= dp.getVote_4();
		}
	}

	public String timeUntilCDIsOver(Player p, int link) {
		DataManager_SQL dm2 = new DataManager_SQL();
		long timeLeft = getLink(dm2.getDivinPlayer(p), link);
		int secondsLeft = (int) ((timeLeft + 86400) - (System.currentTimeMillis()/1000));

		if(link == 1) {
			secondsLeft = (int) ((timeLeft + 5400) - (System.currentTimeMillis()/1000));
		} else if(link == 2) {
			secondsLeft = (int) ((timeLeft + 7200) - (System.currentTimeMillis()/1000));
		}

		int days = secondsLeft / 86400;
		int hours = secondsLeft / 3600;
		int minutes = (secondsLeft % 3600) / 60;
		int seconds = secondsLeft % 60;

		if(days >= 1) {
			return "§edans §b" + days + " jours, " + hours + "h, " + minutes + "min, " + seconds + "se";
		}

		if(hours >= 1) {
			return "§edans §b" + hours + "h, " + minutes + "min, " + seconds + "s";
		}

		if(minutes >= 1) {
			return "§edans §b" + minutes + "min, " + seconds + "s";
		}

		if(seconds >= 1) {
			return "§edans §b" + seconds + "s";
		}

		return "§a maintenant";
	}

	private long getLink(DivinPlayer p, int link) {
		switch(link){
			case 1:
				return p.getVote_1();
			case 2:
				return p.getVote_2();
			case 3:
				return p.getVote_3();
			case 4:
				return p.getVote_4();
		}
		throw new IllegalArgumentException("The given id of the link is outside of the bounds. (" + link + ")");
	}

}
