package inf112.core.board;

public enum MapNames {
    /**
     * Enum for easily adding and changing TileMaps
     */
    TESTING_MAP("maps/testingMap.tmx"),
    CONVEYOR_TESTING_MAP("maps/conveyorTestingMap.tmx"),
    LASER_TESTING_MAP("maps/laserTestingMap.tmx"),
    LASER_TESTING_MAP2("maps/laserTestingMap2.tmx"),
    WRENCH_AND_GEARS_TESTING_MAP("maps/wrenchAndGearsTestingMap.tmx")
    ;

    private String name;

    MapNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
