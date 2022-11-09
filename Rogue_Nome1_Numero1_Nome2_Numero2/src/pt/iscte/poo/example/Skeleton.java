package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Skeleton extends GameElement implements Movable {

	private static final int DAMAGE = -1;
	private static final int MAXIMUM_HP = 5;
	private int skeletonHp;
	private boolean moveTurn = true;

	public Skeleton(Point2D point) {
		position = point;
		skeletonHp = MAXIMUM_HP;
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
	public int getDamage() {
		return DAMAGE;
	}

	@Override
	public void move(Room room) {
		if (moveTurn) {
			Vector2D vector = Vector2D.movementVector(super.position, room.getHero().getPosition());
			Point2D newPoint = super.position.plus(vector);
			GameElement e = room.positionEvaluator(newPoint);
			if(e instanceof Hero){
				attack((Movable) e);
			}
			if(e.isWalkable)
				super.position = newPoint;
			moveTurn = false;
		} else
			moveTurn = true;
	}

	@Override
	public void attack(Movable m) {
		m.setHitpoints(DAMAGE);		
	}

	public int getHitpoints() {
		return skeletonHp;
	}

	public void setHitpoints(int value) {
		skeletonHp += value;
		System.out.println(getName() + " hp: " + skeletonHp);
	}

}
