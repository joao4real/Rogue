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

	public void setHitpoints(int value) {
		int tmp = hitpoints;
		hitpoints = Math.max(0, Math.min(value + hitpoints, maxHitpoints));
		System.out.println(getName() + " Hp: " + hitpoints);
		if (!(this instanceof Hero))
			calculateScore(tmp - hitpoints);
		if (hitpoints < GameEngine.MINIMUM_HP)
			die();

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

	public void attack(Movable m) {
		if (m instanceof Hero || this instanceof Hero)
			m.setHitpoints(damage);

	}

	public void die() {
		if (this instanceof Hero) {
			Hero.getInstance().updateHealthBar(hitpoints);
			GameEngine.getInstance().lose();
		}
		GameEngine.getInstance().getCurrentRoom().getElements().remove(this);
		GameEngine.getInstance().gui.removeImage(this);
	}

	public void calculateScore(int value) {
		GameEngine.getInstance().addScore(value);
		if (hitpoints < GameEngine.MINIMUM_HP) {
			int score = Hero.getInstance().hasItem("Sword") ? maxHitpoints * 10 : maxHitpoints * 5;
			GameEngine.getInstance().addScore(score);
		}
	}

}