package eu.nouss.hackatonleaderboard.domain;

public class LeaderboardEntry implements Comparable<LeaderboardEntry>  {
    private String name;
    private int value;

    public LeaderboardEntry() {
        this.value = 0;
    }

    public LeaderboardEntry(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(LeaderboardEntry leaderboardEntry) {
        if(this.getValue() < leaderboardEntry.getValue()) return 1;
        else if(this.getValue() == leaderboardEntry.getValue()) return 0;
        else return -1;
    }
}
