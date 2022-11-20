package pt.iscte.poo.example;

import java.util.Scanner;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile {

	private static final int DEFAULT_LAYER = 0;

	public boolean isWalkable = false;
	public String name;
	public Point2D position;

	public GameElement(Point2D point, String name) {
		position = point;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return DEFAULT_LAYER;
	}

	public static GameElement create(String code, Point2D point, Scanner info) {
		switch (code) {
		case "Armor":
			return new Armor(point);
		case "Bat":
			return new Bat(point);
		case "Door":
			return new Door(point, info);
		case " ":
			return new Floor(point);
		case "Green":
			return new Green(point);
		case "GreenRed":
			return new GreenRed(point);
		case "HealingPotion":
			return new HealingPotion(point);
		case "Key":
			return new Key(point, info);
		case "Red":
			return new Red(point);
		case "Skeleton":
			return new Skeleton(point);
		case "Sword":
			return new Sword(point);
		case "Thug":
			return new Thug(point);
		case "Treasure":
			return new Treasure(point);
		case "#":
			return new Wall(point);
		default:
			throw new IllegalArgumentException();
		}
	}

}