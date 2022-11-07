package pt.iscte.poo.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Room {

	private ArrayList<ImageTile> imageList = new ArrayList<>();
	private ArrayList<ImageTile> elementList = new ArrayList<>();

	public Room(String name) {
		addMapAndElements(name);
	}

	public void addMapAndElements(String name) {
		System.out.println("aki");
		File file = new File(name);
		try {
			int y = 0;
			Scanner roomScanner = new Scanner(file);
			while (roomScanner.hasNextLine()) {
				String line = roomScanner.nextLine();
				if (y < EngineExample.GRID_HEIGHT)
					for (int x = 0; x != EngineExample.GRID_WIDTH; x++)
						imageList.add(GameElement.create(line.substring(x, x + 1), new Point2D(x, y)));
				if (y > EngineExample.GRID_HEIGHT) {
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

	public ArrayList<ImageTile> getMap() {
		return imageList;
	}
	
	public ArrayList<ImageTile> getElements() {
		return elementList;
	}
}
