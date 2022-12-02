package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Scorpio extends Movable {

	public static final int MAXIMUM_HP = 2;
	public static final int DAMAGE = -1;

	public Scorpio(Point2D point) {
		super(point, "Scorpio", MAXIMUM_HP, DAMAGE);
	}

	@Override
	public void move(Direction d) {
		super.move(Direction.forVector(super.position.vectorTo(GameEngine.getInstance().getHero().getPosition())));
	}

	@Override
	public void attack(Movable m) {
		if (m instanceof Hero)
			GameEngine.getInstance().getHero().setPoisonStatus(true);
	}
}
