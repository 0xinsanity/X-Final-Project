import java.io.*;
/**
 * Write a description of class Grid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grid
{

    private GridSpot[][] grid;
    private int[] player_loc;

    public Grid(int r, int c)
    {
        grid = new GridSpot[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int random = (int)(Math.random()*20);

                if (random <= 1) {
                    grid[i][j] = new Structure();
                } else if (random <= 18) {
                    grid[i][j] = new Empty();
                } else if (random <= 19) {
                    
                    grid[i][j] = generateEnemy();
                }
            }
        }
        grid[r/2][c/2] = new Player();
        player_loc = new int[2];
        player_loc[0] = r/2;
        player_loc[1] = c/2;
    }
    
    public static Enemy generateEnemy() {
        // IMPORTANT: random_ln coincides with number of lines in file
        int random_ln = (int)(Math.random()*15);
        int counter;
        BufferedReader reader;
        String line="";
        try {
            File file = new File("enemies.txt");
            reader = new BufferedReader(new FileReader(file));
    
            counter = 0;
            line = reader.readLine();
            while (counter != random_ln) {
                line = reader.readLine();
                counter++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String nm = line.substring(0,line.indexOf(":"));
        int hp = Integer.parseInt(line.substring(line.indexOf(":")+1,line.indexOf("+")));
        int food = Integer.parseInt(line.substring(line.indexOf("+") + 1));
               
        return new Enemy(nm,hp,food, getWeaponFromLine(random_ln));
    }
    
    public static Weapon getWeaponFromLine(int line) {
            BufferedReader reader2;
            String line2="";
            try {
                File file2 = new File("weapons.txt");
                reader2 = new BufferedReader(new FileReader(file2));
    
                int counter = 0;
                line2 = reader2.readLine();
                while (counter != line) {
                    line2 = reader2.readLine();
                    counter++;
                }
                reader2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String weapName = line2.substring(0, line2.indexOf(":"));
            int weapDurability = Integer.parseInt(line2.substring(line2.indexOf(":") + 1, line2.indexOf("+")));
            int weapDamage = Integer.parseInt(line2.substring(line2.indexOf("+") + 1));
            Weapon weapon = new Weapon(weapName, weapDurability, weapDamage);
            
            return weapon;
    }

    public Player returnPlayer() {
        return (Player)grid[getPlayerLocation()[0]][getPlayerLocation()[1]];
    }

    public GridSpot getSpotAtLoc(int x, int y){
        if (x < 0 || x >= 40 || y < 0 || y >= 40) {
            return null;
        }
        return grid[x][y];
    }

    public int[] getPlayerLocation() {
        // Returns x,y coordinate in int array
        return player_loc;
    }

    public void changePlayerLocation(int x, int y) {

        // Returns whatever was under the current player
        GridSpot old_spot = returnPlayer().getPreviousSpot();

        // Returns whatever is in the new spot
        GridSpot new_spot = getSpotAtLoc(x,y);
        if(new_spot!=null){
            // Changes previous location to the new spot
            returnPlayer().changePreviousSpot(new_spot);

            // Changes new spot to player
            grid[x][y] = grid[player_loc[0]][player_loc[1]];

            // Changes back wherever the player was previously to old spot
            grid[player_loc[0]][player_loc[1]] = old_spot;

            player_loc[0] = x;
            player_loc[1] = y;

            GameState current_state = new GameState();
            int new_state = returnPlayer().getPreviousSpot().getState();
            if (new_state == 1) {
                returnPlayer().decrementWater();
            } else {
                returnPlayer().refreshWater();
            }
            current_state.setGameState(new_state);
            
        }

    }
}
