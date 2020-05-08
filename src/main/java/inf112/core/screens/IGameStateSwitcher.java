package inf112.core.screens;

public interface IGameStateSwitcher {

    void initMainMenu();
    void initMainGame();
    void initGameOver();
    void initMultiplayerJoinOrHost();
    void initMultiplayerClientPromptIP();
    void initMultiplayerClientConnectingFailed();
    void initMultiplayerPromptName();
    void initSelectMap();
    void initMultiplayerClientStandby();
    void initMultiplayerHostStandby();
    void closeApplication();
}
