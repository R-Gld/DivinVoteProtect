package fr.tikifirst.divinvote.main;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SyncVote extends BukkitRunnable {
	
	public final Main main;
	private final Player p;
	
	public SyncVote(Main main, Player p)
	{
		this.main = main;
		this.p = p;
	}
	 		
	@Override
	public void run()
	{
		p.closeInventory();
		VoteGUI vg = new VoteGUI(main);
		vg.openMain(p);
	}

}
