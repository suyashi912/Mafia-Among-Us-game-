import java.util.*;

public final class Game {
    private final Scanner in = new Scanner(System.in);
    private final genericMap<Player> play;
    private final genericMap<Mafia> mafiaL;
    private final genericMap<Detective> detectiveL;
    private final genericMap<Healer> healerL;
    private final genericMap<Commoner> commonerL;
    private final User U;
    private static Random m;
    private final int numberOfplayers;

    public Game() {
        play = new genericMap<Player>();                                 // Association relationship with Players
        U = new User();                                                  // Association relationship with user
        U.setG1(this);                                                   // User knows about game and game knows about user
        mafiaL = new genericMap<Mafia>();                                // Stores all mafias in game
        detectiveL = new genericMap<Detective>();                        // Stores all detectives in game
        healerL = new genericMap<Healer>();                              // Stores all healers in game
        commonerL = new genericMap<Commoner>();                          // Stores all the commoners in game
        m = new Random();
        numberOfplayers = U.takeInput();                                 // storing number of players in game by taking input (N) from user
    }

    public static Random getM() {
        return m;
    }

    public genericMap<Player> getPlay() {
        return play;
    }

    public genericMap<Commoner> getCommonerL() {
        return commonerL;
    }

    public genericMap<Detective> getDetectiveL() {
        return detectiveL;
    }

    public genericMap<Healer> getHealerL() {
        return healerL;
    }

    public genericMap<Mafia> getMafiaL() {
        return mafiaL;
    }

    public int getNumberOfplayers() {
        return numberOfplayers;
    }

    public void printCharacterMenu() {                                       // prints the chatacter menu
        System.out.println("Choose a character");
        System.out.println("1) Mafia");
        System.out.println("2) Detective");
        System.out.println("3) Healer");
        System.out.println("4) Commoner");
        System.out.println("5) Assign Randomly");
    }

    public void assignCharacters()                                          //Assigns characters to players randomly
    {
        int players = numberOfplayers;
        int random = getM().nextInt(players) + 1;                           // random number between 1 to N (both included)
        for (int i = 0; i < players / 5; i++) {                             // assigning players to mafia chahracter
            while (play.containsKey(random)) {
                random = getM().nextInt(players) + 1;
            }
            Mafia m = new Mafia(random);                                    // random passed as parameter is the unique id for mafia
            play.put(m.getId(), m);                                         // put mafia object in play genericMap
            mafiaL.put(m.getId(), m);
            mafiaL.setCount(mafiaL.getCount() + 1);                         // count - counts the number of mafias alive
            m.setM1(mafiaL);                                                // each mafia knows about all other mafias
        }
        for (int j = 0; j < players / 5; j++) {                             // assigning players to detective character
            while (play.containsKey(random)) {
                random = Game.getM().nextInt(players) + 1;
            }
            Detective d = new Detective(random);                             // random - unique id for detective
            play.put(d.getId(), d);                                          // put detective object in play genericMap
            detectiveL.put(d.getId(), d);
            detectiveL.setCount(detectiveL.getCount() + 1);                  //count - counts the number of detectives alive
            d.setD1(detectiveL);                                             // each detective knows about all other detectives
        }
        for (int i = 0; i < players / 10; i++) {                             // assigning players to healer character
            while (play.containsKey(random)) {
                random = Game.getM().nextInt(players) + 1;
            }
            Healer h = new Healer(random);                                   // random - unique id of healer
            play.put(h.getId(), h);                                          // put healer object in play genericMap
            healerL.put(h.getId(), h);
            healerL.setCount(healerL.getCount() + 1);                        // count - counts the number of healers alive
            h.setH1(healerL);                                                // each healer knows about all the other healers
        }
        if (players / 10 == 0) {                                             // this statement is executed when players are less than 10 and we need to add 1 healer
            while (play.containsKey(random)) {
                random = Game.getM().nextInt(players) + 1;
            }
            Healer h = new Healer(random);                                   // random - unique id of healer
            play.put(h.getId(), h);                                          // put healer object in play genericMap
            healerL.put(h.getId(), h);
            healerL.setCount(healerL.getCount() + 1);                        // count - counts the number of healers alive
            h.setH1(healerL);                                                // each healer knows about all the other healers
        }
        for (int j = 1; j < players + 1; j++) {                              // assigning remaining players to commoner character
            if (!play.containsKey(j)) {
                Commoner c = new Commoner(j);                                // unique id of player passed as parameter
                play.put(c.getId(), c);                                      // put commoner object in play genericMap
                commonerL.put(c.getId(), c);
                commonerL.setCount(commonerL.getCount() + 1);                // counts the number of commoners alive
            }
        }
        U.chooseUserCharacter();                                              // In this function the player chooses a character
    }

