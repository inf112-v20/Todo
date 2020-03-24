package inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class HUDScreen {

    private Stage stage;
    private GameScreen gameScreen;
//    private int width;

    public HUDScreen(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.stage = new Stage();
//        this.width = 500;
        Texture forward = new Texture(Gdx.files.internal("img/forward.png"));
        createButton(forward,150,0);

        Texture forward2 = new Texture(Gdx.files.internal("img/forward2.png"));
        createButton(forward2,310,0);

        Texture forward3 = new Texture(Gdx.files.internal("img/forward3.png"));
        createButton(forward3,470,0);

        Texture turnRight = new Texture(Gdx.files.internal("img/right.png"));
        createButton(turnRight,630,0);

        Texture turnLeft = new Texture(Gdx.files.internal("img/left.png"));
        createButton(turnLeft,790,0);

    }

    public Stage getStage() {
        return stage;
    }

    public ImageButton createButton(Texture texture, float posX, float posY) {
        TextureRegion myTextureRegion = new TextureRegion(texture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(posX, posY);
        stage.addActor(button);
        return button;
    }

}
