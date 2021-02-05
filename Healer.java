import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public final class Healer extends Player {

    private final Scanner in = new Scanner(System.in);
    private genericMap<Healer> h1;
    public Healer(int id) {
        super(id);
        this.setPlayerHP(800);
        h1 = new genericMap<Healer>();
    }

    public void setH1(genericMap<Healer> h1) {
        this.h1 = h1;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o != null && getClass() == o.getClass())
        {
            Healer h = (Healer) o;
            return (super.equals(o) && h.getId() == getId());
        }
        else
            return false;
    }


    @Override
    public void printOthers(User i) {
        Iterator it = h1.entrySet().iterator();
        System.out.print("Other healers are: [");
        int s = 0;
        while (it.hasNext())
        {
            s++;
            Map.Entry o = (Map.Entry)it.next();
            Player p = (Player)o.getValue();
            if(!i.getP().equals(p))
            {
                if(s< h1.size()-1)
                    System.out.print("Player" + o.getKey() + ", ");
                else if(s< h1.size())
                    System.out.print("Player" + o.getKey() +" and ");
                else
                    System.out.print("Player" + o.getKey());
            }

        }
        System.out.println("]");
    }


        @Override
    public int choose(User u1) {
            System.out.print("Choose a player to heal: ");
            int i = in.nextInt();
            while (!u1.getG1().getPlay().containsKey(i)) {
                System.out.print("Invalid input. Choose a player to heal:  ");
                i = in.nextInt();
            }
            return i;
        }



        public static void hpHeal(Game g,int b)
        {
            double w = g.getPlay().get(b).getPlayerHP();
            g.getPlay().get(b).setPlayerHP(w+500);
        }



    public static int simulateHealer(Game g2)
    {
        int j = Game.getM().nextInt(g2.getNumberOfplayers()) + 1;
        while (!g2.getPlay().containsKey(j)) {
            j = Game.getM().nextInt(g2.getNumberOfplayers()) + 1;
        }
        System.out.println("Healers have chosen someone to heal.");
        return j;
    }

}

