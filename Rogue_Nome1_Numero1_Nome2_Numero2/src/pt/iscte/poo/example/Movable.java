package pt.iscte.poo.example;

import pt.iscte.poo.utils.*;

public abstract class Movable extends GameElement {

	private static final int DEFAULT_MOVABLE_LAYER = 1;
	public final int maxHitpoints;
	public int hitpoints;
	public int damage;

	public Movable(Point2D point, String name, int hitpoints, int damage) {
		super(point, name);
		maxHitpoints = hitpoints;
		this.hitpoints = hitpoints;
		this.damage = damage;
	}
	
	@Override
	public int getLayer() {
		return DEFAULT_MOVABLE_LAYER;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(int value) {
		hitpoints = Math.min(value + hitpoints, maxHitpoints);
		System.out.println(getName() + hitpoints);
	}

	public void move(Direction d) {
		Vector2D vector = d.asVector();
		Point2D newPoint = super.position.plus(vector);
		GameElement e = GameEngine.getInstance().getCurrentRoom().positionEvaluator(newPoint);
		if (e instanceof Movable) {
			attack((Movable) e);
		}
		if (e.isWalkable)
			super.position = newPoint;
	}
	
	public void attack(Movable m){
		m.setHitpoints(damage);
	}
	
	public void die(Movable m) {
		if (m.getHitpoints() < GameEngine.MINIMUM_HP) 
			GameEngine.getInstance().gui.removeImage(m);
	}
}