package pt.iscte.poo.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pt.iscte.poo.utils.Point2D;

public class Room {

	private Hero hero;
	private ArrayList<GameElement> mapList = new ArrayList<>();
	private ArrayList<GameElement> elementList = new ArrayList<>();

	public Room(String name, Hero hero) {
		this.hero = hero;
		addMapAndElements(name);
	}

	public ArrayList<GameElement> getMap() {
		return mapList;
	}

	public ArrayList<GameElement> getElements() {
		return elementList;
	}

	public Hero getHero() {
		return hero;
	}

	public void addMapAndElements(String name) {
		File file = new File(name);
		try {
			int y = 0;
			Scanner roomScanner = new Scanner(file);
			while (roomScanner.hasNextLine()) {
				String line = roomScanner.nextLine();
				if (y < GameEngine.GRID_HEIGHT)
					for (int x = 0; x != GameEngine.GRID_WIDTH; x++)
						mapList.add(GameElement.create(line.substring(x, x + 1), new Point2D(x, y)));
				if (y > GameEngine.GRID_HEIGHT) {
					Scanner lineScanner = new Scanner(line);
					lineScanner.useDelimiter(",");
					elementList.add(GameElement.create(lineScanner.next(),
							new Point2D(lineScanner.nextInt(), lineScanner.nextInt())));
					lineScanner.close();
				}
				y++;
			}
			roomScanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Ficheiro nao encontrado");
		}
	}

	public GameElement positionEvaluator(Point2D point) {
		if (hero.getPosition().equals(point))
			return hero;
		for (GameElement e : elementList)
			if (!e.isWalkable && e.getPosition().equals(point))
				return e;
		return mapList.get(pointToIndex(point));
	}
	
	public void removeElement(GameElement e){
		elementList.remove(e);
	}

	private int pointToIndex(Point2D point){
		return point.getX() + point.getY() * GameEngine.GRID_HEIGHT;
	}
}