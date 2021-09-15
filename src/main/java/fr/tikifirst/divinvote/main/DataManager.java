package fr.tikifirst.divinvote.main;

import org.bukkit.OfflinePlayer;

public interface DataManager {

    int getCountMonth(OfflinePlayer p);
    void incrementCountMonth(OfflinePlayer p);
    int getCountAll(OfflinePlayer p);
    void incrementCountAll(OfflinePlayer p);
    long getLastVoted(OfflinePlayer p);
    void setLastVoted(OfflinePlayer p);
    void setChall(OfflinePlayer p, long count_chall);
    void resetMonth();
    void resetChall();

}
