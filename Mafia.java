import java.util.*;

public final class Mafia extends Player{

    private final Scanner in = new Scanner(System.in);
    private genericMap<Mafia> m1;                                          // stores all mafias - so that each mafia knows about other mafias

    public Mafia(int id)
    {
        super(id);
        this.setPlayerHP(2500);
        m1 = new genericMap<Mafia>();
    }

    public void setM1(genericMap<Mafia> m1) {
        this.m1 = m1;
    }

    @Override
    public void printOthers(User i) {
        Iterator it = m1.entrySet().iterator();
        System.out.print("Other mafias are: [");
        int s = 0;
        while (it.hasNext())
        {
            s++;
            Map.Entry o = (Map.Entry)it.next();
            Player p = (Player)o.getValue();
            if(!i.getP().equals(p))                                               // using equals method - checks if the player p in GenericMap is user or not
            {
                if(s< m1.size()-1)
                    System.out.print("Player" + o.getKey() + ", ");
                else if(s< m1.size())
                    System.out.print("Player" + o.getKey() +" and ");
                else
                    System.out.print("Player" + o.getKey());
            }

        }
        System.out.println("]");
    }

        @Override
    public int choose(User u1)
    {
            System.out.print("Choose a target: ");
            int i = in.nextInt();
            while (!u1.getG1().getPlay().containsKey(i) || u1.getG1().getPlay().get(i) instanceof  Mafia) { //choose a mafia who is dead
                if(u1.getG1().getPlay().get(i) instanceof Mafia)
                {
                    System.out.print("You cannot target a mafia. ");
                    System.out.print("Choose a target: ");
                }
                else
                {
                    System.out.print("Invalid input. Choose a target: ");
                }
                i = in.nextInt();
            }
            return i;
    }

    public static int simulateMafia(Game g2)
    {
        int j = Game.getM().nextInt(g2.getNumberOfplayers())+1;
        while(!g2.getPlay().containsKey(j) || g2.getMafiaL().containsKey(j)) //say he is dead and is a mafia
        {
            j = Game.getM().nextInt(g2.getNumberOfplayers())+1;
        }

        System.out.println("Mafias have chosen their target.");
       return j;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o != null && getClass() == o.getClass())
        {
            Mafia m = (Mafia) o;
            return (super.equals(o) && m.getId() == getId());
        }
        else
            return false;
    }


    public static void HpUpdate(User i, int a, Game g)            // a is target
    {
        Iterator it = g.getPlay().entrySet().iterator();
        double sum = 0;                                                                 // stores sum of hp of mafias
        int mAliveCount = 0;                                                            // counts number of mafias with hp>0
        double initHP = g.getPlay().get(a).getPlayerHP();                               // this is the initial hp of the target
        while(it.hasNext())                                                             // we sum the hp of all mafias and store it in sum
        {
            Map.Entry o = (Map.Entry)it.next();
            Player p = (Player)o.getValue();
            if(o.getValue().getClass() == Mafia.class)
            {
                if(((Player) o.getValue()).getPlayerHP()>0)
                {
                    mAliveCount++;
                }
                sum = sum + p.getPlayerHP();
            }
        }
        if(sum>=g.getPlay().get(a).getPlayerHP())                                          // if sum of hp of mafias is greater than or equal to target hp
        {
            g.getPlay().get(a).setPlayerHP(0);                                              // then the hp of target is set to 0
        }
        else
        {
            g.getPlay().get(a).setPlayerHP(initHP-sum);                                     // otherwise target hp = initial target hp - sum of mafia hp
        }

        //next, we update the damages incurred by mafias when they target someone
        double mafiaHpUpgrade = 0;
        if(mAliveCount>0)
        {
             mafiaHpUpgrade = initHP/mAliveCount;
        }
        it = i.getG1().getPlay().entrySet().iterator();
        while(it.hasNext() && mAliveCount>0 && mafiaHpUpgrade>= 0.0000001)
        {
            Map.Entry o = (Map.Entry)it.next();
            Player p = (Player)o.getValue();
            if(o.getValue().getClass() == Mafia.class )
            {
                if(p.getPlayerHP() - mafiaHpUpgrade<0)
                {
                    double add = mafiaHpUpgrade - p.getPlayerHP();
                    p.setPlayerHP(0);
                    mAliveCount--;
                    if(mAliveCount>0)
                    {
                        mafiaHpUpgrade += add/mAliveCount;
                    }
                }
                else
                    p.setPlayerHP(p.getPlayerHP() - mafiaHpUpgrade);
            }
        }
    }
}
