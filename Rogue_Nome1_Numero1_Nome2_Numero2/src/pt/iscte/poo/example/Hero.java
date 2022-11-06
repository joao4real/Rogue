package pt.iscte.poo.example;

import java.util.List;

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

	public void move(int key) {
//		Direction randDirection = Direction.random();
//		Vector2D randVector = randDirection.asVector(); 
//		position = position.plus(randVector);
		Direction direction = Direction.directionFor(key);
		Vector2D vector = direction.asVector();
		Point2D newPosition = super.position.plus(vector);
		super.position = newPosition;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void setHitpoints(int value) {
		super.hitpoints = Math.min(super.hitpoints + value, MAXIMUM_HP);
	}
}
