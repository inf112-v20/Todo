package inf112.core.board;

public enum MapNames {
    /**
     * Enum for easily adding and changing TileMaps
     */
    TESTING_MAP("maps/testingMap.tmx")
    ;

    private String name;

    MapNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
