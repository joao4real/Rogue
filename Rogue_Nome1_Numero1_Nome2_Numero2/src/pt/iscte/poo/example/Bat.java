package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bat extends Movable {

	private static final int MAXIMUM_HP = 3;
	private static final int DAMAGE = -1;
	private static final int HEAL = 1;

	public Bat(Point2D point) {
		super(point, "Bat", MAXIMUM_HP, DAMAGE);
	}

	@Override
	public void move(Direction d) {
		if (Math.random() > 0.5)
			d = (Direction.random());
		super.move(d);
	}

	@Override
	public void attack(Movable m) {
		if (Math.random() > 0.5) {
			super.attack(m);
			super.setHitpoints(HEAL);
		}
	}

}