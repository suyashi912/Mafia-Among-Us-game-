public final class frequency implements Comparable<frequency> {                     // this class implements comparable
    private final int playerId;
    private int votes;

    public frequency(int i) {
        this.playerId = i;
        this.votes = 0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public int compareTo(frequency o) {
        int compareVote = o.getVotes();
        return this.getVotes() - compareVote;
    }
}
