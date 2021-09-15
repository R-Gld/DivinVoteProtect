package fr.tikifirst.divinvote.cmd;

import fr.tikifirst.divinvote.main.DataManager_YML;
import fr.tikifirst.divinvote.main.Main;
import fr.tikifirst.divinvote.main.RunRappel;
import fr.tikifirst.divinvote.main.VoteGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandsVote implements CommandExecutor {

	public Main main;
	
	public commandsVote(Main main) {
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
					DataManager_YML dm = new DataManager_YML();
					dm.resetMonth();
					
					//VoteManager vm = new VoteManager(main);
					//vm.awardBestVoters();
				}
				
				
				if(args[0].equalsIgnoreCase("resetChall"))
				{
					DataManager_YML dm = new DataManager_YML();
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
					p.sendMessage("�aVote rappel OK");
					RunRappel rr = new RunRappel(main);
					
					rr.runTaskAsynchronously(main);
				}
				
			}
			
		}
		
		return false;
	}

}
