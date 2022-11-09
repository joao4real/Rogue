package pt.iscte.poo.example;

import java.io.File;
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
	public static final int STARTING_MAP = 0;
	public static final int MINIMUM_HP = 1;
	
	private Room currentRoom;
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
		addRooms();
		currentRoom = rooms.get(STARTING_MAP);
		currentRoom.getMap().forEach(g -> gui.addImage(g));
		addObjects();
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}

	private void addRooms() {
		for (int i = 0; i < getNumberOfRooms("rooms"); i++)
			rooms.add(new Room("rooms/room" + i + ".txt", hero));
//		rooms.add(new Room("rooms/room1.txt", hero));
	}

	private void addObjects() {
		gui.addImage(hero);
		currentRoom.getElements().forEach(g -> gui.addImage(g));
	}

	@Override
	public void update(Observed source) {

		int key = ((ImageMatrixGUI) source).keyPressed();

		if (Direction.isDirection(key)) {
			hero.setKey(key);
			hero.move(currentRoom);
			turns++;
		}
		Iterator<GameElement> it = currentRoom.getElements().iterator();
		while (it.hasNext()) {
			GameElement e = it.next();
			if (e instanceof Movable) {
				Movable m = (Movable) e;
				if (m.getHitpoints() < MINIMUM_HP) {
					gui.removeImage(e);
					it.remove();
				} else
					m.move(currentRoom);
			}
		}
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}

	public int getNumberOfRooms(String name) {
		File rooms = new File(name);
		File[] s = rooms.listFiles();
		return s.length;
	}

}
