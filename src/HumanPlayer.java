import java.io.IOException;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public static final String HUMAN = "human";
    public HumanPlayer(String name) {
        super(HUMAN,name);

    }

    /**
     *
     * @return Choice made by human player after reading from keyboard
     * @throws Exception contains IOException and ChoiceException #TODO
     */
    @Override
    public Choice makeChoice() throws Exception {
        System.out.println(this.getName()+", make a Choice by typing in:\n" +
                "Paper - '0'\n Rock - '1'\n Scissors - '2'");
        Scanner keyboard = new Scanner(System.in);
        int choiceInt = keyboard.nextInt();
        if (choiceInt > 2 || choiceInt < 0) {
            throw new IOException("Invalid input for choice, try again");
        }
        Choice choice = super.makeChoice(choiceInt);
        System.out.println(this.getName()+" chose "+choice.getName());
        return choice;
    }

}
