package pt.iscte.poo.example;

import java.util.Scanner;

import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement {

	private boolean isOpen = false;
	private String roomName;
	private String keyCode;
	private Point2D heroPoint;

	public Door(Point2D point, Scanner info) {
		super(point, "DoorClosed");
		roomName = info.next();
		heroPoint = new Point2D(info.nextInt(), info.nextInt());
		if (info.hasNext())
			keyCode = info.next();
		else
			isOpen = true;
	}

	@Override
	public String getName() {
		return isOpen() ? "DoorOpen" : "DoorClosed";
	}

	public String getRoomName() {
		return roomName;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public Point2D getHeroPoint() {
		return heroPoint;
	}

	public void open() {
		isWalkable = true;
		isOpen = true;
	}

	public boolean isOpen() {
		return isOpen;
	}
}