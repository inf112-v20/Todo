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
        return priority - other.getPriority();
    }

}
