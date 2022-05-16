import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Choice {
    //public Choice beats;
    private final int index;
    protected String name;
    public Choice(int index,String name) {
        this.index = index;
        this.name = name;
    }

    /**
     *
     * @param choice1 first choice
     * @param choice2 second choice
     * @return winner between 1st choice and 2nd choice based on the rules
     * of Rock,Paper,Scissors, null if both choices are the same (draw)
     * NOT USED
     */

    public Choice winner(Choice choice1,Choice choice2) throws Exception {
        Choice result;
        switch(choice1.getIndex() - choice2.getIndex()){
            case -2:
            case 1:
                result = choice2;
                break;
            case -1:
            case 2:
                result = choice1;
                break;
            case 0:
                result = null;
                break;
            default:
                throw new Exception("Cannot declare winner, index of one of the chosen objects not in bound");
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
