package inf112.core.programcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum CardFactory {
    FORWARD1,
    FORWARD2,
    FORWARD3,
    BACKWARDS1,
    ROTATERIGHT1,
    ROTATELEFT1,
    ROTATELEFT2;

    private static int priority = 100;

    public static ProgramCard createCard(CardFactory type){
        switch (type) {
            case FORWARD1:
                return new MovementCard(getNewPriority(),1,true,"Move forward 1 tile");
            case FORWARD2:
                return new MovementCard(getNewPriority(),2,true,"Move forward 2 tiles");
            case FORWARD3:
                return new MovementCard(getNewPriority(),3,true,"Move forward 3 tiles");
            case BACKWARDS1:
                return new MovementCard(getNewPriority(),1,false,"Move forward 1 tile");
            case ROTATELEFT1:
                return new RotationCard(getNewPriority(), false, 1, "Rotate left once");
            case ROTATELEFT2:
                return new RotationCard(getNewPriority(), false, 2, "U-Turn");
            case ROTATERIGHT1:
                return new RotationCard(getNewPriority(), false, 1, "Rotate right once");
            default:
                return new MovementCard(0,0,true, "No movement");
        }
    }

    public static List<ProgramCard> createDefaultDeck(){
        priority = 100;
        List<ProgramCard> cards = new ArrayList<>();
        for(int i = 0; i < 18; i++){
            cards.add(createCard(FORWARD1));
            cards.add(createCard(ROTATERIGHT1));
            cards.add(createCard(ROTATELEFT1));
        }

        for(int i = 0; i < 12; i ++){
            cards.add(createCard(FORWARD2));
        }

        for(int i = 0; i < 6; i++){
            cards.add(createCard(FORWARD3));
            cards.add(createCard(BACKWARDS1));
            cards.add(createCard(ROTATELEFT2));
        }

        Collections.shuffle(cards);
        return cards;
    }

    private static int getNewPriority(){
        int current = priority;
        priority += 10;
        return current;
    }
}
