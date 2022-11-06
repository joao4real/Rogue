package pt.iscte.poo.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Room {

	private List<ImageTile> imageList;
//	private List<GameElement> elementList;

	public Room(String name) {
		addMap(name);
	}

	public void addMap(String name) {
		List<ImageTile> tileList = new ArrayList<>();
		char[] walls = readMap(name);
		for (int x = 0; x != EngineExample.GRID_WIDTH; x++)
			for (int y = 0; y != EngineExample.GRID_HEIGHT; y++)
				tileList.add((walls[x * EngineExample.GRID_WIDTH + y] == '#') ? new Wall(new Point2D(x, y))
						: new Floor(new Point2D(x, y)));
		imageList = tileList;
	}

	public char[] readMap(String name) { // added
		File file = new File(name);
		try {
			Scanner roomScanner = new Scanner(file);
			char[] walls = new char[EngineExample.GRID_WIDTH * EngineExample.GRID_HEIGHT];
			String line = new String();
			for (int i = 0; i != EngineExample.GRID_HEIGHT; i++) {
				line = roomScanner.nextLine();
				for (int j = 0; j < line.length(); j++) {
					walls[j * EngineExample.GRID_WIDTH + i] = line.charAt(j);
				}
			}
			roomScanner.close();
			return walls;
		} catch (FileNotFoundException e) {
			System.err.println("Ficheiro nao encontrado");
			return null;
		}
	}

	public List<ImageTile> getMap() {
		return imageList;
	}
}
