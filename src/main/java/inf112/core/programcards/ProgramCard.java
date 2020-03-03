package inf112.core.programcards;


public abstract class ProgramCard implements Comparable<ProgramCard>{
    private CardType type;
    private int priority;
    private String name;

    public ProgramCard(CardType type, int priority, String name){
        this.type = type;
        this.priority = priority;
        this.name = name;
    }

    public CardType getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public String getName(){
        return name;
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
