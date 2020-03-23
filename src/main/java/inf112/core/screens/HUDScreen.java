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
//    private GameScreen gameScreen;
//    private int width;

    public HUDScreen(){
//       this.gameScreen = gamescreen;
        this.stage = new Stage();
//        this.width = 500;
        Texture texture = new Texture(Gdx.files.internal("img/playAgainButton.png"));
        createButton(texture,1,500,400);
    }

    public Stage getStage() {
        return stage;
    }

    public ImageButton createButton(Texture texture, float scale, float posX, float posY) {
        TextureRegion myTextureRegion = new TextureRegion(texture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);
        button.setPosition(posX, posY);
        stage.addActor(button);
        return button;
    }

}
