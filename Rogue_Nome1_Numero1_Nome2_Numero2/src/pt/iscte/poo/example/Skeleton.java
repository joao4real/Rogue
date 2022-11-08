package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Skeleton extends GameElement implements Movable {

//	private static final int MAXIMUM_HP = 5;
	private boolean moveTurn = true;

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
	public int getLayer() {
		return 0;
	}

	@Override
	public void move(Room room) {
		if (moveTurn) {
			Vector2D vector = Vector2D.movementVector(super.position, room.getHero().getPosition());
			Point2D newPoint = super.position.plus(vector);
			if (room.isPositionWalkable(newPoint))
				super.position = newPoint;
			moveTurn = false;
		} else
			moveTurn = true;
	}

}
