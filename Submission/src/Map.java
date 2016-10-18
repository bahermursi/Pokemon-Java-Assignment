import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author bahermursi
 */
public class Map
  {
    Map()
      {
        readFile("Background", background);
      }
   private Image image;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static String grid[] = new String[20];
    private static String background[] = new String[10];
    private static Pair itemPair,door,target,p;
    private static ArrayList enemyPos = new ArrayList();
    private int counter = 0,enemy=0,eneymCount;
    private Random random = new Random();
    private final int mapWidth = 25, mapHeight = 20;
    
    /**
     * function that returns the image to be drawn in the map
     * @param i the row of the character that was read from the map
     * @param j the column of the character that was read from the map
     * @return 
     */
        public Image mapDraw(int i, int j)
      {
         switch (Character.toString(grid[j].charAt(i)))
          {
            case "#":
                counter++;
                if(j!=0 && i!=0 && j!=mapHeight-1 && i!=mapWidth-1)
                return toolkit.getImage(background[random.nextInt(10)]);
                else
                return toolkit.getImage("tree.png");
            case "@":
                counter++;
                itemPair = new Pair(i, j);
                break;
            case "D":
                counter++;
                door = new Pair(i,j);
                break;
            case "T":
                counter++;
                target = new Pair(i,j);
                break;
          }
     
         if (counter % 32 == 1)
                  {
                   if(enemy <eneymCount)
                     {
                    p = new Pair(i,j);
                    enemyPos.add(p);
                     enemy++;
                     counter =0;
                     }
                    
                  }
           return (toolkit.getImage("ground.png"));
      }
        /**
         * Function that draws the ground tile of the map
         * @param g of type Graphics
         * @param x of type int that indicates the x X coordinates to be drawn in the map
         * @param y of type int that indicates the x X coordinates to be drawn in the map
         */
        public void drawGround(Graphics g,int x, int y)
          {
            image = toolkit.getImage("ground.png");
              g.drawImage(image, x, y, null);
          }
        /**
         * Function that reads the map from the file
         * @param fileName of type string that indicates the path of the file
         * @param Map string array that stored the read data
         */
    public static void readFile(String fileName, String Map[])
      {
        grid = Map;
        int i = 0;
        try
          {
            File file = new File(fileName);
            try (Scanner scanner = new Scanner(file))
              {
                while (scanner.hasNextLine())
                  {
                    Map[i] = scanner.nextLine();
                    i++;
                  }
              }
          } catch (FileNotFoundException e)
              {}
      }
    /**
     * Function that gets the item's position 
     * @return a pair containing the positions of the items
     */
    public Pair getItemPosition()
            {
              return itemPair;
            }
    /**
     * Function that sets the number of enemy to be generated in the map
     * @param x of type int indicating the number of enemies
     */
    public void setEnemyCount(int x)
            {
              eneymCount = x;
            }

            /**
             * Function that gets the target position
             * @return a pair containing the positions of the target
             */
    public Pair getTargetPosition()
            {
              return target;
            }
    /**
     * Function that returns the enemies position
     * @return an ArrayList containing the position of the enemies
     */
    public ArrayList getEnemyPosition()
            {
              return enemyPos;
            }
    /**
     * FUnction that indicates if the player is standing on the target or not
        * @param x of type int that indicates the X coordinates 
         * @param y of type int that indicates the Y coordinates
     * @return a boolean
     */
    public boolean isOnTarget(int x, int y)
            {
              return x == target.getX() && y == target.getY();
              
            }
    /**
     * Function that draws the door on the map
     * @param g of type Graphics
     * @param yFactor of type int that indicates the factor that the map is shifted by
     * @param open of type boolean that idicates to draw which state of the door
     */
    public void drawDoor(Graphics g,int yFactor,boolean open)
      {
         if(open)
          {
            image = toolkit.getImage("openDoor.png");
        g.drawImage(image, door.getX() * 32, door.getY() * 32 + yFactor,null);
          }
        else
          {
            image = toolkit.getImage("closedDoor.png");
        g.drawImage(image, door.getX() * 32, door.getY() * 32 + yFactor,null);
                  }
      }
    /**
     * Function that draws the target on the map
     * @param g of type Graphics
     * @param yFactor of type int that indicates the factor that the map is shifted by
     */
    public void drawTarget(Graphics g,int yFactor)
      {
        image = toolkit.getImage("terminate.gif");
        g.drawImage(image, target.getX() * 32, target.getY() * 32 + yFactor,null);
      }
    
    
  }
