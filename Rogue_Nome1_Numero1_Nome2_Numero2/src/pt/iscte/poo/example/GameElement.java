package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile {

	public Point2D position;
	public boolean isWalkable = false;

	public static GameElement create(String code, Point2D point) {
		switch (code) {
		case "Armor":
			return new Armor(point);
		case "Bat":
			return new Bat(point);
		case "Door":
			return new Door(point);
		case " ":
			return new Floor(point);
		case "HealingPotion":
			return new HealingPotion(point);
		case "Key":
			return new Key(point);
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
			return null;
		}
	}
}
