package inf112.core.screens;

public interface IGameStateSwitcher {

    void initMainMenu();
    void initMainGame();
    void initGameOver();
    void initMultiplayerSettings(String name);
    void initMultiplayerJoin(String name);
    void initMultiplayerHost(String name);
    void initMultiplayerPlayername();
    void closeApplication();
}
