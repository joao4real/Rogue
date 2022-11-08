package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends GameElement implements Movable {

	private static final int DAMAGE = -1;
	private static final int HEAL = 1;
	private static final int MAXIMUM_HP = 3;
	private int BAT_HP;
	
	public Bat(Point2D point) {
		position = point;
		isWalkable = false;
		setHitpoints(MAXIMUM_HP);
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
		else if (newPoint.equals(room.getHero().getPosition()))
			attack(room);
	}

	@Override
	public void attack(Room room) {
		double prob = Math.random();
		if(prob < 0.5) {
			room.getHero().setHitpoints(DAMAGE);
			heal();
		}
	}

	public int getHitpoints() {
		return BAT_HP;
	}

	public void setHitpoints(int value) {
		BAT_HP += value;
	}

	public void heal() {
		if(BAT_HP < 3)
			setHitpoints(HEAL);
	}
}
