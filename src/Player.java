import java.io.IOException;

public class Player {
    public String typeOfPlayer;
    public Choice playerChoice;
    public String name;
    public Player(String typeOfPlayer,String name){
        this.typeOfPlayer = typeOfPlayer;
        this.name = name;
    }

    /**
     *
     * @param choice integer mapped to "choice" (P->0; R->1; R->2)
     * @return Choice y where F(x) = y , x integer and y choice
     * @throws Exception ChoiceException #TODO
     */
    public Choice makeChoice (int choice)throws Exception{
        Choice result;
        switch (choice){
            case 0: result = new Paper();
                break;
            case 1: result = new Rock();
                break;
            case 2: result = new Scissors();
                break;
            default : throw new Exception("Invalid choice. The chosen element does not exist");

        }
        setPlayerChoice(result);
        return result;
    }

    /**
     *
     * @return Choice as the choice made by the player
     * @throws Exception ChoiceException #TODO
     */
    public Choice makeChoice() throws Exception {
       Choice choice = null;
       while(choice == null){
           choice = makeChoice();
       }

       return choice;

    }

    public void setPlayerChoice(Choice playerChoice) {
        this.playerChoice = playerChoice;
    }

    public String getTypeOfPlayer() {
        return typeOfPlayer;
    }

    public Choice getPlayerChoice() {
        return playerChoice;
    }

    public String getName() {
        return name;
    }
}
