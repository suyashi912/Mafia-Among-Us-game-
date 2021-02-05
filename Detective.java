import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public final class Detective extends Player {

    private final Scanner in = new Scanner(System.in);
    private genericMap<Detective> d1;
    public Detective(int id) {
        super(id);
        this.setPlayerHP(800);
        d1 = new genericMap<Detective>();                 //Since all detectives know about other detectives
    }

    public void setD1(genericMap<Detective> d1) {
        this.d1 = d1;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o != null && getClass() == o.getClass())
        {
            Detective d = (Detective)o;
            return (super.equals(o) && getId() == d.getId());
        }
        else
            return false;
    }

    @Override
    public void printOthers(User i) {
        Iterator it = d1.entrySet().iterator();
        System.out.print("Other detectives are: [");
        int s = 0;
        while (it.hasNext())
        {
            s++;
            Map.Entry o = (Map.Entry)it.next();
            Player p = (Player)o.getValue();
            if(!i.getP().equals(p))
            {
                if(s< d1.size()-1)
                    System.out.print("Player" + o.getKey() + ", ");
                else if(s< d1.size())
                    System.out.print("Player" + o.getKey() +" and ");
                else
                    System.out.print("Player" + o.getKey());
            }

        }
        System.out.println("]");
    }

    @Override
    public int choose(User u1) {
            System.out.print("Choose a player to test: ");
            int i = in.nextInt();
            while (!u1.getG1().getPlay().containsKey(i) || u1.getG1().getPlay().get(i) instanceof Detective) {

                if(u1.getG1().getPlay().get(i) instanceof Detective)
                {
                    System.out.print("You cannot test a detective. ");
                    System.out.print("Choose a player to test: ");
                }
                else
                {
                    System.out.print("Invalid input. Choose a player to test: ");
                }
                i = in.nextInt();
            }
            return i;
        }

        public static int simulateDetective(Game g2)
        {
            int j = Game.getM().nextInt(g2.getNumberOfplayers()) + 1;
            while (!g2.getPlay().containsKey(j) || g2.getDetectiveL().containsKey(j)) {
                j =Game.getM().nextInt(g2.getNumberOfplayers()) + 1;
            }
            System.out.println("Detectives have chosen a player to test.");
            return j;
        }

    }

