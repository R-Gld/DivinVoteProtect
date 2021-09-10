package fr.tikifirst.divinvote.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UtilsVote {
	
	public Main main;
	
	public UtilsVote(Main main) {
		this.main = main;
	}


	
	public void voteIsOkay(Player p, int link)
	{
		DataManager dm = new DataManager(main);
		FileConfiguration fcp = dm.getPlayerConfigurationFile(p);
		
		fcp.set("datas.lastVotedOn." + link, (System.currentTimeMillis()/1000));
		fcp.set("datas.count.month", fcp.getInt("datas.count.month") + 1);
		fcp.set("datas.count.all", fcp.getInt("datas.count.all") + 1);
		
		dm.saveData(dm.getPlayerFile(Bukkit.getOfflinePlayer(p.getUniqueId())), fcp);
		
		int voteParty = main.getConfig().getInt("config.voteParty.current") + 1;
		
		main.getConfig().set("config.voteParty.current", voteParty);
		main.saveConfig(); 
		
		String bcMsg = main.getConfig().getString("config.texts.broadcastVote");
		bcMsg = bcMsg.replace("%p", p.getName());
		bcMsg = bcMsg.replace("&", "§");
		
		Bukkit.broadcast(bcMsg, "divin.basics");
		
		List<String> awards = main.getConfig().getStringList("config.voteParty.recompense");
		
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		
		for(String cmds : awards)
		{
			cmds = cmds.replaceAll("%p", p.getName());
			Bukkit.dispatchCommand(console, cmds);
		}
		
		int needed = main.getConfig().getInt("config.voteParty.needed");
		int neededLeft = needed - voteParty;

		if(voteParty >= needed - 1)
		{
			Bukkit.dispatchCommand(console, main.getConfig().getString("config.voteParty.recompense"));
			
			main.getConfig().set("config.voteParty.current", 0);
			main.saveConfig();
			
			Bukkit.broadcastMessage("     §f§m-----------§e * §lEVENT VOTE§e * §f§m-----------§e");
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§f     §e§l" + neededLeft + " votes§f atteints ! Tout le monde");
			Bukkit.broadcastMessage("          reçoit §e§l+200,000 $ §finstantanément !");
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("     §d§lMERCI POUR VOTRE SOUTIENS <3");
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("     §f§m-------------------------------------");
			
			//Bukkit.broadcastMessage("§e§lEVENT VOTE§7§6§l » " + main.getConfig().getInt("config.voteParty.needed") + " Votes atteints ! Tout le monde reçoit §e200,000$ §a!");
		} else {
			
			if(neededLeft % 20 == 0 || neededLeft < 30)
			{
				Bukkit.broadcastMessage("     §f§m-----------§e * §lEVENT VOTE§e * §f§m-----------§e");
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage("§f     Plus que §e§l" + neededLeft + " votes§f avant que tout le monde");
				Bukkit.broadcastMessage("          reçoive §e§l200,000 $ §finstantanément !");
				Bukkit.broadcastMessage("");
				Bukkit.broadcastMessage("     §f§m-------------------------------------");
			} 
		}

		for(String s : main.getConfig().getStringList("config.recompenses"))
		{
			s = s.replace("%p", p.getName());
			Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), s);
		}
	}
	
	@SuppressWarnings("unused")
	public boolean hasVoted1(Player p)  // www.serveur-prive.net
	{ 		
		String ip = p.getAddress().getAddress().toString().replace("/", "");
        
        URL url;
        
        BufferedReader in;
        try {
        	
        	String apiLink = main.getConfig().getString("config.apiManager.1");
        	apiLink = apiLink.replace("%ip", ip);
        	
            url = new URL(apiLink);
            URLConnection con = url.openConnection();
            
            InputStream instream = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding; 
            String body = IOUtils.toString(instream, encoding);
            if(p.getName().equalsIgnoreCase("Tikifirst")) p.sendMessage("§e!! 1 => " + body);
            if(body.equals("1")) {
            	p.sendMessage("§7(§a!§7) §aVote complété et vérifié !");
                return true;
            } else {
              	p.sendMessage("§7(§c!§7) §cVote non complété ! Essaie de voter correctement sur ce site de vote !");
                return false;
            }
        } catch(Throwable t) {
          	p.sendMessage("§7(§c!§7) §cVote non complété ! Essaie de voter correctement sur ce site de vote ! (Code 1)");
            System.out.println("[DivinVote] False du to an error.");
            return false;
        }
		
	}

	
	@SuppressWarnings("unused")
	public boolean hasVoted2(Player p)  // www.liste-serveurs-minecraft.org
	{ 
    	String ip = p.getAddress().getAddress().toString().replace("/", ""); 
        
        URL url;
        
        BufferedReader in;

        try {
        	         	
        	String apiLink = main.getConfig().getString("config.apiManager.2");
        	apiLink = apiLink.replace("%ip", ip);
        	
            url = new URL(apiLink);
            URLConnection con = url.openConnection();
            
            InputStream instream = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding; 
            String body = IOUtils.toString(instream, encoding);
            
            if(body.equals("1")) {
            	p.sendMessage("§7(§a!§7) §aVote complété et vérifié !");
                return true;
            } else {
              	p.sendMessage("§7(§c!§7) §cVote non complété ! Essaie de voter correctement sur ce site de vote !");
                return false;
            }
        } catch(Throwable t) {
          	p.sendMessage("§7(§c!§7) §cVote non complété ! Essaie de voter correctement sur ce site de vote ! (Code 1)");
            System.out.println("[DivinVote] False du to an error.");
            return false;
        }
     }

	
	@SuppressWarnings("unused")
	public boolean hasVoted3(Player p) // www.serveursminecraft.org
	{
		
		String ip = p.getAddress().getAddress().toString().replace("/", "");
         
         URL url;
         
         BufferedReader in; 
         try {         	
         	String apiLink = main.getConfig().getString("config.apiManager.3");
         	apiLink = apiLink.replace("%ip", ip);
         	
             url = new URL(apiLink);
             URLConnection con = url.openConnection();
             
             InputStream instream = con.getInputStream();
             String encoding = con.getContentEncoding();
             encoding = encoding == null ? "UTF-8" : encoding;
             String body = IOUtils.toString(instream, encoding);
             if(p.getName().equalsIgnoreCase("Tikifirst")) p.sendMessage("§e!! 3 => " + body);
             if(Integer.valueOf(body) < 86400)
             {
            	 p.sendMessage("§7(§a!§7) §aVote complété et vérifié !");
                 return true;
             } else {
               	p.sendMessage("§7(§c!§7) §cVote non complété ! Essaie de voter correctement sur ce site de vote !");
                 return false;
             }
         } catch(Throwable t) {
          	p.sendMessage("§7(§c!§7) §cVote non complété ! Essaie de voter correctement sur ce site de vote ! (Code 1)");
             System.out.println("[DivinVote] False du to an error.");
             return false;
         }
     }
	
	
	@SuppressWarnings("unused")
	public boolean hasVoted4(Player p) // www.serveurs-minecraft.org
	{
		
		String ip = p.getAddress().getAddress().toString().replace("/", "");
         
         URL url;
         
         BufferedReader in; 
         try {         	
         	String apiLink = main.getConfig().getString("config.apiManager.4");
         	apiLink = apiLink.replace("%ip", ip);

         	
             url = new URL(apiLink);
             URLConnection con = url.openConnection();
             
             InputStream instream = con.getInputStream();
             String encoding = con.getContentEncoding();
             encoding = encoding == null ? "UTF-8" : encoding;
             String body = IOUtils.toString(instream, encoding); 
             if(p.getName().equalsIgnoreCase("Tikifirst")) p.sendMessage("§e!! 4 => " + body);
             if(body.equals("1") || body.equals("2")) {
            	 p.sendMessage("§7(§a!§7) §aVote complété et vérifié !");
                 return true;
             } else {
               	p.sendMessage("§7(§c!§7) §cVote non complété ! Essaie de voter correctement sur ce site de vote !");
                 return false;
             }
         } catch(Throwable t) {
           	p.sendMessage("§7(§c!§7) §cVote non complété ! Essaie de voter correctement sur ce site de vote ! (Code 1)");
             System.out.println("[DivinVote] False du to an error.");
             return false;
         }
     }
	
	/*
	 * 
	 * UTILS
	 * 
	 */
	
	@SuppressWarnings("deprecation")
	public ItemStack getItem(String name, String lore, int id, int amount, byte data, boolean isTool, boolean enchanted)
	{
		ItemStack is = new ItemStack(id,amount);
		
		if(!isTool)
		{
			is.setDurability(data);
		}
		
		ItemMeta im = is.getItemMeta();
		
		im.setDisplayName(name);
		
		int i;
		
		String[] lores = lore.split(";");
		ArrayList<String> listLore = new ArrayList<String>();
		
		for(i = 0; i < lores.length; i++)
		{
			listLore.add(lores[i]);
		}
		
		im.setLore(listLore);
		
		if(enchanted)
		{
			im.addEnchant(Enchantment.SILK_TOUCH, 1, false);
			im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		
		is.setItemMeta(im);
		
		return is;

	}


}
