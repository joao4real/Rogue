package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Thief extends Movable {

	private static final int DAMAGE = 0;
	private static final int MAXIMUM_HP = 10;
	private GameElement stolenElement;

	public Thief(Point2D point) {
		super(point, "Thief", MAXIMUM_HP, DAMAGE);
	}

	@Override
	public void move(Direction d) {
		if (stolenElement == null)
			super.move(Direction.forVector(super.position.vectorTo(GameEngine.getInstance().getHero().getPosition())));
		else
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

	@Override
	public void attack(Movable m) {
		int x = getRandomElement();
		while(GameEngine.getInstance().getHero().getInventory()[x] == null)
			x = getRandomElement();
		 GameElement e = 
	}

	public GameElement getStolenElement() {
		return stolenElement;
	}

	public void setStolenElement(GameElement stolenElement) {
		this.stolenElement = stolenElement;
	}

	@Override
	public void die(Movable m) {
		if (stolenElement != null) {
			stolenElement.setPosition(super.position);
			GameEngine.getInstance().gui.addImage(stolenElement);
			GameEngine.getInstance().getCurrentRoom().addElement(stolenElement);
		}
		super.die(m);
	}
}