    public void playMafia() {
        System.out.println("You are Player" + U.getPlayNo() +".");            // print the user id
        System.out.print("You are a " + U.getP().getClass().getName() +". "); // print the type of character using getClass
        U.getP().printOthers(U);                                              // print other players of the user type
        int playerLeft = play.size();
        int countRounds = 1;
        boolean detectiveGuess = false;
        int print;
        while (!checkWin()) {
            System.out.println("Round " + countRounds+": ");
            System.out.print(playerLeft + " players are remaining: ");
            Iterator it = play.entrySet().iterator();
            print = 0;
            while (it.hasNext()) {
                print++;
                Map.Entry o = (Map.Entry) it.next();
                if(print<play.size())
                    System.out.print("Player" + o.getKey() + ", ");
                else
                    System.out.print("Player" + o.getKey());
            }
            System.out.println(" are alive.");
            int mafiaTarget, detectTest, healedPlayer;

            // checking if the user is a mafia and is alive
            if (U.getP().getClass() == Mafia.class && play.containsKey(U.getPlayNo())) {
                mafiaTarget = U.getP().choose(U);
            }
            else // otherwise if the user is not mafia or is dead , simulate mafia
            {
                mafiaTarget = Mafia.simulateMafia(this);
            }

            Mafia.HpUpdate(U, mafiaTarget, this);

            // if user is detective and is alive
            if (U.getP().getClass() == Detective.class && play.containsKey(U.getPlayNo())) {
                detectTest = U.getP().choose(U);
            }

            else // otherwise if the user is not a detective or is dead, simulate detective
            {
                detectTest = Detective.simulateDetective(this);
            }

            //whether there are any alive detectives to conduct the test
            if (detectiveL.getCount() > 0)
            {
                //whether the test is right or not
                if (mafiaL.containsKey(detectTest) && getPlay().containsKey(detectTest)) {  // if tested player is a mafia and is alive
                    if(U.getP().getClass() == Detective.class)                              // print test result only when the user is a detective
                    {
                        System.out.println("Player" + detectTest + " is a mafia. ");
                    }
                    detectiveGuess = true;
                }
                else
                {
                    if(U.getP().getClass() == Detective.class)                               // print test result only when the user is a detective
                    {
                        System.out.println("Player" + detectTest + " is not a mafia. ");
                    }
                }
            }

            //if the user is a healer and is alive
            if (U.getP().getClass() == Healer.class && play.containsKey(U.getPlayNo()))
            {
                healedPlayer = U.getP().choose(U);
            }
            else
            {
                healedPlayer = Healer.simulateHealer(this);
            }

            // if healers are alive only then healing will occur
            if (getHealerL().getCount() >= 0) {
                Healer.hpHeal(this, healedPlayer);
            }
            System.out.println("--End of actions--");

            // determining if mafia's chosen target died or not
            if (getPlay().get(mafiaTarget).getPlayerHP() == 0) {
                System.out.println("Player" + mafiaTarget + " died.");
                updateCount(mafiaTarget);
                getPlay().remove(mafiaTarget);
                playerLeft--;
            }
            else
            {
                System.out.println("No one died.");
            }

            // determining if someone won or not when mafia's target was killed(or not)
            if (checkWin()) {                                                               // if someone won we break the loop
                System.out.println("--End of round" + countRounds + "--");
                break;
            }

            //if mafia was chosen by detective - then mafia is automaically voted out
            if (detectiveGuess && play.containsKey(detectTest)) {                           // if detective's guess was true and mafia is alive
                System.out.println("Player" + detectTest + " has been voted out.");
                updateCount(detectTest);
                getPlay().remove(detectTest);
                playerLeft--;
            }
            // otherwise if test was wrong, individual voting occurs and the one with majority of votes wins
            else
            {
                int select = this.votingProcess();
                System.out.println("Player" + select + " has been voted out. ");
                updateCount(select);
                getPlay().remove(select);
                playerLeft--;
            }

            System.out.println("--End of round" + countRounds + "--");
            System.out.println();
            countRounds++;
            detectiveGuess = false;
        }
        System.out.println("Game Over.");
        this.Winner();
        mafiaL.print(U);
        detectiveL.print(U);
        healerL.print(U);
        commonerL.print(U);
        System.out.println("--End of game--");

    }

