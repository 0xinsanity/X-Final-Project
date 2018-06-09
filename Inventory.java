import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// CHANGED
public class Inventory
{
    static private List<Weapon> weaponList;
    public Inventory()
    {
        if (weaponList == null) {
            weaponList = new ArrayList<Weapon>();
            weaponList.add(new Weapon("Rusty Knife", 10, 2));
        }
    }

    //spot is an index of the weaponlist
    public List<Weapon> getWeaponList()
    {
        return weaponList;
    }
    
    public void reset()    
    {
        weaponList = null;
        new Inventory();
    }

    public void removeWeapon(int index){
        weaponList.remove(index);
    }

    public void addWeapon(Weapon weapon)
    {
        weaponList.add(weapon);
    }
}
