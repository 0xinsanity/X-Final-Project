import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.JFrame;
/**UPDATE
 */
public class RunnerClass extends Frame implements KeyListener
{
    private static GameState gameState;
    private static int current_pos_start;
    private static Grid main_grid;
    private Inventory inventory = new Inventory();

    public RunnerClass() {
        JFrame tf = new JFrame("Game");
        tf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tf.addKeyListener(this);
        tf.setVisible(true); 
    }

    public static void main (String[] args) {
        new RunnerClass();

        gameState = new GameState();
        current_pos_start = 0;
        main_grid = new Grid(40,40);

        PrintUtility.printStartScreen(current_pos_start);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (gameState.getGameState() == 0) {
                current_pos_start = 0;
            } else if (gameState.getGameState() == 1) {
                main_grid.changePlayerLocation(main_grid.getPlayerLocation()[0]-1, main_grid.getPlayerLocation()[1]);
            }
            else if (gameState.getGameState() == 2)
            {
                if (current_pos_start != 0)
                    current_pos_start--;
            }
            else if (gameState.getGameState() == 3)
            {
                current_pos_start--;
                if (current_pos_start < 0){
                    current_pos_start = 0;
                }
            } else if (gameState.getGameState() == 4) {
                current_pos_start = 0;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (gameState.getGameState() == 0) {
                current_pos_start = 1;
            } else if (gameState.getGameState() == 1) {
                main_grid.changePlayerLocation(main_grid.getPlayerLocation()[0]+1, main_grid.getPlayerLocation()[1]);

            }
            else if (gameState.getGameState() == 2){
                if (current_pos_start < new Inventory().getWeaponList().size()-1) {
                    current_pos_start++;
                }
            }
            else if (gameState.getGameState() == 3){
                current_pos_start++;
                if (current_pos_start > 2){
                    current_pos_start = 2;
                }
            } else if (gameState.getGameState() == 4) {
                current_pos_start = 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (gameState.getGameState() == 1) {
                main_grid.changePlayerLocation(main_grid.getPlayerLocation()[0], main_grid.getPlayerLocation()[1]-1);

            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (gameState.getGameState() == 1) {
                main_grid.changePlayerLocation(main_grid.getPlayerLocation()[0], main_grid.getPlayerLocation()[1]+1);

            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (gameState.getGameState() == 0) {
                if (current_pos_start == 0) {
                    // Change Game to Game State 1
                    gameState.setGameState(1);
                } else {
                    // End Game
                    System.exit(0);
                }
            }
            else if (gameState.getGameState() == 2){
                main_grid.returnPlayer().changeCurrentWeapon(current_pos_start);
            }
            else if (gameState.getGameState() == 3){
                if (current_pos_start == 0){
                    if (main_grid.returnPlayer().getWeapon() != null){
                        if (main_grid.returnPlayer().getWeapon().getDurability() > 0)
                        {
                            ((Enemy)(main_grid.returnPlayer().getPreviousSpot())).changeHP(main_grid.returnPlayer().getWeapon().getDamage());
                            main_grid.returnPlayer().getCurrentWeapon().decrementDurability();
                        }
                        else{
                            main_grid.returnPlayer().destroyWeapon();
                            if (main_grid.returnPlayer().getWeapon() == null){
                                gameState.setGameState(5);
                            }
                        }
                    }
                    else
                    {
                        gameState.setGameState(5);
                    }
                    if (((Enemy)(main_grid.returnPlayer().getPreviousSpot())).getHP() <= 0)
                    {
                        gameState.setGameState(1);
                        Weapon droppedWeap = ((Enemy)(main_grid.returnPlayer().getPreviousSpot())).getWeapon();
                        droppedWeap.restoreDurability();
                        inventory.addWeapon(droppedWeap);
                        main_grid.returnPlayer().changePreviousSpot(new Empty());
                    }
                    else{
                        main_grid.returnPlayer().changeHP(((Enemy)(main_grid.returnPlayer().getPreviousSpot())).getWeapon().getDamage());
                    }
                    if (main_grid.returnPlayer().getHP() <=0){
                        gameState.setGameState(5);
                    }

                }
                else if (current_pos_start == 1){
                    if (main_grid.returnPlayer().getFood() > 0){
                        main_grid.returnPlayer().eatFood();  
                    }
                }
                else if (current_pos_start == 2){
                    gameState.setGameState(1);
                }    
            } else if (gameState.getGameState() == 4) {
                int state;
                if (current_pos_start == 0) {
                    state = ((Structure)main_grid.returnPlayer().getPreviousSpot()).getDoorWay1();
                } else {
                    state = ((Structure)main_grid.returnPlayer().getPreviousSpot()).getDoorWay2();
                }
                
                if (state == 3) {
                    main_grid.returnPlayer().changePreviousSpot(Grid.generateEnemy());
                } else {
                    main_grid.returnPlayer().changePreviousSpot(new Empty());
                }
                current_pos_start = 0;
                gameState.setGameState(state);
            }
            else if (gameState.getGameState() == 5) {
                inventory.reset();
                main_grid = new Grid(40,40);  
                gameState.setGameState(0);
                current_pos_start = 0;
            } else if (gameState.getGameState() == 6) {
                gameState.setGameState(1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            if (gameState.getGameState() == 1) {
                gameState.setGameState(2);
            } else if (gameState.getGameState() == 2) {
                gameState.setGameState(1);
            }
            current_pos_start = 0;
        }
        updateScreen();
    }

    private void updateScreen() {
        if (gameState.getGameState() == 0) {
            // Start Screen
            PrintUtility.printStartScreen(current_pos_start);
        } else if (gameState.getGameState() == 1) {
            // Main Grid
            PrintUtility.printGrid(main_grid,40,40);
            if (main_grid.returnPlayer().getWater() <= 0) {
                gameState.setGameState(5);
                updateScreen();
            }
        } else if (gameState.getGameState() == 2) {
            // Inventory
            PrintUtility.printInventory(current_pos_start, main_grid.returnPlayer());
        } else if (gameState.getGameState() == 3) {
            // Fight Screen
            PrintUtility.printFightScreen(current_pos_start, main_grid.returnPlayer().getFood(), 
                (Enemy)(main_grid.returnPlayer().getPreviousSpot()), main_grid.returnPlayer());
        } else if (gameState.getGameState() == 4) {
            // Structure
            PrintUtility.printStructure(current_pos_start, (Structure)(main_grid.returnPlayer().getPreviousSpot()));
        } else if (gameState.getGameState() == 5) {
            // Death
            PrintUtility.printDeath();
        } else if (gameState.getGameState() == 6) {
            // Treasure
            PrintUtility.printTreasure(main_grid.returnPlayer());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}
