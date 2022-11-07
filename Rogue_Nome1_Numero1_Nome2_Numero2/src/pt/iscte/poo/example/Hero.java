package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends GameElement implements Movable {

	private static final int MAXIMUM_HP = 10;
	private static final int DAMAGE = 1;

	public Hero(Point2D position) {
		super.position = position;
		super.hitpoints = MAXIMUM_HP;
	}

	@Override
	public String getName() {
		return "Hero";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	public void move(int key) {
		Direction direction = Direction.directionFor(key);
		Vector2D vector = direction.asVector();
		Point2D newPosition = super.position.plus(vector);
		super.position = newPosition;
	}

}
