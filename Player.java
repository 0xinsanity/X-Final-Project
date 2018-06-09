
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends GridSpot 
{
    Weapon currentWeapon;
    private static GridSpot previous_spot;
    private int hp;
    private int food;
    private int water;
    private Inventory inventory;
    //private String name;

    public Player()
    {
        super(1,"@");
        //name=namex;
        hp=40;
        water=10;
        food=8;
        previous_spot = new Empty();
        inventory = new Inventory();
        currentWeapon = inventory.getWeaponList().get(0);

    }

    /*
    public String getName(){

    return name;

    }
     */

    public void changeHP(int hit){
        hp-=hit;

    }

    public void addFood(int number_added) {
        food += number_added;
    }
    
    public int getFood() {
        return food;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void changeCurrentWeapon(int spot) {
        Inventory i = new Inventory();
        i.getWeaponList().add(0, i.getWeaponList().remove(spot));
        currentWeapon = i.getWeaponList().get(0);
    }

    public void eatFood(){
        if (hp < 100){
            food--;
            hp += 10;
            if (hp > 100)
                hp = 100;
        }
    }
    
    public void destroyWeapon()
    {
        int index = 0;
        while (!inventory.getWeaponList().get(index).equals(currentWeapon))
        {
            index++;
        }
        inventory.getWeaponList().remove(index);
        if (inventory.getWeaponList().size() > 0)
        {
            index = (int)(Math.random() * inventory.getWeaponList().size());
            currentWeapon = inventory.getWeaponList().get(index);
        }
        else
            currentWeapon = null;
    }

    public int getHP() {
        return hp;
    }

    public int getWater() {
        return water;
    }

    public void decrementWater() {
        water--;
    }

    public void refreshWater() {
        water = 10;
    }

    public void changePreviousSpot(GridSpot new_spot) {
        previous_spot = new_spot;
    }

    public GridSpot getPreviousSpot() {
        return previous_spot;
    }

    public Weapon getWeapon()
    {
        return currentWeapon;
    }

}
