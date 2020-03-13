package inf112.core.programcards;


public abstract class ProgramCard extends Card implements Comparable<ProgramCard>{
    private CardType type;
    private int priority;
    private String name;

    public ProgramCard(CardType type, int priority, String name){
        super(type, name);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }


    @Override
    public int compareTo(ProgramCard other){
        if (this.getPriority() > other.getPriority()){
            return 1;
        }
        else if (this.getPriority() == other.getPriority()){
            return 0;
        }
        return -1;
    }

}
