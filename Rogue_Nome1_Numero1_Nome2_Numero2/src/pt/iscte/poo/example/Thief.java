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
	
	public GameElement getStolenElement() {
		return stolenElement;
	}

	@Override
	public void move(Direction d) {
		d = Direction.forVector(super.position.vectorTo(Hero.getInstance().getPosition()));
		if (stolenElement != null || Hero.getInstance().isInventoryEmpty())
			d = d.opposite();
		super.move(d);
	}

	@Override
	public void attack(Movable m) {
		if (m instanceof Hero) {
			removeRandomElement();
			return;
		}
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
	
	public void removeRandomElement() {
		GameElement[] inventory = Hero.getInstance().getInventory();
		if (Hero.getInstance().isInventoryEmpty())
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

}