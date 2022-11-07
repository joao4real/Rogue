package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Skeleton extends GameElement implements Movable {

//	private static final int MAXIMUM_HP = 5;

	public Skeleton(Point2D point) {
		position = point;
		isWalkable = false;
	}

	@Override
	public String getName() {
		return "Skeleton";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public void move(Room room) {

	}

	@Override
	public int getLayer() {
		return 0;
	}

}
