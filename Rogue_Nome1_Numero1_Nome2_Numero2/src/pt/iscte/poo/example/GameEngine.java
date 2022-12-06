package pt.iscte.poo.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
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
	public static final Point2D STARTING_POINT = new Point2D(1, 1);

	public String currentRoom;
	private static GameEngine INSTANCE = null;
	public ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	public ArrayList<Room> rooms = new ArrayList<>();
	public ArrayList<Player> players = new ArrayList<>();

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

	public Room getRoom(String name) {
		Room r = genericSearch(rooms, t -> t.getName().equals(name));
		return r;
	}

	public Room getCurrentRoom() {
		return genericSearch(rooms, r -> r.getName().equals(currentRoom));
	}

	public Hero getHero() {
		return hero;
	}

	public void start() {
		hero = Hero.getInstance();
		getHighScorePlayers();
		addRoom(STARTING_MAP);
		currentRoom = STARTING_MAP;
		getCurrentRoom().getMap().forEach(g -> gui.addImage(g));
		addObjects();
		gui.update();
		name = gui.askUser("Choose your Nickname");
		if (name == null)
			System.exit(0);
		if (name.equals(""))
			name = "Unknown";
		name = name.trim();
		gui.setStatusMessage("ROGUE | Nickname: " + name + " | Score: " + score + " | Turns: " + turns);
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
			turns++;
			score = Math.max(0, --score);
			Iterator<GameElement> it = getCurrentRoom().getElements().iterator();
			while (it.hasNext()) {
				GameElement e = it.next();
				if (e instanceof Movable) {
					((Movable) e).move(d);
				}
			}
		}
		gui.setStatusMessage("ROGUE | Nickname: " + name + " | Score: " + score + " | Turns: " + turns);
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
		hero = Hero.resetInstance();
		start();
	}

	public void swapRoom(String name, Point2D point) {
		gui.clearImages();
		currentRoom = name;
		getCurrentRoom().getMap().forEach(e -> gui.addImage(e));
		addObjects();
		getCurrentRoom().checkDoors();
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

	public void win() {
		printInFile();
		Object[] options = { "Try Again", "Exit", "Show HighscoreBoard" };
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"Congratulations! You achieved a total score of  " + score + " points!", "YOU WON!",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

		switch (n) {
		case JOptionPane.YES_OPTION:
			restart();
			break;
		case JOptionPane.CANCEL_OPTION:
			showHighScoreBoard();
			System.exit(0);
			break;
		default:
			System.exit(0);
		}
	}

	public void lose() {
		hero.getHealthBar().clear();
		Object[] options = { "Try Again", "Exit, I´m going to Rage Quit!" };
		int n = JOptionPane.showOptionDialog(new JFrame(), "You achieved a total score of  " + score + " points!",
				"YOU DIED!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (n == JOptionPane.YES_OPTION)
			restart();
		else
			System.exit(0);
	}

	public void getHighScorePlayers() {
		try {
			Scanner playerScanner = new Scanner(new File("highscore.txt"));
			while (playerScanner.hasNextLine()) {
				players.add(Player.playerFromLine(playerScanner.nextLine()));
			}
			playerScanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found");
		}
	}

	public void printInFile() {
		try {
			players.add(new Player(name, score));
			PointsComparator comp = new PointsComparator();
			players.sort(comp);
			PrintWriter pw = new PrintWriter(new File("highscore.txt"));
			for (int i = 0; i < 5 && i < players.size(); i++)
				pw.println(players.get(i));
			if (players.size() > 5)
				players.remove(players.size() - 1);
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found");
		}
	}

	private class PointsComparator implements Comparator<Player> {

		@Override
		public int compare(Player p1, Player p2) {
			if (p1.getPoints() == p2.getPoints())
				return String.CASE_INSENSITIVE_ORDER.compare(p1.getName(), p2.getName());
			return p2.getPoints() - p1.getPoints();
		}
	}

	public void showHighScoreBoard() {
		String highscore = "TOP 5 PLAYERS\n\n";
		
		for (Player p : players)
			highscore += p+"\n";
		gui.setMessage(highscore);
	}

	public void addScore(int i) {
		score += i;
	} 
}