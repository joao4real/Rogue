package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Thug extends GameElement implements Movable {

	public Thug(Point2D point) {
		position = point;
		isWalkable = false;
	}

	@Override
	public String getName() {
		return "Thug";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void move(Room room) {
	}

}