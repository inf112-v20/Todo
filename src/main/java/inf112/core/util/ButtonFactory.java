package inf112.core.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


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

}
