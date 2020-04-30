package inf112.core.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class ButtonFactory {

    public static TextButton createCustomButton(String text, float size){

        BitmapFont font = AssMan.manager.get(AssMan.CHINTZY_FONT);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        TextButton button = new TextButton(text, style);

        button.setTransform(true);
        button.getLabel().setFontScale(size/2);

        button.setTransform(false);

        return button;
    }

    public static ImageButton createImageButton(Texture texture, float size){
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegionDrawable texRegionDrawable = new TextureRegionDrawable(textureRegion);
        ImageButton button = new ImageButton(texRegionDrawable);
        button.setSize(16*size,32*size);

        return button;
    }

}
