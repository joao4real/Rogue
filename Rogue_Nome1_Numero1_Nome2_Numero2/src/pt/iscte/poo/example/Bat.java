package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Bat extends Movable {

	private static final int DAMAGE = -1;
	private static final int HEAL = 1;
	private static final int MAXIMUM_HP = 3;

	public Bat(Point2D point) {
		super(point, MAXIMUM_HP);
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
		return super.position;
	}

	@Override
	public void move(int key) {
		if (Math.random() > 0.5)
			super.move(key);
	}

	@Override
	public void attack(Movable m) {
		if (Math.random() > 0.5) {
			m.setHitpoints(DAMAGE);
			heal();
		}
	}

	public void heal() {
		if (super.hitpoints < 3)
			setHitpoints(HEAL);
	}

}
