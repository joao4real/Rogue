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

	public String currentRoom;
	private static GameEngine INSTANCE = null;
	public ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	public ArrayList<Room> rooms = new ArrayList<>();

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
		addRoom(STARTING_MAP);
		currentRoom = STARTING_MAP;
		getRoom().getMap().forEach(g -> gui.addImage(g));
		addObjects();
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}

	public void addRoom(String name) {
		rooms.add(new Room(name, hero));
	}

	private void addObjects() {
		gui.addImage(hero);
		getRoom().getElements().forEach(g -> gui.addImage(g));
	}

	@Override
	public void update(Observed source) {

		int key = ((ImageMatrixGUI) source).keyPressed();

		if (Direction.isDirection(key)) {
			hero.move(key);
			turns++;
			Iterator<GameElement> it = getRoom().getElements().iterator();
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

	public Hero getHero() {
		return hero;
	}

	public static ArrayList<GameElement> getElements() {
		return GameEngine.getInstance().getRoom().getElements();
	}

	public void swapRoom(String name, Point2D point) {
		currentRoom = name;
		gui.clearImages();
		
		gui.addImage(hero);

		getRoom().getMap().forEach(e -> System.out.println(e.getName()));
		getRoom().getMap().forEach(e -> gui.addImage(e));
		System.out.println("aki");

		getRoom().getElements().forEach(e -> gui.addImage(e));

		hero.setPosition(point);
	}
	
	public Room getRoom(){
		for(Room r : rooms)
			if(r.getName().equals(currentRoom)) return r;
		throw new IllegalArgumentException();
	}
}