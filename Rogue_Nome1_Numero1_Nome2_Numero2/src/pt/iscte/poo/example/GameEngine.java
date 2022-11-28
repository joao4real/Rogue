package pt.iscte.poo.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Direction; //added

public class GameEngine implements Observer {

	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;
	public static final int LIFEBAR_HEIGHT = 1;
	public static final int MINIMUM_HP = 1;
	public static final String STARTING_MAP = "room0";
	private static final Point2D STARTING_POINT = new Point2D(1, 1);

	public String currentRoom;
	private static GameEngine INSTANCE = null;
	public ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	public ArrayList<Room> rooms = new ArrayList<>();

	private Hero hero;
	private int turns;
	private int score;
	private String name;

	public static GameEngine getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameEngine();
		return INSTANCE;
	}

	private GameEngine() {
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT + LIFEBAR_HEIGHT);
		gui.go();
	}

	public Hero getHero() {
		return hero;
	}

	public int getTurns() {
		return turns;
	}

	public int getScore() {
		return score;
	}

	public Room getRoom(String name) {
		Room r = genericSearch(rooms, t -> t.getName().equals(name));
		return r;
	}

	public Room getCurrentRoom() {
		return genericSearch(rooms, r -> r.getName().equals(currentRoom));
	}

	public void start() {
		hero = new Hero(STARTING_POINT);
		addRoom(STARTING_MAP);
		currentRoom = STARTING_MAP;
		getCurrentRoom().getMap().forEach(g -> gui.addImage(g));
		addObjects();
		name = gui.askUser("Choose your Nickname");
		gui.setStatusMessage("ROGUE | Nickname: " + name + " | Score: " + score + " | Turns: " + getTurns());
		gui.update();
	}

	public void addRoom(String name) {
		rooms.add(new Room(name, hero));
	}

	private void addObjects() {
		getCurrentRoom().getElements().forEach(g -> gui.addImage(g));
		gui.addImage(hero);
	}

	@Override
	public void update(Observed source) {

		int key = ((ImageMatrixGUI) source).keyPressed();

		hero.keyEvaluator(key);

		if (Direction.isDirection(key)) {
			Direction d = Direction.directionFor(key);
			hero.move(d);
			turns = getTurns() + 1;
			Iterator<GameElement> it = getCurrentRoom().getElements().iterator();
			while (it.hasNext()) {
				GameElement e = it.next();
				if (e instanceof Movable) {
					Movable m = (Movable) e;
					if (m.getHitpoints() < MINIMUM_HP) {
						((Movable)m).die(m);
						it.remove();
					} else
						m.move(d);
					if (getHero().getHitpoints() < MINIMUM_HP) {
						Object[] options = { "Try Again", "Exit, I´m going to Rage Quit!" };
						int n = JOptionPane.showOptionDialog(new JFrame(),
								"You achieved a total score of  " + score + " points!", "YOU DIED!",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						if (n == JOptionPane.YES_OPTION) {
							restart();
						} else
							System.exit(0);
					}
				}
			}
		}

		gui.setStatusMessage("ROGUE | Nickname: " + name + " | Score: " + score + " | Turns: " + getTurns());
		gui.update();
	}

	private void restart() {
		gui.clearImages();
		turns = 0;
		score = 0;
		Iterator<Room> it = rooms.iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
		start();
	}

	public void swapRoom(String name, Point2D point) {
		gui.clearImages();
		currentRoom = name;
		getCurrentRoom().getMap().forEach(e -> gui.addImage(e));
		addObjects();
		hero.setPosition(point);
		hero.updateHeroBar();
	}

	public void manageRoom(String name, Point2D point) {
		if (getRoom(name) == null)
			addRoom(name);
		swapRoom(name, point);

	}

	public static <T> T genericSearch(ArrayList<T> list, Predicate<T> p) {
		for (T t : list)
			if (p.test(t))
				return t;
		return null;
	}
}