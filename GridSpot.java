
/**
 * Write a description of class GridSpot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class GridSpot
{

    private int interactive;
    private String character;
    private boolean shown;
    
    public GridSpot(int interact, String symbol)
    {
        interactive=interact;
        character=symbol;
        boolean shown = false;
    }
    
    public int getState(){    
        return interactive;    
    }
    
    public String getCharacter(){    
        return character;
    }
    
    public String toString() {
        return character;
    }
    
    public boolean getShown(){
        return shown;
    }
    
    public void changeShown(){
        shown = true;
    }
    

}
