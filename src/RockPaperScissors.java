import java.io.IOException;
import java.util.*;

class RockPaperScissors {
    //not used for now
    private static final int NUMBER_PLAYERS = 2;
    private static boolean gameRunning = false;
    private static final List<Player> PLAYERS = new ArrayList<>();

    //list of choices, no usage for now
    private static final List<Choice> CHOICES = new ArrayList<>();
    private static final Map<String, String> RULES_MAP = new HashMap<>();

    public static void main(String[] args) {
        gameRunning = true;
        initializeRules();

        System.out.println("Welcome to Rock Paper Scissors!\n" + "Rules:\n" +
                "Rock beats Scissors\n Scissors beats Paper\n Paper beats Rock\n" +
                "Press enter to start!");

        while (gameRunning) {
            PLAYERS.clear();
            //choices.clear();
            try {
                Player player1 = createPlayerByKeyboardInput(1);
                Player player2 = createPlayerByKeyboardInput(2);
                PLAYERS.add(player1);
                PLAYERS.add(player2);
            } catch (Exception e) {
                System.out.println(e.getMessage()
                );
            }

            //choose who starts first
            Collections.shuffle(PLAYERS);

            for (Player player : PLAYERS) {
                Choice choice = null;
                while (choice == null) {
                    try {
                        choice = player.makeChoice();
                        player.setPlayerChoice(choice);
                        System.out.flush();
                    } catch (Exception e) {
                        System.out.println("Try and make a choice again.");
                    }
                }

                //choices.add(choice);
            }
            Player winner = declareWinner(PLAYERS.get(0), PLAYERS.get(1));
            if (winner == null) {
                System.out.println("TIE!");
            } else {
                System.out.println(winner.getName() + " won using " + winner.getPlayerChoice().getName());
            }

            System.out.println("Start new game? Y/N");
            boolean correctInput = false;
            while (!correctInput) {
                try {
                    Scanner keyboard = new Scanner(System.in);
                    String answer = keyboard.next();
                    if (answer.equals("n") || answer.equals("N")) {
                        gameRunning = false;
                        correctInput = true;
                    } else if (answer.equals("y") || answer.equals("Y")) {
                        gameRunning = true;
                        correctInput = true;
                    } else {
                        throw new IOException("Please enter valid " +
                                "input for continuing/stopping the game");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }


        }

    }

    /**
     * @param playerNumber integer that represents the playerNumber(ex. player1, player2)
     * @return Player that was created based on the playerX keyboard input
     */
    private static Player createPlayerByKeyboardInput(int playerNumber) {
        Player player = null;
        while (player == null) {
            try {
                System.out.println("Input player" + playerNumber + " type by writing 'human' for Human or 'computer' for Computer");
                Scanner keyboard = new Scanner(System.in);
                String inputPlayer1Type = keyboard.next();
                System.out.println("Input " + playerNumber + " name");
                String inputPlayerName = keyboard.next();
                player = createPlayer(inputPlayer1Type, inputPlayerName);
            } catch (Exception e) {
                System.out.println("Error try again.");
            }
        }
        return player;
    }

    /**
     * @param inputPlayerType the type chosen for the newly created Player
     * @param inputPlayerName the name chose for the newly created Player
     * @return Player created with type inputPlayerType and name inputPlayerName
     * @throws IllegalArgumentException in case there cannot be created a Player with the
     *                                  parameters passed from keyboard input
     */
    private static Player createPlayer(String inputPlayerType, String inputPlayerName) throws IllegalArgumentException {
        Player player;


        if (!inputPlayerType.equals("human")
                && !inputPlayerType.equals("Human")
                && !inputPlayerType.equals("computer")
                && !inputPlayerType.equals("Computer")) {
            throw new IllegalArgumentException("Cannot create player of type " + inputPlayerType);
        } else if (inputPlayerType.equals("human") || inputPlayerType.equals("Human")) {
            player = new HumanPlayer(inputPlayerName);
            System.out.println("Human player name set to: " + inputPlayerName);
        } else {
            player = new ComputerPlayer(inputPlayerName);
            System.out.println("Computer player name set to: " + inputPlayerName);
        }
        return player;
    }

    /**
     * @param player1 player1 in the current game
     * @param player2 player2 in the current game
     * @return player winner between player1 and player2
     */
    private static Player declareWinner(Player player1, Player player2) {
        switch (beats(player1.getPlayerChoice(), player2.getPlayerChoice())) {
            case WIN:
                return player1;
            case TIE:
                return null;
            case LOOSE:
                return player2;

        }
        return null;
    }

    /**
     * initialize hashmap of type <String,String> with logic value "beats" key
     */
    public static void initializeRules() {
        RULES_MAP.put("Paper", "Rock");
        RULES_MAP.put("Rock", "Scissors");
        RULES_MAP.put("Scissors", "Paper");
    }

    /**
     * @param choice1 first choice (ex. Rock, Paper, Scissors)
     * @param choice2 second choice (ex. Rock, Paper, Scissors)
     * @return choice winner choice between the 2 choice parameters
     */
    public static Results beats(Choice choice1, Choice choice2) {
        if (choice1.getName().equals(choice2.getName())) {
            return Results.TIE;
        }
        if (RULES_MAP.get(choice1.getName()).equals(choice2.getName())) {
            return Results.WIN;
        } else {
            return Results.LOOSE;
        }

    }


}
