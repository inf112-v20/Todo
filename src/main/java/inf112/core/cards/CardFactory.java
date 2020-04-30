package inf112.core.cards;

import com.badlogic.gdx.graphics.Texture;
import inf112.core.tile.Rotation;
import inf112.core.util.AssMan;

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
                        AssMan.manager.get(AssMan.FORWARD1));
            case FORWARD2:
                return new MovementCard(getNewPriority(),2,true,"Move forward 2 tiles",
                        AssMan.manager.get(AssMan.FORWARD2));
            case FORWARD3:
                return new MovementCard(getNewPriority(),3,true,"Move forward 3 tiles",
                        AssMan.manager.get(AssMan.FORWARD3));
            case BACKWARDS1:
                return new MovementCard(getNewPriority(),1,false,"Move forward 1 tile",
                        AssMan.manager.get(AssMan.BACKWARD));
            case ROTATELEFT1:
                return new RotationCard(getNewPriority(), Rotation.LEFT, 1, "Rotate left once",
                        AssMan.manager.get(AssMan.LEFT));
            case ROTATELEFT2:
                return new RotationCard(getNewPriority(), Rotation.LEFT, 2, "U-Turn",
                        AssMan.manager.get(AssMan.UTURN));
            case ROTATERIGHT1:
                return new RotationCard(getNewPriority(), Rotation.RIGHT, 1, "Rotate right once",
                        AssMan.manager.get(AssMan.RIGHT));
            default:
                return new MovementCard(0,0,true, "No movement",
                        AssMan.manager.get(AssMan.FORWARD1));  // temp
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
