import java.util.List;
import java.util.ArrayList;
/**UPDATED
 */
public class PrintUtility
{
    public static void printStartScreen(int i) {
        clearBoard();

        
        
        System.out.println("__        __ ");
        System.out.println("\\ \\      / / ");
        System.out.println(" \\ \\    / / ");
        System.out.println("  \\ \\  / / ");
        System.out.println("   \\ \\/ / ");
        System.out.println("   / /\\ \\ ");
        System.out.println("  / /  \\ \\ ");
        System.out.println(" / /    \\ \\ ");
        System.out.println("/ /      \\ \\ ");
        System.out.println("‾‾        ‾‾ ");
        System.out.println("\neXplore.eXpand.eXterminate.\n");

        System.out.println("Note: Remember to make your console full screen and keep the GUI window selected for optimal experience!\n");

        if (i == 0)
            System.out.print("-> ");

        System.out.println("Start Your Quest");
        
        if (i == 1)
            System.out.print("-> ");

        System.out.println("End Your Experience Now");
    }

    public static void printInventory(int current_pos, Player p) {
        clearBoard();

        System.out.println("HP: " + p.getHP());
        System.out.println("Water: " + p.getWater());
        System.out.println("Food: " + p.getFood());

        System.out.println();

        Inventory i = new Inventory();
        List<Weapon> weaponList = i.getWeaponList();

        for (int j = 0; j < weaponList.size(); j++) {
            if (j == current_pos) {
                System.out.print("-> ");
            }
            if (j == 0) {
                System.out.print(" Current: ");
            }
            System.out.println(weaponList.get(j).getName() + ", Durability: " + weaponList.get(j).getDurability() + ", Attack Base Damage: " + weaponList.get(j).getDamage());
        }
    }

    public static void printGrid(Grid g, int row, int col) {
        clearBoard();

        for (int r = g.getPlayerLocation()[0] - 2; r < g.getPlayerLocation()[0] + 3; r++)
        {
            for (int c = g.getPlayerLocation()[1] - 2; c < g.getPlayerLocation()[1] + 3; c++)
            {
                if (g.getSpotAtLoc(r, c) != null) {
                    g.getSpotAtLoc(r, c).changeShown();
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (g.getSpotAtLoc(i,j).getShown() == false) {
                    System.out.print("  ");
                } else {
                    System.out.print(g.getSpotAtLoc(i,j).getCharacter()+" ");
                }

            }
            System.out.println();
        }
        System.out.println("HP: " + g.returnPlayer().getHP());
        System.out.println("Water: " + g.returnPlayer().getWater());
        System.out.println("Food: " + g.returnPlayer().getFood());
    }

    public static void printFightScreen(int i, int food, Enemy enemy, Player player) {
        clearBoard();

        System.out.println("Your hp: " + player.getHP());
        if (player.getWeapon() != null){
            System.out.println("Current weapon: " + player.getWeapon().getName());
            System.out.println("Current weapon durability: " + player.getWeapon().getDurability()+ "\n");
        }
        //System.out.println("Last Attack: You ");
        
        if (i == 0)
            System.out.print("->");

        System.out.println("Attack" + "                " + enemy.getName());

        if (i == 1)
            System.out.print("->");

        System.out.println("Eat: " + food + "                " + enemy.getHP());

        if (i == 2)
            System.out.print("->");

        System.out.println("Run Away");
    }
    
    public static void printStructure(int current_pos, Structure input) {
        clearBoard();
        
        
        System.out.print("You have stumbled upon " + input.getPlaceName() + ". ");
        
        System.out.println("What will you choose? Note: You could find an enemy, treasure, or be transported back to the grid.");
        
        if (current_pos == 0)
            System.out.print("-> ");
            
        System.out.println("Path 1");
        
        if (current_pos == 1)
            System.out.print("-> ");
            
        System.out.println("Path 2");
    
    }
    
    public static void printTreasure(Player p) {
        int amount_of_food = (int) (Math.random()*4)+1;
        
        System.out.println("You found some food! " + amount_of_food + " to be exact.");
        p.addFood(amount_of_food);
        int weapon = (int) (Math.random()*10);
        
        if (weapon > 6) {
            // IMPORTANT: weapon coincides with number of lines in file
            weapon = (int) (Math.random()*15);
            Weapon found_weapon = Grid.getWeaponFromLine(weapon);
            Inventory i = new Inventory();
            i.addWeapon(found_weapon);
            
            System.out.println("You Also Found A Weapon! " + found_weapon.getName() + " to be exact.");
        } else {
            System.out.println("You found no weapons, sorry.");
        }
        
        System.out.println("Press enter to return to grid.");
    }
    
    
    public static void printDeath() {
        clearBoard();
        System.out.println("You died.");
        System.out.println("Press Enter to return to Start Screen.");
    }

    private static void clearBoard() {
        System.out.println('\u000c');
    }
}
