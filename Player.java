abstract public class Player {
    private double playerHP;
    private final int id;
    public Player(int id)
    {
        this.playerHP = 0;
        this.id = id;
    }
    public void setPlayerHP(double playerHP) {
        this.playerHP = playerHP;
    }

    public int getId() {
        return id;
    }

    public double getPlayerHP() {
        return playerHP;
    }
   public abstract void printOthers(User i);


    public abstract int choose(User u1);             // Prompts the user to choose his choice of target/test/person to be healed

    @Override
    public boolean equals(Object o)
    {
        if(o != null && getClass() == o.getClass())
        {
            Player o1 = (Player)o;
            return (id == o1.getId());
        }
        else
            return false;
    }

    //randomly select person to vote from alive list - defined in this class since it is same for all users
    public int vote(Game g2)
    {
        int v = Game.getM().nextInt(g2.getNumberOfplayers()) + 1;
        while (!g2.getPlay().containsKey(v)) {
            v =Game.getM().nextInt(g2.getNumberOfplayers()) + 1;
        }
        return v;
    }
}
