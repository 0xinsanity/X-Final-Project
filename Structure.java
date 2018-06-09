
/**
 * Write a description of class structure here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Structure extends GridSpot
{
    private int option1;
    private int option2;
    private String place_name;
    
    public Structure()
    {
        super(4,"H");
        
        int[] possible_states = {1,3,6};
        for (int i = 0; i < 2; i++) {
            int random = (int)(Math.random()*3);
            if (i == 0) {
                option1 = possible_states[random];
            } else {
                option2 = possible_states[random];
            }
        }
        
        String[] possible_places = {"the Math Department", "Randy's Living Room", "a crack in the wall", "the bonehead corner"};
        int random = (int)(Math.random()*4);
        place_name = possible_places[random];
    }
    
    public int getDoorWay1() {
        return option1;
    }
    
    public int getDoorWay2() {
        return option2;
    }
    
    public String getPlaceName() {
        return place_name;
    }
}
