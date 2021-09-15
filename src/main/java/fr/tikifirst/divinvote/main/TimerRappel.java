package fr.tikifirst.divinvote.main;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;


public class TimerRappel extends BukkitRunnable {
	
	public final Main main;
	
	public TimerRappel(Main main) {
		this.main = main;
	}
	
	private int timer = 1200;
	
	@Override
	public void run() {
		if(timer == 0) {
			Bukkit.dispatchCommand(main.getServer().getConsoleSender(), "vote rappel");
			
			timer = 1200;
		}

		timer--;
	}

}
