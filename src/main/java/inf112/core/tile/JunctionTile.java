package inf112.core.tile;

/**
 * A junctionTile is a pairing of a primary and secondary MoverTile
 * The functionality of a junctionTile will depend on which direction a player enters the junction
 * For edgecases, for example if a player moves into a junction from the outside, the primary moverTile will be used
 * @author Alvar
 */
public interface JunctionTile extends MoverTile {
    /**
     *
     * @return primary MoverTile
     */
    MoverTile getPrimary();

    /**
     *
     * @return secondary MoverTile
     */
    MoverTile getSecondary();
}
