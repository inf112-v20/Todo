package inf112.core.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class AssMan {
    public static final AssetManager manager = new AssetManager();

    //BUTTONS
    public static final AssetDescriptor<Texture> EXIT_BUTTON
            = new AssetDescriptor<Texture>("assets/buttons/exitButton.png", Texture.class);

    public static final AssetDescriptor<Texture> EXIT_BUTTON_CLICKED
            = new AssetDescriptor<Texture>("assets/buttons/exitButtonClicked.png", Texture.class);

    public static final AssetDescriptor<Texture> PLAY_AGAIN_BUTTON
            = new AssetDescriptor<Texture>("assets/buttons/playAgainButton.png", Texture.class);

    public static final AssetDescriptor<Texture> PLAY_BUTTON
            = new AssetDescriptor<Texture>("assets/buttons/playButton.png", Texture.class);

    public static final AssetDescriptor<Texture> PLAY_BUTTON_CLICKED
            = new AssetDescriptor<Texture>("assets/buttons/playButtonClicked.png", Texture.class);

    //TEXT
    public static final AssetDescriptor<Texture> GAME_OVER_TEXT
            = new AssetDescriptor<Texture>("assets/img/gameOver.png", Texture.class);


    //FONT

    public static final AssetDescriptor<BitmapFont> CHINTZY_FONT
            = new AssetDescriptor<BitmapFont>("assets/fonts/chintzy_font.fnt", BitmapFont.class);





    @SuppressWarnings("Duplicates")
    public static void load() {
        //BUTTONS
        manager.load(EXIT_BUTTON);
        manager.load(EXIT_BUTTON_CLICKED);
        manager.load(PLAY_AGAIN_BUTTON);
        manager.load(PLAY_BUTTON);
        manager.load(PLAY_BUTTON_CLICKED);

        //TEXT
        manager.load(GAME_OVER_TEXT);

        //FONT
        manager.load(CHINTZY_FONT);



    }

    public static void dispose() {
        manager.clear();
        manager.dispose();
    }
}
