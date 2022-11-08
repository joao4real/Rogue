package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends GameElement implements Movable {

	public Bat(Point2D point) {
		position = point;
		isWalkable = false;
	}

	@Override
	public String getName() {
		return "Bat";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public void move(Room room) {
		Vector2D vector = null;
		if (Math.random() > 0.5) {
			Direction direction = Direction.random();
			vector = direction.asVector();
		} else
			vector = Vector2D.movementVector(super.position, room.getHero().getPosition());
		Point2D newPoint = super.position.plus(vector);
		if (room.isPositionWalkable(newPoint))
			super.position = newPoint;
	}

}
