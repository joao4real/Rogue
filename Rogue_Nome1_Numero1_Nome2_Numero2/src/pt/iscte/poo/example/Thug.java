package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thug extends GameElement implements Movable {

	private static final int DAMAGE = -3;
	private static final int MAXIMUM_HP = 10;
	private int thugHp;

	public Thug(Point2D point) {
		position = point;
		isWalkable = false;
		thugHp = MAXIMUM_HP;

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
	public int getDamage() {
		return DAMAGE;
	}

	@Override
	public void move(Room room) {
		Vector2D vector = Vector2D.movementVector(super.position, room.getHero().getPosition());
		Point2D newPoint = super.position.plus(vector);
		GameElement e = room.positionEvaluator(newPoint);
		if(e instanceof Hero){
			attack((Movable) e);
		}
		if(e.isWalkable)
			super.position = newPoint;
	}

	@Override
	public void attack(Movable m) {
		double prob = Math.random();
		if(prob < 0.3)
		m.setHitpoints(DAMAGE);
	}

	public int getHitpoints() {
		return thugHp;
	}

	public void setHitpoints(int value) {
		thugHp += value;
	}
}