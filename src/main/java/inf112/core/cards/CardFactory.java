package inf112.core.cards;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Pål
 * A easily modifiable factory that creates the game decks (atm only movement).
 */
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
                return new MovementCard(getNewPriority(),1,true,"Move forward 1 tile",
                        new Texture("cards/forward.png"));
            case FORWARD2:
                return new MovementCard(getNewPriority(),2,true,"Move forward 2 tiles",
                        new Texture("cards/forward2.png"));
            case FORWARD3:
                return new MovementCard(getNewPriority(),3,true,"Move forward 3 tiles",
                        new Texture("cards/forward3.png"));
            case BACKWARDS1:
                return new MovementCard(getNewPriority(),1,false,"Move forward 1 tile",
                        new Texture("cards/backward.png"));
            case ROTATELEFT1:
                return new RotationCard(getNewPriority(), false, 1, "Rotate left once",
                        new Texture("cards/left.png"));
            case ROTATELEFT2:
                return new RotationCard(getNewPriority(), false, 2, "U-Turn",
                        new Texture("cards/uturn.png"));
            case ROTATERIGHT1:
                return new RotationCard(getNewPriority(), true, 1, "Rotate right once",
                        new Texture("cards/right.png"));
            default:
                return new MovementCard(0,0,true, "No movement",
                        new Texture("cards/forward.png"));
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
