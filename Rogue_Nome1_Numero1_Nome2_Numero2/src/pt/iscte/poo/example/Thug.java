package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thug extends GameElement implements Movable {
	
	private static final int DAMAGE = -3;
	private static final int MAXIMUM_HP = 10;
	private int THUG_HP;
	

	public Thug(Point2D point) {
		position = point;
		isWalkable = false;
		setHitpoints(MAXIMUM_HP);
		
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
		Vector2D vector = Vector2D.movementVector(super.position, room.getHero().getPosition());
		Point2D newPoint = super.position.plus(vector);
		if (room.isPositionWalkable(newPoint))
			super.position = newPoint;
		else if (newPoint.equals(room.getHero().getPosition()))
			attack(room);	
	}

	@Override
	public void attack(Room room) {
		double prob = Math.random();
		if(prob < 0.3)
		room.getHero().setHitpoints(DAMAGE);
	}

	public int getHitpoints() {
		return THUG_HP;
	}
	
	public void setHitpoints(int value) {
		THUG_HP += value;
	}
}