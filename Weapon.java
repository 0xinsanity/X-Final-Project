
/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon
{
    private String name;
    private int durability;
    private int damage;
    private int initialDurability;

    public Weapon(String namex, int dur, int dam)
    {
        name=namex;
        initialDurability =dur;
        damage = dam;
        durability = initialDurability;

    }

    public String getName()
    {
        return name;
    }

    public void restoreDurability()
    {
        durability = initialDurability;
    }

    public int getDurability()
    {
        return durability;
    }

    public void decrementDurability()
    {
        durability--;
    }

    public boolean equals(Weapon compared) {
        if (compared.getName().equals(name)) {
            return true;
        } else {
            return false;
        }
    }

    public int getDamage(){
        return damage;
    }

}
