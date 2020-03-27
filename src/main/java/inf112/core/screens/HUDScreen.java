package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.core.movement.MovementHandler;
import inf112.core.programcards.ProgramCard;

import java.util.List;

public class HUDScreen {

    private Stage stage;
    private GameScreen gameScreen;
    private MovementHandler movementHandler;
//    private int width;

    public HUDScreen(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.stage = new Stage();
    }


    protected void setMovementHandler(MovementHandler movementHandler){
        this.movementHandler = movementHandler;
    }

    public void createButtons(){   // Temp solution -- Proof of concept
        int x = 0;
        int y = 0;
        int index = 0;
        List<ProgramCard> cards = movementHandler.getActivePlayer().getRegisters();
        for (ProgramCard card : cards){
            createButton(card.getTexture(), x,y, index);
            x += 160;
            index+=1;
        }
        Gdx.input.setInputProcessor(stage);    // Overrides the inputprocessor that is set elsewhere
    }

    public Stage getStage() {
        return stage;
    }

    public ImageButton createButton(Texture texture, float posX, float posY, int index) {
        TextureRegion myTextureRegion = new TextureRegion(texture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(posX, posY);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                movementHandler.cardMovement(movementHandler.getActivePlayer(), index);
            }
        });
        stage.addActor(button);
        return button;
    }

}
