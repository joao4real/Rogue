package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Skeleton extends Movable {

	private static final int DAMAGE = -1;
	private static final int MAXIMUM_HP = 5;
	private boolean moveTurn = true;

	public Skeleton(Point2D point) {
		super(point, "Skeleton", MAXIMUM_HP, DAMAGE);
	}

	@Override
	public void move(Direction d) {
		if (moveTurn)
			super.move(d);
		moveTurn = moveTurn ? false : true;
	}

	@Override
	public void attack(Movable m) {
		if(m instanceof Hero)
			super.attack(m);
	}
}