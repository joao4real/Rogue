package pt.iscte.poo.example;

import java.util.Scanner;

import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement {
	
	public static boolean DOOR_TO_OPEN = false;

	private boolean isOpen = false;
	private String roomName;
	private String keyCode;
	private Point2D heroPoint;

	public Door(Point2D point, Scanner info) {
		super(point, "DoorClosed");
		roomName = info.next();
		heroPoint = new Point2D(info.nextInt(), info.nextInt());
		if (info.hasNext()) {
			keyCode = info.next();
		} else
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
	
	public boolean isOpen() {
		return isOpen;
	}

	public void open() {
		isWalkable = true;
		isOpen = true;
	}

	public void checkDoor(){
		if (Hero.getInstance().hasItem(keyCode) && DOOR_TO_OPEN){
			isOpen = true;
			DOOR_TO_OPEN = false;
		}
	}

}