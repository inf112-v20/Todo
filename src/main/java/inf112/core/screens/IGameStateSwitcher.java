package inf112.core.screens;

public interface IGameStateSwitcher {

    void initMainMenu();
    void initMainGame();
    void initGameOver();
    void initMultiplayerSettings();
    void initMultiplayerJoin();
    void initMultiplayerHost();
    void initMultiplayerPlayername();
    void initSelectScreen();
    void initMultiplayerStandby();
    void closeApplication();
}
