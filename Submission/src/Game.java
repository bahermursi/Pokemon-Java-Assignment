
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author bahermursi
 */
public final class Game extends Applet implements KeyListener, ActionListener
  {

   
    private Hero hero, pikatchu;
    private Enemy[] enemy;
    private Items items;
    private Graphics dbg;
    private Graphics2D screen2D;
    private Toolkit toolkit;
    private Image dbImage, background;
    private final int mapWidth = 25, mapHeight = 20;
    private Image[][] map;
    private String[] grid;
    private ArrayList enemyPos;
    private Map mp;
    private int enemyCount;
    private int lives;
    private final int width = 800, height = 800;
    private final int yFactor = 40;
    private Timer timer = null;
    private final int frameDelay = 1000 / 100, timeToLoad = 5; // frames per second = 30
    private Pair itemPair, enPos;
    private boolean removeItem, isOnItem, movePika = false;
    private int xPos, yPos;
    private long timeInMilli, timeLeft;
    private String itemsLeft, itemsLoaded, heroLives;
    private boolean was_L_Pressed, was_U_Pressed, onTarget;
    private AudioClip soundFile1;
    private boolean drawmenu = true, drawlevels = false, playGame = false, enterPressed = false;
    private Menu menu;

    
    /**
     * Function used to initialize all the Game class variables
     */
    @Override
    public void init()
      {
        toolkit = Toolkit.getDefaultToolkit();
        menu = new Menu();
        enemyCount = 3;
        enemy = new Enemy[enemyCount];
        hero = new Hero();
        pikatchu = new Hero();
        mp = new Map();
        grid = new String[mapHeight];
        mp.setEnemyCount(enemyCount);

        Map.readFile("MapFile", grid);
        map = new Image[mapWidth][mapHeight];
        for (int i = 0; i < mapWidth; i++)
            for (int j = 0; j < mapHeight; j++)
                map[i][j] = mp.mapDraw(i, j);
        for (int i = 0; i < enemy.length; i++)
            enemy[i] = new Enemy(grid);
        itemPair = mp.getItemPosition();
        enemyPos = mp.getEnemyPosition();
        items = new Items(itemPair);
        lives = 3;
        removeItem = false;
        timer = new Timer(frameDelay, this);
        timer.start();
        setSize(width, height);
        hero.setPosition(32, 32 + yFactor);
        pikatchu.getMap(grid);
        pikatchu.setPosition(64, 32 + yFactor);
        pikatchu.enablePika();
        setEnemyPosition(enemyCount);
        itemsLeft = Integer.toString(timeToLoad);
        itemsLoaded = "0";
        soundFile1 = getAudioClip(getCodeBase(), "/Users/bahermursi/NetBeansProjects/Applets/Pokemon_Theme.wav");

        soundFile1.play();
        soundFile1.loop();
        addKeyListener(this);
        requestFocus();

      }

    /**
     * Function that checks the event of the pressed key to check the movement
     * of the hero and the selected menu item
     *
     * @param ke of type keyEvent
     */
    @Override
    public void keyPressed(KeyEvent ke)
      {

        xPos = hero.getXPosition() / 32;
        yPos = (hero.getYPosition() - yFactor) / 32;

        if (drawmenu)
          {
            if (ke.getKeyCode() == KeyEvent.VK_DOWN)
              {
                if (menu.getSelected() == 0)
                    menu.setCounter(1);
                else
                    menu.setCounter(0);
              }
            if (ke.getKeyCode() == KeyEvent.VK_UP)
              {
                if (menu.getSelected() == 1)
                    menu.setCounter(0);
                else
                    menu.setCounter(1);
              }
            if (ke.getKeyCode() == KeyEvent.VK_ENTER)
              {
                if (menu.getSelected() == 0)
                  {
                    enterPressed = false;
                    drawlevels = true;
                    playGame = false;
                    drawmenu = false;
                  } else
                    System.exit(1);
              }
          }
        if (drawmenu == false && playGame == false && drawlevels)
          {

            if (ke.getKeyCode() == KeyEvent.VK_DOWN)
              {
                enterPressed = true;
                menu.moveDown();
              }
            if (ke.getKeyCode() == KeyEvent.VK_UP)
                menu.moveUp();

            if (ke.getKeyCode() == KeyEvent.VK_ENTER && enterPressed == true)
              {
                if (menu.getSelected() == 0)
                  {
                    System.out.print(menu.getSelected());
                    enemyCount = 1;
                  } else if (menu.getSelected() == 1)
                    enemyCount = 2;
                else
                    enemyCount = 3;

                drawmenu = false;
                playGame = true;
                drawlevels = false;
              }
            if (ke.getKeyCode() == KeyEvent.VK_ENTER)
                enterPressed = true;
          }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT)
          {
            movePika = true;
            if (xPos > 0 && hero.checkValid(grid, xPos - 1, yPos))
              {
                hero.moveLeft();
                pikatchu.followHero(hero.getXPosition() / 32, (hero.getYPosition() - yFactor) / 32);
              }
          }
        if (ke.getKeyCode() == KeyEvent.VK_UP)
          {
            if (yPos > 0 && hero.checkValid(grid, xPos, yPos - 1))
                hero.moveUp();
            pikatchu.followHero(hero.getXPosition() / 32, (hero.getYPosition() - yFactor) / 32);
          }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
          {
            if (xPos < mapWidth - 1 && hero.checkValid(grid, xPos + 1, yPos))
                hero.moveRight();
            pikatchu.followHero(hero.getXPosition() / 32, (hero.getYPosition() - yFactor) / 32);
          }
        if (ke.getKeyCode() == KeyEvent.VK_DOWN)
            if (yPos < mapHeight - 1 && hero.checkValid(grid, xPos, yPos + 1))
              {
                hero.moveDown();
                pikatchu.followHero(hero.getXPosition() / 32, (hero.getYPosition() - yFactor) / 32);
              }
        if (ke.getKeyCode() == KeyEvent.VK_L)
            if (items.isItem(xPos, yPos) == true)
              {
                timeInMilli = System.currentTimeMillis();
                was_L_Pressed = true;
                onTarget = false;
              }

        if (ke.getKeyCode() == KeyEvent.VK_U)
          {
            timeInMilli = System.currentTimeMillis();
            if (mp.isOnTarget(hero.getXPosition() / 32, (hero.getYPosition() - yFactor) / 32))
              {
                items.setPosition(hero.getXPosition() / 32, (hero.getYPosition() - yFactor) / 32);
                was_U_Pressed = true;
              }
          }
        repaint();
      }

    /**
     * Function that paints all the images on the screen
     *
     * @param g of type Graphics
     */
    @Override
    public void paint(Graphics g)
      {
        setBackground(Color.white);
        background = toolkit.getImage("pokemonBack.png");
        screen2D = (Graphics2D) g;
        Font font = new Font("Arial Black", Font.BOLD, 20);
        screen2D.setFont(font);

        if (drawmenu == true)
          {
            g.drawImage(background, 0, 100, this);
            menu.drawMainMenu(screen2D);
          }

        if (drawmenu == false && playGame == false && drawlevels == true)
          {
            g.drawImage(background, 0, 100, this);
            menu.drawDiffMenu(screen2D);
          }

        if (drawmenu == false && playGame == true && drawlevels == false)
          {
            g.clearRect(0, 0, width, height);
            for (int i = 0; i < mapWidth; i++)
                for (int j = 0; j < mapHeight; j++)
                    mp.drawGround(g, i * 32, ((j * 32) + yFactor));
            for (int i = 0; i < mapWidth; i++)
                for (int j = 0; j < mapHeight; j++)
                    g.drawImage(map[i][j], i * 32, ((j * 32) + yFactor), null);

             mp.drawTarget(g, yFactor); 
            if (!removeItem)
                items.draw(g);

            mp.drawDoor(g, yFactor, onTarget);
          
            hero.draw(g);
            pikatchu.draw(g);
            for (int i = 0; i < enemyCount; i++)
                enemy[i].draw(g);
            screen2D.setColor(Color.red);
            screen2D.drawString("Lives: x" + heroLives, 50, 20);
            screen2D.setColor(Color.blue);
            screen2D.drawString("Time to Load: " + itemsLeft + "s", 150, 20);
            screen2D.drawString("Time to Unload:" + itemsLoaded + "s", 400, 20);

            if (isLost())
              {

                screen2D.setColor(Color.white);
                g.clearRect(0, 0, width, height);
                g.drawImage(toolkit.getImage("youLose.jpg"), 0, 0, this);
                font = new Font("Arial Black", Font.BOLD, 50);
                screen2D.setFont(font);
                screen2D.drawString("GAME OVER", 250, 350);
              }

            if (onTarget && hero.getXPosition() == 32 && hero.getYPosition() - yFactor == 32)
              {

                screen2D.setColor(Color.red);
                g.clearRect(0, 0, width, height);
                g.drawImage(toolkit.getImage("pokemonBack2.png"), 0, 0, this);
                font = new Font("Arial Black", Font.BOLD, 50);
                screen2D.setFont(font);
                screen2D.drawString("YOU WiN ", 250, 400);
              }
          }
      }

    /**
     * Function that prevents the screen from flickering
     *
     * @param g A parameter of type Graphics
     */
    @Override
    public void update(Graphics g)
      {
        dbImage = createImage(width, height);
        dbg = dbImage.getGraphics();
        paint(dbg);
        g.drawImage(dbImage, 0, 0, this);
      }

    /**
     * Function that keeps updating the enemies movement on the map
     */
    public void EnemeyUpdate()
      {
        if (playGame)
            for (int i = 0; i < enemyCount; i++)
              {
                enemy[i].moveRandom();
                enemy[i].update();
              }
      }

    /**
     * Function that sets the enemies position on the map
     */
    void setEnemyPosition(int x)
      {
        if (x > 0)
            for (int i = 0; i < x; i++)
              {
                enPos = (Pair) enemyPos.get(i);
                enemy[i].setPosition(enPos.getX() * 32, enPos.getY() * 32 + yFactor);
              }
      }

    /**
     * function that checks if the hero encountered an enemy or not
     */
    void isEnemy()
      {
        int x = hero.getXPosition();
        int y = hero.getYPosition();
        for (int i = 0; i < enemyCount; i++)
            if (lives > 0 && enemy[i].checkEnemy(x, y))
                lives -= 1;

        heroLives = Integer.toString(lives);
      }

    /**
     * Function that updates Pikatchu's movement
     */
    void pikatchuUpdate()
      {
        pikatchu.update();
      }

    @Override
    public void keyTyped(KeyEvent ke)
      {
      }

    @Override
    public void keyReleased(KeyEvent ke)
      {
      }

    /**
     * Function that checks if the item on the map was loaded by the hero
     */
    public void isLoaded()
      {
        timeLeft = System.currentTimeMillis() / 1000 - timeInMilli / 1000;
        isOnItem = items.checkItem(hero.getXPosition() / 32, (hero.getYPosition() - yFactor) / 32);
        if (was_L_Pressed && timeLeft == timeToLoad && isOnItem)
            removeItem = true;
        else if (was_L_Pressed && timeLeft < timeToLoad && isOnItem)
          {
            itemsLoaded = Long.toString(timeLeft + 1);
            itemsLeft = Integer.toString(timeToLoad - Integer.parseInt(itemsLoaded));
          }
        if (!isOnItem)
          {
            was_L_Pressed = false;
          }
      }

    /**
     * Function that checks if the item on the map was loaded or unloaded by the
     * hero
     */
    public void isUnLoaded()
      {
        timeLeft = System.currentTimeMillis() / 1000 - timeInMilli / 1000;
        isOnItem = items.checkItem(hero.getXPosition() / 32, (hero.getYPosition() - yFactor) / 32);
        if (was_U_Pressed && timeLeft == timeToLoad)
          {
            removeItem = false;
            onTarget = true;

          } else
          {
            if (was_U_Pressed && timeLeft < timeToLoad)
              {
                itemsLeft = Long.toString(timeLeft + 1);
                itemsLoaded = Integer.toString(timeToLoad - Integer.parseInt(itemsLeft));

              }
          }

      }

    /**
     * Function from the Action Listener class that is used to update the game
     * every action performed using a timer
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
      {
        hero.update();
        isEnemy();
        EnemeyUpdate();
        pikatchu.update();
        isLoaded();
        isUnLoaded();
        repaint();
      }

    /**
     * Function that checks if the hero lost or not
     *
     * @return a boolean
     */
    private boolean isLost()
      {
        return lives == 0;
      }

  }
