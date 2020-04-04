package inf112.core.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssMan {
    public static final AssetManager manager = new AssetManager();

    public static final AssetDescriptor<Texture> EXIT_BUTTON
            = new AssetDescriptor<Texture>("assets/buttons/exitButton.png", Texture.class);

    public static final AssetDescriptor<Texture> EXIT_BUTTON_CLICKED
            = new AssetDescriptor<Texture>("assets/buttons/exitButtonClicked.png", Texture.class);


}
