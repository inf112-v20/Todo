package inf112.core.programcards;

public abstract class ProgramCard {
    private String name;
    private int priority;

    public ProgramCard(String name, int priority){
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
}
