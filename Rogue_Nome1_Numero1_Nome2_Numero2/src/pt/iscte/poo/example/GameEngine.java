package pt.iscte.poo.example;

import java.util.ArrayList;
import java.util.Iterator;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Direction; //added

public class GameEngine implements Observer {

    public static final int GRID_HEIGHT = 10;
    public static final int GRID_WIDTH = 10;
    public static final int MINIMUM_HP = 1;
    public static final String STARTING_MAP = "room0";
    
    public Room currentRoom;
    private static GameEngine INSTANCE = null;
    public ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    private ArrayList<Room> rooms = new ArrayList<>();

    private Hero hero;
    private int turns;

    public static GameEngine getInstance() {
        if (INSTANCE == null)
            INSTANCE = new GameEngine();
        return INSTANCE;
    }

    private GameEngine() {
        gui.registerObserver(this);
        gui.setSize(GRID_WIDTH, GRID_HEIGHT);
        gui.go();
    }

    public void start() {
        hero = new Hero(new Point2D(1, 1));
        rooms.add(new Room(STARTING_MAP, hero));
        currentRoom = rooms.get(0);
        currentRoom.getMap().forEach(g -> gui.addImage(g));
        addObjects();
        gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
        gui.update();
    }

    private void addObjects() {
        gui.addImage(hero);
        currentRoom.getElements().forEach(g -> gui.addImage(g));
    }
    @Override
    public void update(Observed source) {

        int key = ((ImageMatrixGUI) source).keyPressed();

        if (Direction.isDirection(key)) {
            hero.move(key);
            turns++;
            Iterator<GameElement> it = currentRoom.getElements().iterator();
            while (it.hasNext()) {
                GameElement e = it.next();
                if (e instanceof Movable) {
                    Movable m = (Movable) e;
                    if (m.getHitpoints() < MINIMUM_HP) {
                        gui.removeImage(e);
                        it.remove();
                    } else
                        m.move(key);
                }
            }
        }
        
        gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
        gui.update();
    }

    /*public void addElement(GameElement e){
        currentRoom.getElements().add(e);
        gui.addImage(e);
    }
    
    public void removeElement(GameElement e){
        currentRoom.getElements().remove(e);
        gui.removeImage(e);
    }*/
    
    public static Hero getHero(){
        return GameEngine.getInstance().hero;
    }
    
    public static ArrayList<GameElement> getElements(){
        return GameEngine.getInstance().currentRoom.getElements();
    }
}