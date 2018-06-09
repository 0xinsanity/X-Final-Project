
/**UPDATED
 */
public class Enemy extends GridSpot 
{

    private String name;
    private int hp;
    private int food_dropped;
    private Weapon weapon;

    public Enemy()
    {

        super(3,"$");
        hp=100;
        food_dropped=100;
        
    }

    public Enemy(String name, int hp, int food_dropped, Weapon weap) {
        super(3,"$");
        this.name = name;
        this.hp = hp;
        this.food_dropped = food_dropped;
        weapon = weap;
    }

    public void changeHP(int hit){
        hp-=hit;

    }
    
    
    
    public String getName(){
        return name;
    }
    
    public int getHP(){
        return hp;
    }
    
    public int getFoodDropped(){
        return food_dropped;
    }
    
    public Weapon getWeapon(){
        return weapon;
    }

}
