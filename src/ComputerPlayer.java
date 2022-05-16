import java.util.Random;

public class ComputerPlayer extends Player {
    public static final String COMPUTER = "Computer";

    public ComputerPlayer(String name) {
        super(COMPUTER,name);
    }

    @Override
    public Choice makeChoice() throws Exception {
        int randomIndex = new Random().nextInt(2);
        Choice choice = this.makeChoice(randomIndex);
        this.setPlayerChoice(choice);
        System.out.println(this.getName()+" chose "+choice.getName());
        return choice;
    }

}
