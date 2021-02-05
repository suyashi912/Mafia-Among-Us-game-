import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
public class User {
        private int playNo;
        private Game g1;
        private Player p;
        private final Scanner in = new Scanner(System.in);

        public User()
        {
            playNo = 0;
            p = null;
            g1 = null;
        }

    public void setG1(Game g1)
    {
        this.g1 = g1;
    }

    public void setP(Player p) {
        this.p = p;
    }

    public Player getP() {
        return p;
    }

    public Game getG1() {
        return g1;
    }

    public int getPlayNo() {
        return playNo;
    }

    public void setPlayNo(int playNo) {
        this.playNo = playNo;
    }

    public int takeInput() {

        System.out.println("Welcome to Mafia");
        System.out.print("Enter number of players: ");
        int N = in.nextInt();
            while(N<6)
        {
            System.out.print("Not enough players. Enter number of players: ");
            N = in.nextInt();
        }
            return N;
    }


    public void chooseUserCharacter()                           // Iterating through players hashmap to assign user to his chosen character type
    {
        int chooseCharacter = in.nextInt();
        Iterator it = this.getG1().getPlay().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry o = (Map.Entry) it.next();
            Player p1 = (Player) o.getValue();
            if (chooseCharacter == 1) {
                if (p1.getClass() == Mafia.class) {
                    this.setPlayNo((int) o.getKey());                               // sets the player id of user
                    this.setP((Player) o.getValue());                               // setting player object in user
                    break;
                }
            } else if (chooseCharacter == 2) {
                if (p1.getClass() == Detective.class) {
                    this.setPlayNo((int) o.getKey());
                    this.setP((Player) o.getValue());
                    break;
                }

            } else if (chooseCharacter == 3) {
                if (p1.getClass() == Healer.class) {
                    this.setPlayNo((int) o.getKey());                                // sets the player id of user
                    this.setP((Player) o.getValue());                                // setting player object in user
                    break;
                }

            } else if (chooseCharacter == 4) {
                if (p1.getClass() == Commoner.class) {
                    this.setPlayNo((int) o.getKey());                                 // sets the player id of user
                    this.setP((Player) o.getValue());                                 // setting player object in user
                    break;
                }

            } else if (chooseCharacter == 5) {
                this.setPlayNo(1);
                this.setP((Player) o.getValue());
                break;
            } else {
                System.out.println("Invalid input. You have been randomly assigned a player. ");
                this.setPlayNo(1);
                this.setP((Player) o.getValue());
                break;
            }
        }
    }


}


