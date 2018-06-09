
/**
 * Write a description of class GameState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameState
{
    private static int gameState;
    public GameState() {
        gameState = 0;
    }
    
    public int getGameState() {
        return gameState;
    }
    
    public void setGameState(int new_state) {
        gameState = new_state;
    }
}
