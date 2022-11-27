package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Thief extends Movable {

	private static final int DAMAGE = 0;
	private static final int MAXIMUM_HP = 10;

	public Thief(Point2D point) {
		super(point, "Thief", MAXIMUM_HP, DAMAGE);
	}

	@Override
	public void move(Direction d) {
		super.move(Direction.forVector(super.position.vectorTo(GameEngine.getInstance().getHero().getPosition()))
				.opposite());
	}

	public int getRandomElement() {
		double r = Math.random();
		if (r < 0.33)
			return 0;
		if (0.33 < r && r < 0.66)
			return 1;
		return 2;
	}

	public GameElement steal() {
		GameElement[] inventory = GameEngine.getInstance().getHero().getInventory();
		int x = getRandomElement();
		GameElement e = inventory[x];
		inventory[x] = null;
		GameEngine.getInstance().gui.removeImage(e);
		return e;
	}
}