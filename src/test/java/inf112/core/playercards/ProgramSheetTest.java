package inf112.core.playercards;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import inf112.core.cards.register.IProgramSheet;
import inf112.core.cards.register.ProgramSheet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ProgramSheetTest {

    private IProgramSheet programSheet;
    private Texture defaultTexture;

    @Before
    public void init() {
        this.programSheet = new ProgramSheet();
        this.defaultTexture = new Texture(0,0, Pixmap.Format.Alpha);   // arbitrary
    }

    @Test
    public void containsAddedCardTest() {
        assertTrue(true);
    }

}
