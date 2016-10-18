
public class Pair {
    private int x;
    private int y;
    private int index;

    /**
     * Constructor of class pair 
     * @param x of type int
     * @param y of type int
     */
    public Pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    
    }

    public Pair()
    {}

    /**
     * get the the first variable in the pair
     * @return an int
     */
    public int getX() {
        return x;
    }
    
    /**
     * Function that sets first position to a value
     * @param x of type int
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * get the the second variable in the pair
     * @return an int
     */
    public int getY() {
        return y;
    }

    /**
     * Function that sets second position to a value
     * @param y of type int
     */
    public void setY(int y) {
        this.y = y;
    }

    


}