package pt.iscte.poo.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Direction; //added
import java.awt.event.KeyEvent;

public class EngineExample implements Observer {

	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;
	public static final int STARTING_MAP = 3;

	private static EngineExample INSTANCE = null;
	public ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	private List<Room> rooms = new ArrayList<>();

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
		addRooms();
		gui.addImages(rooms.get(STARTING_MAP).getMap());
		addObjects();
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}

	private void addRooms() {
		for (int i = 0; i < getNumberOfRooms("rooms"); i++)
			rooms.add(new Room("rooms/room" + i + ".txt"));
	}

	private void addObjects() {
		hero = new Hero(new Point2D(1, 1));
		gui.addImage(hero);
	}

	@Override
	public void update(Observed source) {

		int key = ((ImageMatrixGUI) source).keyPressed();

		if (Direction.isDirection(key)) {
			hero.move(key);
			turns++;
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
