package fr.tikifirst.divinvote.cmd;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.tikifirst.divinvote.main.DataManager;
import fr.tikifirst.divinvote.main.Main;
import fr.tikifirst.divinvote.main.RunRappel;
import fr.tikifirst.divinvote.main.VoteGUI;

public class commandesVote implements CommandExecutor {

	public Main main;
	
	public commandesVote(Main main) {
		this.main = main;
	}
	
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(!(sender instanceof Player))
		{
			if(cmd.getName().equalsIgnoreCase("vote"))
			{
				if(args[0].equalsIgnoreCase("rappel"))
				{
					RunRappel rr = new RunRappel(main);
					
					rr.runTaskAsynchronously(main);
				}
				
				if(args[0].equalsIgnoreCase("reset"))
				{
					DataManager dm = new DataManager(main);
					dm.resetMonth();
					
					//VoteManager vm = new VoteManager(main);
					//vm.awardBestVoters();
				}
				
				
				if(args[0].equalsIgnoreCase("resetChall"))
				{
					DataManager dm = new DataManager(main);
					dm.resetChall();
				}

			}
		}
		
		
		
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("vote"))
			{
				if(args.length == 0)
				{
					VoteGUI vg = new VoteGUI(main);
					
					vg.openMain(p);

					return true;
				}
				
				if(args[0].equalsIgnoreCase("rappel") && p.isOp())
				{
					RunRappel rr = new RunRappel(main);
					
					rr.runTaskAsynchronously(main);
				}
				
			}
			
		}
		
		return false;
	}

}
