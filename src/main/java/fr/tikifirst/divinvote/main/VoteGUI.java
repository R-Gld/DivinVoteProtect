package fr.tikifirst.divinvote.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
	
	public void openMain(Player p) {
		UtilsVote uv = new UtilsVote(main);
		DataManager_SQL dm = new DataManager_SQL();
		VoteManager vm = new VoteManager(main);
		
		ItemStack filler1 = uv.getItem("", "", 160, 1, (byte) 4, false, false);
		ItemStack filler2 = uv.getItem("", "", 160, 1, (byte) 0, false, false);
		
		Inventory inv = Bukkit.createInventory(p, 54, "§4* §8Menu des votes");
	
		
		//String click = ";;§6§l » §eClique-gauche: §fObtenir le lien de vote;§6§l » §eClique-droit: §fValider votre vote et;    §frécupérer les récompenses !;;§6§l » §eRécompenses:;  §f• §e1x Clef de Vote;  §f";
		
		StringBuilder click = new StringBuilder();
		
		for(String s : main.getConfig().getStringList("config.texts.minecart"))
		{
			click.append(s).append(";");
			click = new StringBuilder(click.toString().replace("&", "§"));
		}
		
		String voteStatus;
		int intHasVoted;
		
		if(vm.canPlayerVoteAgain(p, 1))
		{
			voteStatus = "§7(§a!§7) §aTu peux voter sur ce lien !";
			intHasVoted = 328;
		} else {
			voteStatus = "§7(§e!§7) §eTu pourras voter sur ce lien " +  vm.timeUntilCDIsOver(p, 1);
			intHasVoted = 342;
		}
		
		ItemStack nLink1 = uv.getItem("§eLien de vote n°1", "; §7Délai: §b1 heure 30;;" + voteStatus + click, intHasVoted, 1, (byte) 0, false, false);
		
		if(vm.canPlayerVoteAgain(p, 2))
		{
			voteStatus = "§7(§a!§7) §aTu peux voter sur ce lien !";
			intHasVoted = 328;
		} else {
			voteStatus = "§7(§e!§7) §eTu pourras voter sur ce lien " +  vm.timeUntilCDIsOver(p, 2);
			intHasVoted = 342;
		}
		
		ItemStack nLink2 = uv.getItem("§eLien de vote n°2", "; §7Délai: §b2 heures;;" + voteStatus + click, intHasVoted, 1, (byte) 0, false, false);
		
		if(vm.canPlayerVoteAgain(p, 3))
		{
			voteStatus = "§7(§a!§7) §aTu peux voter sur ce lien !";
			intHasVoted = 328;
		} else {
			voteStatus = "§7(§e!§7) §eTu pourras voter sur ce lien " +  vm.timeUntilCDIsOver(p, 3);
			intHasVoted = 342;
		}
			
		ItemStack nLink3 = uv.getItem("§eLien de vote n°3", "; §7Délai: §b24 heures;;" + voteStatus + click, intHasVoted, 1, (byte) 0, false, false);

		if(vm.canPlayerVoteAgain(p, 4))
		{
			voteStatus = "§7(§a!§7) §aTu peux voter sur ce lien !";
			intHasVoted = 328;
		} else {
			voteStatus = "§7(§e!§7) §eTu pourras voter sur ce lien " +  vm.timeUntilCDIsOver(p, 4);
			intHasVoted = 342;
		}
			
		ItemStack nLink4 = uv.getItem("§eLien de vote n°4", "; §7Délai: §b24 heures;;" + voteStatus + click, intHasVoted, 1, (byte) 0, false, false);

		
		SkullMeta  meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);

		meta.setOwner(p.getName());
		
		ItemStack skull = uv.getItem("§e" + p.getName(), ";§6§lTES VOTES:;§e • §7Ce mois: §e" + dm.getCountMonth(p) + ";§e • §7Total: §e" + dm.getCountAll(p) + ";;§b§lEvent Vote §7» §e" + main.getConfig().getInt("config.voteParty.current") + "§6/§e" + main.getConfig().getInt("config.voteParty.needed") + " votes;;§7 Un objectif commun à tous les joueurs.;§7 Dès que l'objectif est atteint, tout le; §7monde recevra §a300,000 $ §7!;§e", 397, 1 , (byte) 3, false, false);
		
		meta.setDisplayName(skull.getItemMeta().getDisplayName());
		meta.setLore(skull.getItemMeta().getLore());
		
		skull.setItemMeta(meta);
		
		StringBuilder loreChall = new StringBuilder();
		
		for(String s : main.getConfig().getStringList("config.texts.challenges"))
		{
			loreChall.append(s).append(";");
			loreChall = new StringBuilder(loreChall.toString().replace("&", "§"));
		}
		
		ItemStack chall = uv.getItem("§a§lPALIERS DE VOTES", loreChall.toString(), 399, 1, (byte) 0, false, false);
		
		StringBuilder loreInfo = new StringBuilder();
		
		for(String s : main.getConfig().getStringList("config.texts.instructions"))
		{
			loreInfo.append(s).append(";");
			loreInfo = new StringBuilder(loreInfo.toString().replace("&", "§"));
		}
		
		ItemStack info = uv.getItem("§e§lINFORMATIONS", loreInfo.toString(), 340, 1, (byte) 0, false, false);
		
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
		
		inv.setItem(2, filler2);
		inv.setItem(3, filler2);
		inv.setItem(5, filler2);
		inv.setItem(6, filler2);
		inv.setItem(47, filler2);
		inv.setItem(48, filler2);
		inv.setItem(49, filler2);
		inv.setItem(50, filler2);
		inv.setItem(51, filler2);

		inv.setItem(19, nLink1);
		inv.setItem(21, nLink2);
		inv.setItem(23, nLink3);
		inv.setItem(25, nLink4);
		
		inv.setItem(39, info);
		inv.setItem(41, chall);
		inv.setItem(4, skull);
		
		p.openInventory(inv);
		
	}

	public void openChallenges(Player p) {
		UtilsVote uv = new UtilsVote(main);
		DataManager_SQL dm2 = new DataManager_SQL();
		DivinPlayer dp = dm2.getDivinPlayer(p);
		
		Inventory inv = Bukkit.createInventory(p, 36, "§4* §8Paliers mensuels"); 
		
		ItemStack is;
		int i = 0;
		
		int[] slots = {11, 20, 21, 22, 13, 14, 15, 24};
		
		long month = dp.getCount_months();
		long chall = dp.getCount_all();
		
		for(String s : main.getConfig().getConfigurationSection("config.paliers").getKeys(false)) {
			
			int idIs;
			String status;
			StringBuilder loreAwards = new StringBuilder();
			
			if(chall > i) {
				idIs = 422;
				status = ";§2§l » §a§lPalier débloqué !";
			} else {
				
				for(String s1 : main.getConfig().getStringList("config.paliers." + s + ".awards"))
				{
					loreAwards.append(s1).append(";");
					loreAwards = new StringBuilder(loreAwards.toString().replace("&", "§"));
				}
				
				if(month >= Integer.parseInt(s))
				{
					if(chall == i)
					{
						idIs = 343;
						status = ";§2 ✔ §aPlalier complété;;§2§l » §a§lClique pour obtenir ta récompense !";
						//status = ";§f§l(§e§l!§f§l) §e§lPlalier complété;;§f§l(§a§l!§f§l) §a§lClique pour obtenir ta récompense !";
					} else {
						idIs = 328;
						status = ";§4 ✖ §cPalier à débloquer !;;§6§l » §e§lTu dois débloquer le palier précédent !";
						//status = ";§f§l(§c§l!§f§l) §c§lPalier à débloquer !;;§f§l(§e§l!§f§l) §e§lTu dois débloquer le palier précédent !";
					}
				} else {
					idIs = 328;
					status = ";§4 ✖ §cPalier non débloqué: §e" + month + "§6/§e" + s + " votes";
					//status = ";§f§l(§c§l!§f§l) §c§lPalier non débloqué ! §f§l(§e§l" + month + "§f§l/§e§l" + s + "§f§l)";
				}
			}
			
			is = uv.getItem("§ePalier n°" + (i + 1), status + ";" + loreAwards, idIs, 1, (byte) 0, false, false);
			inv.setItem(slots[i], is);
			
			i++;
		}
		
		p.openInventory(inv);
	}
}
