package fr.tikifirst.divinvote.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class VoteGUI {
	
	public Main main;
	
	public VoteGUI(Main main)
	{
		this.main = main;
	}
	
	public void openMain(Player p)
	{
		UtilsVote uv = new UtilsVote(main);
		DataManager dm = new DataManager(main);
		VoteManager vm = new VoteManager(main);
		
		ItemStack filler1 = uv.getItem("", "", 160, 1, (byte) 4, false, false);
		ItemStack filler2 = uv.getItem("", "", 160, 1, (byte) 14, false, false);
		ItemStack filler3 = uv.getItem("", "", 160, 1, (byte) 1, false, false);
		ItemStack filler4 = uv.getItem("", "", 160, 1, (byte) 0, false, false);
		
		Inventory inv = Bukkit.createInventory(p, 54, "§4* §8Menu des votes");
		
		for(int i = 0; i < inv.getSize(); i++)
		{
			inv.setItem(i, filler4);
		}
		
		//String click = ";;§6§l » §eClique-gauche: §fObtenir le lien de vote;§6§l » §eClique-droit: §fValider votre vote et;    §frécupérer les récompenses !;;§6§l » §eRécompenses:;  §f• §e1x Clef de Vote;  §f";
		
		String click = "";
		
		for(String s : main.getConfig().getStringList("config.texts.minecart"))
		{
			click = click + s + ";";
			click = click.replace("&", "§");
		}
		
		String voteStatus = "";
		int intHasVoted = 1;
		
		if(vm.canPlayerVoteAgain(p, 1))
		{
			voteStatus = "§7(§a!§7) §aVous pouvez voter sur ce lien";
			intHasVoted = 328;
		} else {
			voteStatus = "§7(§e!§7) §eVous pourrez voter sur ce lien " +  vm.timeUntileCDIsOver(p, 1);
			intHasVoted = 342;
		}
		
		ItemStack nLink1 = uv.getItem("§eLien de vote n°1", ";§e* §7Site: §ewww.serveur-prive.net;§e* §7Délai: §e1 heure 30;;" + voteStatus + click, intHasVoted, 1, (byte) 0, false, false);
		
		if(vm.canPlayerVoteAgain(p, 2))
		{
			voteStatus = "§7(§a!§7) §aVous pouvez voter sur ce lien";
			intHasVoted = 328;
		} else {
			voteStatus = "§7(§e!§7) §eVous pourrez voter sur ce lien " +  vm.timeUntileCDIsOver(p, 2);
			intHasVoted = 342;
		}
		
		ItemStack nLink2 = uv.getItem("§eLien de vote n°2", ";§e* §7Site: §ewww.liste-serveurs-minecraft.org;§e* §7Délai: §e3 heures;;" + voteStatus + click, intHasVoted, 1, (byte) 0, false, false);
		
		if(vm.canPlayerVoteAgain(p, 3))
		{
			voteStatus = "§7(§a!§7) §aVous pouvez voter sur ce lien";
			intHasVoted = 328;
		} else {
			voteStatus = "§7(§e!§7) §eVous pourrez voter sur ce lien " +  vm.timeUntileCDIsOver(p, 3);
			intHasVoted = 342;
		}
			
		ItemStack nLink3 = uv.getItem("§eLien de vote n°3", ";§e* §7Site: §ewww.serveursminecraft.org;§e* §7Délai: §e24 heures;;" + voteStatus + click, intHasVoted, 1, (byte) 0, false, false);

		if(vm.canPlayerVoteAgain(p, 4))
		{
			voteStatus = "§7(§a!§7) §aVous pouvez voter sur ce lien";
			intHasVoted = 328;
		} else {
			voteStatus = "§7(§e!§7) §eVous pourrez voter sur ce lien " +  vm.timeUntileCDIsOver(p, 4);
			intHasVoted = 342;
		}
			
		ItemStack nLink4 = uv.getItem("§eLien de vote n°4", ";§e* §7Site: §ewww.serveurs-minecraft.org;§e* §7Délai: §e24 heures;;" + voteStatus + click, intHasVoted, 1, (byte) 0, false, false);

		
		SkullMeta  meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);

		meta.setOwner(p.getName());
		
		ItemStack skull = uv.getItem("  §7-[§e*§7]- §e" + p.getName() + " §7-[§e*§7]-", ";§e* §7Tes votes:;§e  - §7Ce mois: §e" + dm.getCountMonth(p) + ";§e  - §7Total: §e" + dm.getCountAll(p) + ";;§e* §7Event Vote: §e" + main.getConfig().getInt("config.voteParty.current") + "§6/§e" + main.getConfig().getInt("config.voteParty.needed") + ";  §e-> §7Gagnez §a§l200,000 $§7 !", 397, 1 , (byte) 3, false, false);
		
		meta.setDisplayName(skull.getItemMeta().getDisplayName());
		meta.setLore(skull.getItemMeta().getLore());
		
		skull.setItemMeta(meta);
		
		String loreChall = "";
		
		for(String s : main.getConfig().getStringList("config.texts.challenges"))
		{
			loreChall = loreChall + s + ";";
			loreChall = loreChall.replace("&", "§");
		}
		
		ItemStack chall = uv.getItem("§a§lPALIERS DE VOTES", loreChall, 399, 1, (byte) 0, false, false);
		
		String loreInfo = "";
		
		for(String s : main.getConfig().getStringList("config.texts.instructions"))
		{
			loreInfo = loreInfo + s + ";";
			loreInfo = loreInfo.replace("&", "§");
		}
		
		ItemStack info = uv.getItem("§e§lINFORMATIONS", loreInfo, 340, 1, (byte) 0, false, false);
		
		inv.setItem(0, filler1);
		inv.setItem(1, filler1);
		inv.setItem(7, filler1);
		inv.setItem(8, filler1);
		inv.setItem(9, filler1);
		inv.setItem(17, filler1);
		
		inv.setItem(36, filler1);
		inv.setItem(44, filler1);
		inv.setItem(45, filler1);
		inv.setItem(46, filler1);
		inv.setItem(52, filler1);
		inv.setItem(53, filler1);
		
		inv.setItem(3, filler2);
		inv.setItem(4, filler2);
		inv.setItem(5, filler2);
		inv.setItem(48, filler2);
		inv.setItem(49, filler2);
		inv.setItem(50, filler2);
		
		inv.setItem(2, filler3);
		inv.setItem(6, filler3);
		inv.setItem(18, filler3);
		inv.setItem(27, filler3);
		inv.setItem(26, filler3);
		inv.setItem(35, filler3);
		inv.setItem(47, filler3);
		inv.setItem(51, filler3);

		inv.setItem(20, nLink1);
		inv.setItem(22, nLink3);
		inv.setItem(24, nLink4);
		//inv.setItem(25, nLink4);
		
		inv.setItem(38, info);
		inv.setItem(40, chall);
		inv.setItem(42, skull);
		
		p.openInventory(inv);
		
	}

	public void openChallenges(Player p)
	{
		UtilsVote uv = new UtilsVote(main);
		DataManager dm = new DataManager(main);
		
		FileConfiguration fcp = dm.getPlayerConfigurationFile(p);
		
		Inventory inv = Bukkit.createInventory(p, 36, "§4* §8Paliers mensuels"); 
		
		ItemStack is;
		int i = 0;
		
		int[] slots = {11, 20, 21, 22, 13, 14, 15, 24};
		
		int month = fcp.getInt("datas.count.month");
		int chall = fcp.getInt("datas.challenges");
		
		for(String s : main.getConfig().getConfigurationSection("config.paliers").getKeys(false))
		{
			
			int idIs;
			String status = "";
			String loreAwards = "";
			
			if(chall > i)
			{
				idIs = 422;
				status = ";§f§l(§a§l!§f§l) §a§lPalier débloqué !";
			} else {
				
				for(String s1 : main.getConfig().getStringList("config.paliers." + s + ".awards"))
				{
					loreAwards = loreAwards + s1 + ";";
					loreAwards = loreAwards.replace("&", "§");
				}
				
				if(month >= Integer.valueOf(s))
				{
					if(chall == i)
					{
						idIs = 343;
						status = ";§f§l(§e§l!§f§l) §e§lPlalier complété;;§f§l(§a§l!§f§l) §a§lClique pour obtenir ta récompense !";
					} else {
						idIs = 328;
						status = ";§f§l(§c§l!§f§l) §c§lPalier à débloquer !;;§f§l(§e§l!§f§l) §e§lTu dois débloquer le palier précédent !";
					}
				} else {
					idIs = 328;
					status = ";§f§l(§c§l!§f§l) §c§lPalier non débloqué ! §f§l(§e§l" + month + "§f§l/§e§l" + s + "§f§l)";
				}
				
			}
			
			is = uv.getItem("§ePalier n°" + (i + 1), status + ";" + loreAwards, idIs, 1, (byte) 0, false, false);
			inv.setItem(slots[i], is);
			
			i++;
		}
		
		p.openInventory(inv);
	}
}
