package fr.tikifirst.divinvote.main;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SyncVoteCompleted extends BukkitRunnable
{
		public final Main main;
		private final Player p;
		private final int link;
  
		public SyncVoteCompleted(Main main, Player p, int link) 
		{
			this.main = main;
			this.p = p;
			this.link = link;
		}

		public void run() 
		{
			UtilsVote uv = new UtilsVote(this.main);
			uv.voteIsOkay(this.p, this.link);
    
			this.p.sendMessage("§7(§a!§7) §aTu as validé ton vote sur le §eLien n°" + this.link + "§c ❤");
			this.p.closeInventory();
    
			VoteGUI vg = new VoteGUI(this.main);
			vg.openMain(this.p);
		}
}