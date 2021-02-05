import java.util.Scanner;

public final class Commoner extends Player {
    private final Scanner in = new Scanner(System.in);
    public Commoner(int id) {
        super(id);
        this.setPlayerHP(1000);
    }

    @Override
    public void printOthers(User i) {
    }

    @Override
    public int choose(User u1) {
            System.out.println("Choose a person to vote out ");
            int i = in.nextInt();
            while (!u1.getG1().getPlay().containsKey(i)) {
                System.out.println("Invalid input. Choose a person to vote out ");
                i = in.nextInt();
            }
            return i;
        }

    @Override
    public boolean equals(Object o)
    {
        if(o != null && getClass() == o.getClass())
        {
            Commoner c = (Commoner) o;
            return (super.equals(o) && c.getId() ==getId());
        }
        else
            return false;
    }

}
