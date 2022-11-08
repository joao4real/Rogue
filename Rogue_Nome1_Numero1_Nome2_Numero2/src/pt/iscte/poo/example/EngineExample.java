package pt.iscte.poo.example;

import java.io.File;
import java.util.ArrayList;

import java.util.Iterator;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Direction; //added

public class EngineExample implements Observer {

	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;
	public static final int STARTING_MAP = 0;
	private Room currentRoom;

	private static EngineExample INSTANCE = null;
	public ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	private ArrayList<Room> rooms = new ArrayList<>();

	private Hero hero;
	private int turns;

	public static EngineExample getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EngineExample();
		return INSTANCE;
	}

	private EngineExample() {
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT);
		gui.go();
	}

	public void start() {
		hero = new Hero(new Point2D(1, 1));
		addRooms();
		currentRoom = rooms.get(STARTING_MAP);
		for (GameElement g : currentRoom.getMap()) {
			gui.addImage(g);
		}
		addObjects();
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}

	private void addRooms() {
//		for (int i = 0; i < 4; i++)
//			rooms.add(new Room("rooms/room" + i + ".txt"));
		rooms.add(new Room("rooms/room0.txt", hero));
	}

	private void addObjects() {
		gui.addImage(hero);
		for (GameElement e : currentRoom.getElements())
			gui.addImage(e);
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
				if (m.getHitpoints() <= 0)
					it.remove();
				else
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
