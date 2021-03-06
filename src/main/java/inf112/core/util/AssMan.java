package inf112.core.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


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

    //CARDS
    public static final AssetDescriptor<Texture> FORWARD1
            = new AssetDescriptor<Texture>("assets/cards/forward.png", Texture.class);

    public static final AssetDescriptor<Texture> FORWARD2
            = new AssetDescriptor<Texture>("assets/cards/forward2.png", Texture.class);

    public static final AssetDescriptor<Texture> FORWARD3
            = new AssetDescriptor<Texture>("assets/cards/forward3.png", Texture.class);

    public static final AssetDescriptor<Texture> BACKWARD
            = new AssetDescriptor<Texture>("assets/cards/backward.png", Texture.class);

    public static final AssetDescriptor<Texture> LEFT
            = new AssetDescriptor<Texture>("assets/cards/left.png", Texture.class);

    public static final AssetDescriptor<Texture> RIGHT
            = new AssetDescriptor<Texture>("assets/cards/right.png", Texture.class);

    public static final AssetDescriptor<Texture> UTURN
            = new AssetDescriptor<Texture>("assets/cards/uturn.png", Texture.class);

    // UI
    public static final AssetDescriptor<Texture> OVERLAY
            = new AssetDescriptor<Texture>("assets/ui/overlay.png", Texture.class);

    public static final AssetDescriptor<Texture> UIBACKGROUND
            = new AssetDescriptor<Texture>("assets/ui/uibackground.png", Texture.class);

    public static final AssetDescriptor<Texture> LIFETOKEN
            = new AssetDescriptor<Texture>("assets/ui/lifetoken.png", Texture.class);

    public static final AssetDescriptor<Texture> DAMAGETOKEN
            = new AssetDescriptor<Texture>("assets/ui/damagetoken.png", Texture.class);

    public static final AssetDescriptor<Texture> CHUTEMAP
            = new AssetDescriptor<Texture>("assets/maps/imgMaps/GarbageChute.png", Texture.class);

    public static final AssetDescriptor<Texture> TESTMAP
            = new AssetDescriptor<Texture>("assets/maps/imgMaps/testMap.png", Texture.class);

    public static final AssetDescriptor<Texture> WIPEOUTMAP
            = new AssetDescriptor<Texture>("assets/maps/imgMaps/Wipeout.png", Texture.class);






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

        //CARDS
        manager.load(FORWARD1);
        manager.load(FORWARD2);
        manager.load(FORWARD3);
        manager.load(BACKWARD);
        manager.load(LEFT);
        manager.load(RIGHT);
        manager.load(UTURN);

        // UI
        manager.load(OVERLAY);
        manager.load(UIBACKGROUND);
        manager.load(LIFETOKEN);
        manager.load(DAMAGETOKEN);

        // MAPS
        manager.load(CHUTEMAP);
        manager.load(TESTMAP);
        manager.load(WIPEOUTMAP);

    }

    public static void dispose() {
        manager.clear();
        manager.dispose();
    }
}
