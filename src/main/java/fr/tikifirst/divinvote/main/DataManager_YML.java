package fr.tikifirst.divinvote.main;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataManager_YML implements DataManager {

	public DataManager_YML() {}
	
	
	public void saveData(File f, FileConfiguration fc)
	{
		try {
			fc.save(f);
		} catch (IOException e) {
			System.out.println("[DivinVote] ERROR: save file");
		}
	}
	
	public File getPlayerFile(OfflinePlayer p)
	{
		UUID uuid = p.getUniqueId();
		File playerDataFile = new File("plugins/DivinVote/PlayerDatas");
				
		if(!playerDataFile.exists())
		{
			playerDataFile.mkdir();
		}

		File playerData = new File("plugins/DivinVote/PlayerDatas/" + uuid + ".yml");
				
		if(!playerData.exists())
		{
			try {
				playerData.createNewFile();
			} catch (IOException ex) {
				System.out.println("[DivinVote] Erreur creation fichier player !");
				return null;
			}
		
			FileConfiguration PDFile = YamlConfiguration.loadConfiguration(playerData);
			
			PDFile.createSection("datas");
			PDFile.createSection("datas.count");
			
			PDFile.set("datas.count.month", 0);
			PDFile.set("datas.count.all", 0);
			PDFile.set("datas.lastVoted", 0);
			
			try {
				PDFile.save(playerData);
			} catch (IOException e) {
				System.out.println("[DivinVote] ERROR: Player file");
				return null;
			}
			
		}
		
		return playerData;
	}
	
	public FileConfiguration getPlayerConfigurationFile(OfflinePlayer p)
	{
		File playerData = getPlayerFile(p);
		
		FileConfiguration PDFile = YamlConfiguration.loadConfiguration(playerData);
		
		try {
			PDFile.save(playerData);
		} catch (IOException e) {
			System.out.println("[DivinVote] ERROR: Player conf file");
			return null;
		}
		
		
		return PDFile;
	}
	
	public int getCountMonth(OfflinePlayer p)
	{
		FileConfiguration fcp = getPlayerConfigurationFile(p);

		return fcp.getInt("datas.count.month");
	}
	
	public void incrementCountMonth(OfflinePlayer p)
	{
		FileConfiguration fcp = getPlayerConfigurationFile(p);
		
		fcp.set("datas.count.month", getCountMonth(p) + 1);
		saveData(getPlayerFile(p), fcp);
	}
	
	
	public int getCountAll(OfflinePlayer p)
	{
		FileConfiguration fcp = getPlayerConfigurationFile(p);

		return fcp.getInt("datas.count.all");
	}
	
	public void incrementCountAll(OfflinePlayer p)
	{
		FileConfiguration fcp = getPlayerConfigurationFile(p);
		
		fcp.set("datas.count.all", getCountAll(p) + 1);
		saveData(getPlayerFile(p), fcp);
	}
	

	public long getLastVoted(OfflinePlayer p)
	{
		FileConfiguration fcp = getPlayerConfigurationFile(p);
		return fcp.getInt("datas.lastVoted");
	}
	
	public void setLastVoted(OfflinePlayer p)
	{
		FileConfiguration fcp = getPlayerConfigurationFile(p);
		
		fcp.set("datas.lastVoted", (System.currentTimeMillis()/1000));
		saveData(getPlayerFile(p), fcp);
	}

	public void setChall(OfflinePlayer p, long count_chall) {
		throw new UnsupportedOperationException("This Function is not supported when the data type is: YML");
	}


	public void resetMonth() 
	{
		File playerDatasFile = new File("plugins/DivinVote/PlayerDatas");
		
		if(!playerDatasFile.exists())
		{
			playerDatasFile.mkdir();
		}
		
		File[] listFiles = playerDatasFile.listFiles();

		assert listFiles != null;
		for (File pFile : listFiles) {
			String id = pFile.getName();
			id = id.replace(".yml", "");

			FileConfiguration fcp = YamlConfiguration.loadConfiguration(pFile);
			OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(id));

			fcp.set("datas.count.month", 0);
			fcp.set("datas.challenges", 0);

			saveData(getPlayerFile(op), fcp);
		}
	}
	
	public void resetChall() 
	{
		File playerDatasFile = new File("plugins/DivinVote/PlayerDatas");
		
		if(!playerDatasFile.exists())
		{
			playerDatasFile.mkdir();
		}
		
		File[] listFiles = playerDatasFile.listFiles();

		assert listFiles != null;
		for (File pFile : listFiles) {
			String id = pFile.getName();
			id = id.replace(".yml", "");

			FileConfiguration fcp = YamlConfiguration.loadConfiguration(pFile);
			OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(id));

			fcp.set("datas.challenges", 0);

			saveData(getPlayerFile(op), fcp);
		}
	}
	
}
