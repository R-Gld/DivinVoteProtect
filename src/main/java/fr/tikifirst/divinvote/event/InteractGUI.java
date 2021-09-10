package fr.tikifirst.divinvote.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import fr.tikifirst.divinvote.main.Main;
import fr.tikifirst.divinvote.main.RunCheck;
import fr.tikifirst.divinvote.main.SyncVote;
import fr.tikifirst.divinvote.main.VoteGUI;
import fr.tikifirst.divinvote.main.VoteManager;

public class InteractGUI implements Listener {
	
	public Main main;
	
	public InteractGUI(Main main)
	{
		this.main = main;
	}
	@EventHandler
	public void onLeave(InventoryCloseEvent e)
	{
		Player p = (Player) e.getPlayer();
		
		if(e.getInventory().getName().contains("Paliers mensuels"))
		{
			SyncVote sv = new SyncVote(main, p);
			sv.runTask(main);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		
		ItemStack current = e.getCurrentItem();
		
		if(current == null)
		{
			return;
		}
		
		
		if(!e.getInventory().getName().contains("§4*"))
		{
			return;
		}
		
		e.setCancelled(true);
		
		if(e.getInventory().getName().contains("votes"))
		{			
			if(e.getCurrentItem().getType().equals(Material.MINECART))
			{				
				if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Lien"))
				{
					String link = e.getCurrentItem().getItemMeta().getDisplayName();
					link = link.replace("§eLien de vote n°", "");
					
					if(e.getClick().equals(ClickType.LEFT))
					{
						
						if(link.equalsIgnoreCase("1"))
						{
							p.sendMessage("§f§m---------------------§f[§e*§f]§m--------------------");
							p.sendMessage("");
							p.sendMessage("      §7[§eClique§7]  §e" + main.getConfig().getString("config.links.1"));
							p.sendMessage("");
							p.sendMessage("§f§m--------------------§f[§e*§f]§m--------------------");
						} else if (link.equalsIgnoreCase("2"))
						{
							p.sendMessage("§f§m--------------------§f[§e*§f]§m--------------------");
							p.sendMessage("");
							p.sendMessage("      §7[§eClique§7]  §e" + main.getConfig().getString("config.links.2"));
							p.sendMessage("");
							p.sendMessage("§f§m--------------------§f[§e*§f]§m--------------------");
						}  else if (link.equalsIgnoreCase("3"))
						{
							p.sendMessage("§f§m--------------------§f[§e*§f]§m--------------------");
							p.sendMessage("");
							p.sendMessage("      §7[§eClique§7]  §e" + main.getConfig().getString("config.links.3"));
							p.sendMessage("");
							p.sendMessage("§f§m--------------------§f[§e*§f]§m--------------------");
						}  else if (link.equalsIgnoreCase("4"))
						{
							p.sendMessage("§f§m--------------------§f[§e*§f]§m--------------------");
							p.sendMessage("");
							p.sendMessage("      §7[§eClique§7]  §e" + main.getConfig().getString("config.links.4"));
							p.sendMessage("");
							p.sendMessage("§f§m--------------------§f[§e*§f]§m--------------------");
						}
						
						p.closeInventory();
					}
					
					if(e.getClick().equals(ClickType.RIGHT))
					{
						if(link.equalsIgnoreCase("1"))
						{
							p.closeInventory();
							RunCheck rc = new RunCheck(main, p, 1);
							rc.runTaskAsynchronously(main);
							p.sendMessage("§7(§a!§7) §aVérification du Lien 1 en cours...");
						} else if (link.equalsIgnoreCase("2"))
						{
							p.closeInventory();
							RunCheck rc = new RunCheck(main, p, 2);
							rc.runTaskAsynchronously(main);
							p.sendMessage("§7(§a!§7) §aVérification du Lien 2 en cours...");
						}  else if (link.equalsIgnoreCase("3"))
						{
							p.closeInventory();
							RunCheck rc = new RunCheck(main, p, 3);
							rc.runTaskAsynchronously(main);
							p.sendMessage("§7(§a!§7) §aVérification du Lien 3 en cours...");
						}  else if (link.equalsIgnoreCase("4"))
						{
							p.closeInventory();
							RunCheck rc = new RunCheck(main, p, 4);
							rc.runTaskAsynchronously(main);
							p.sendMessage("§7(§a!§7) §aVérification du Lien 4 en cours...");
						}
					}
					
					return;

				}
			}
			
			if(e.getCurrentItem().getType().equals(Material.NETHER_STAR))
			{	
				VoteGUI vg = new VoteGUI(main);
				vg.openChallenges(p);
				return;
			}
			
			
		}
		
		if(e.getInventory().getName().contains("Paliers mensuels"))
		{			
			if(e.getCurrentItem().getType().equals(Material.POWERED_MINECART))
			{	
				if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Palier"))
				{
					String chall = e.getCurrentItem().getItemMeta().getDisplayName();
					chall = chall.replace("§ePalier n°", "");
					
					int i = 0;
					
					VoteManager vm = new VoteManager(main);

					p.closeInventory();
					
					for(String s : main.getConfig().getConfigurationSection("config.paliers").getKeys(false))
					{
						i++;
						
						if(Integer.valueOf(chall) == i)
						{
							vm.challUnlock(p, Integer.valueOf(chall), Integer.valueOf(s));
							return;
						}
					}
				}
			}
			
		}
				
	}
}

