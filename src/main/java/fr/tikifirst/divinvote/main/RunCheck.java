package fr.tikifirst.divinvote.main;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RunCheck extends BukkitRunnable {
	
	public final Main main;
	private final Player p;
	private final int link;
	
	public RunCheck(Main main, Player p, int link)
	{
		this.main = main;
		this.p = p;
		this.link = link;
	}
	 		
	@Override
	public void run()
	{
		VoteManager vm = new VoteManager(main);
		
		UtilsVote uv = new UtilsVote(main);
		
		if(link == 1)
		{
			if(uv.hasVoted1(p))
			{
				
				if(vm.canPlayerVoteAgain(p, link))
				{
					SyncVoteCompleted sv = new SyncVoteCompleted(main, p, 1);
					sv.runTask(main);
				} else {
					SyncVote sv = new SyncVote(main, p);
					sv.runTask(main);
				}
				
			} else {
				
				SyncVote sv = new SyncVote(main, p);
				sv.runTask(main);
			}
		}
		
		if(link == 2)
		{
			if(uv.hasVoted2(p))
			{
				
				if(vm.canPlayerVoteAgain(p, link))
				{
					SyncVoteCompleted sv = new SyncVoteCompleted(main, p, 2);
					sv.runTask(main);
				} else {
					SyncVote sv = new SyncVote(main, p);
					sv.runTask(main);
				}
				
			} else {

				SyncVote sv = new SyncVote(main, p);
				sv.runTask(main);
			}
		}

		if(link == 3)
		{
			if(uv.hasVoted3(p))
			{
				
				if(vm.canPlayerVoteAgain(p, link))
				{
					SyncVoteCompleted sv = new SyncVoteCompleted(main, p, 3);
					sv.runTask(main);
				} else {
					SyncVote sv = new SyncVote(main, p);
					sv.runTask(main);
				}

			} else {

				SyncVote sv = new SyncVote(main, p);
				sv.runTask(main);
			}
		}
		

		if(link == 4)
		{
			if(uv.hasVoted4(p))
			{
				
				if(vm.canPlayerVoteAgain(p, link))
				{
					SyncVoteCompleted sv = new SyncVoteCompleted(main, p, 4);
					sv.runTask(main);
				} else {
					SyncVote sv = new SyncVote(main, p);
					sv.runTask(main);
				}

			} else {

				SyncVote sv = new SyncVote(main, p);
				sv.runTask(main);
			}
		}
	}
}
