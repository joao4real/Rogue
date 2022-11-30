package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Thug extends Movable {

	private static final int MAXIMUM_HP = 10;
	private static final int DAMAGE = -3;
	private static final double ATTACK_CHANCE = 0.3;

	public Thug(Point2D point) {
		super(point, "Thug", MAXIMUM_HP, DAMAGE);
	}

	@Override
	public void move(Direction d) {
		super.move(Direction.forVector(super.position.vectorTo((GameEngine.getInstance().getHero()).getPosition())));
	}

	@Override
	public void attack(Movable m) {
		if (Math.random() < ATTACK_CHANCE)
			super.attack(m);
	}

}