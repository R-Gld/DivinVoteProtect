package fr.tikifirst.divinvote.main;

import java.util.UUID;

public class DivinPlayer {

    private final UUID uuid;
    private final String ip;
    private final long vote_1;
    private final long vote_2;
    private final long vote_3;
    private final long vote_4;
    private final long count_months;
    private final long count_all;
    private final long count_challenge;
    private final long last_voted;

    public DivinPlayer(UUID uuid, String ip, long vote_1, long vote_2, long vote_3, long vote_4, long count_months, long count_all, long count_challenge, long last_voted) {
        this.uuid = uuid;
        this.ip = ip;
        this.vote_1 = vote_1;
        this.vote_2 = vote_2;
        this.vote_3 = vote_3;
        this.vote_4 = vote_4;
        this.count_months = count_months;
        this.count_all = count_all;
        this.count_challenge = count_challenge;
        this.last_voted = last_voted;
    }

    public DivinPlayer(String uuid, String ip, long vote_1, long vote_2, long vote_3, long vote_4, long count_months, long count_all, long count_challenge, long last_voted) {
        this.uuid = UUID.fromString(uuid);
        this.ip = ip;
        this.vote_1 = vote_1;
        this.vote_2 = vote_2;
        this.vote_3 = vote_3;
        this.vote_4 = vote_4;
        this.count_months = count_months;
        this.count_all = count_all;
        this.count_challenge = count_challenge;
        this.last_voted = last_voted;
    }

    public UUID getUuid() {
        return uuid;
    }
    public String getIp() {
        return ip;
    }
    public long getVote_1() {
        return vote_1;
    }
    public long getVote_2() {
        return vote_2;
    }
    public long getVote_3() {
        return vote_3;
    }
    public long getVote_4() {
        return vote_4;
    }
    public long getCount_months() {
        return count_months;
    }
    public long getCount_all() {
        return count_all;
    }
    public long getCount_challenge() {
        return count_challenge;
    }
    public long getLast_voted() {
        return last_voted;
    }

}
