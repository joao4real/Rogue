package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Thief extends Movable {

	private static final int DAMAGE = 0;
	private static final int MAXIMUM_HP = 5;
	private GameElement stolenElement;

	public Thief(Point2D point) {
		super(point, "Thief", MAXIMUM_HP, DAMAGE);
	}

	@Override
	public void move(Direction d) {
		d = Direction.forVector(super.position.vectorTo(GameEngine.getInstance().getHero().getPosition()));
		if (stolenElement != null || GameEngine.getInstance().getHero().isInventoryEmpty())
			d = d.opposite();
		super.move(d);
	}

	public void removeRandomElement() {
		GameElement[] inventory = GameEngine.getInstance().getHero().getInventory();
		if (GameEngine.getInstance().getHero().isInventoryEmpty())
			return;
		int i = (int) (Math.random() * inventory.length);
		GameElement e = inventory[i];
		while (e == null) {
			i = (int) (Math.random() * inventory.length);
			e = inventory[i];
		}
		inventory[i] = null;
		stolenElement = e;
		System.out.println("Your " + stolenElement.getName() + " got stolen by a Thief!");
		GameEngine.getInstance().gui.removeImage(e);
	}

	@Override
	public void attack(Movable m) {
		if (m instanceof Hero) {
			removeRandomElement();
			return;
		}
	}

	public GameElement getStolenElement() {
		return stolenElement;
	}

	@Override
	public void die() {
		if (stolenElement != null) {
			stolenElement.setPosition(super.position);
			GameEngine.getInstance().gui.addImage(stolenElement);
			GameEngine.getInstance().getCurrentRoom().addElement(stolenElement);
		}
		super.die();
	}
}