    private boolean checkWin() {
        int m = 0, rest = 0;
        Iterator it = play.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry o = (Map.Entry) it.next();
            Player p = (Player) o.getValue();
            if (p.getClass() == Mafia.class)                                        // counting the number of alive mafia
            {
                m++;
            }
            else
                rest++;                                                             // counting number of alive players except mafia
        }
        if (m == 0)                                                                 // if number of mafias = 0, then game is over  - mafias lose
            return true;
        else
        {
            if(rest>0 && m / rest == 1)                                             // if ratio of mafia and others is 1:1 - mafias win
            {
                return true;
            }
            return false;
        }
    }

    private void Winner() {                                                         // this function tells us who wins once the game is over
        int m = 0;
        Iterator it = play.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry o = (Map.Entry) it.next();
            Player p = (Player) o.getValue();
            if (p.getClass() == Mafia.class) {
                m++;                                                               // count the number of alive mafias
            }
        }
        if (m == 0)
        {                                                                           //if number of alive mafias = 0 - mafia lost
            System.out.println("The Mafias have lost.");
        }
        else
        {
            System.out.println("The Mafias have won.");                             // else mafia won
        }
    }

    private void updateCount(int a1) {                                                          // this function is to update the count variable when a player dies

        if (getPlay().get(a1).getClass() == Mafia.class)                                        // checking if a1 id player is mafia
        {
            getMafiaL().setCount(getMafiaL().getCount() - 1);
        }
        else if (getPlay().get(a1).getClass() == Detective.class)                               // checking if a1 id player is detective
        {
            getDetectiveL().setCount(getDetectiveL().getCount() - 1);
        }
        else if (getPlay().get(a1).getClass() == Healer.class)                                  // checking if a1 id player is healer
        {
            getHealerL().setCount(getHealerL().getCount() - 1);
        }
        else if (getPlay().get(a1).getClass() == Commoner.class)                                // checking if a1 id player is commoner
        {
            getCommonerL().setCount(getCommonerL().getCount() - 1);
        }
    }

    private int votingProcess()                                  // the function simulates the voting process
    {                                                            // we take input from user only when he is alive
        int voteOut;
        int finalVote = 0;
        ArrayList<frequency> f = new ArrayList<frequency>();     // stores the votes of all players who receive votes >0
        boolean notTie = false;                                  // checks if tie has occured or not
        genericMap<frequency> f1 = new genericMap<frequency>();
        int uservote=0;
        boolean userVoted = false;                               //  checks if user has already voted or not
        do {
            Iterator it = play.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry o = (Map.Entry) it.next();
                Player p = (Player) o.getValue();
                if (p.equals(U.getP()))                      // if the player is user then we ask the user to input votes
                {
                    if(!userVoted)
                    {
                        System.out.print("Select a person to vote out : ");
                        voteOut = in.nextInt();
                        while (!play.containsKey(voteOut))
                        {
                            System.out.print("Invalid input. Select a person to vote out : ");
                            voteOut = in.nextInt();
                        }
                        uservote = voteOut;
                        userVoted = true;
                    }
                    else
                        voteOut = uservote;
                }
                else
                {                                        // if the player is not user then the vote of the player is chosen randomly
                    voteOut = p.vote(this);
                }

                if (f1.containsKey(voteOut))             // updating the votes of the given player in genericMap f1
                {
                    frequency f2 = f1.get(voteOut);
                    f2.setVotes(f2.getVotes() + 1);
                }
                else                                       // adding the player id and votes of the given player in genericMap f1
                {
                    frequency f2 = new frequency(voteOut);
                    f2.setVotes(1);
                    f1.put(voteOut, f2);
                }
            }

            Iterator it2 = f1.entrySet().iterator();       // iterating through the hashmap in generic map in f1
            while (it2.hasNext()) {
                Map.Entry o = (Map.Entry) it2.next();
                frequency p = (frequency) o.getValue();
                f.add(p);                                  // adding all the values in hashmap into the arraylist
            }
            Collections.sort(f);                           // sorting the arraylist in ascending order on the basis of votes - the compareTo function has been overridden in frequency

            if((f.size()>=2 && f.get(f.size()-1).getVotes() != f.get(f.size() - 2).getVotes()) || f.size()==1)
            {
                finalVote = f.get(f.size()-1).getPlayerId();
                notTie = true;
            }
            else
            {
                f1.getPlayerMap().clear();                       // otherwise if there is a tie, we clear the hashmap and arraylist and start the voting process all over again
                f.clear();
            }
        }
        while (!notTie);
        return finalVote;
    }
}
