package inf112.core.board;

/**
 * Enum for easily adding and changing TileMaps
 */
public enum MapNames {
    /**
     * GameMaps
     */
    GARBAGE_CHUTE("maps/gameMaps/GarbageChute.tmx"),
    WIPEOUT("maps/gameMaps/Wipeout.tmx"),
    /**
     * TestMaps
     */
    TESTING_MAP("maps/testMaps/testingMap.tmx"),
    CONVEYOR_TESTING_MAP("maps/testMaps/conveyorTestingMap.tmx"),
    LASER_TESTING_MAP("maps/testMaps/laserTestingMap.tmx"),
    LASER_TESTING_MAP2("maps/testMaps/laserTestingMap2.tmx"),
    WRENCH_AND_GEARS_TESTING_MAP("maps/testMaps/wrenchAndGearsTestingMap.tmx"),
    GEAR_TEST_MAP("maps/testMaps/gearTestMap.tmx"),
    SPAWN_TESTING("maps/testMaps/spawnTestingMap.tmx")
    ;

    private String name;

    MapNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
