package eu.nouss.hackatonleaderboard.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard  {
    private List<LeaderboardEntry> entries;

    public Leaderboard() {
        this.entries = new ArrayList<>();
    }

    public List<LeaderboardEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<LeaderboardEntry> entries) {
        this.entries = entries;
    }


    public void addEntry(LeaderboardEntry entry) {
        this.entries.add(entry);
        Collections.sort(this.entries);
    }
}


