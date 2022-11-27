package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public abstract class Item extends GameElement {

	public Item(Point2D point, String name) {
		super(point, name);
		super.isWalkable = true;
	}

	public void pick(GameElement e) {
		if (!addItem(e))
			return;
		GameEngine.getInstance().getCurrentRoom().removeElement(e);
		GameEngine.getInstance().gui.removeImage(e);
	}

	public boolean addItem(GameElement e) {
		Hero hero = GameEngine.getInstance().getHero();
		int index = hero.getSlot();
		if (index < 0) {
			System.out.println("Inventory is Full!");
			return false;
		} else {
			e.setPosition(new Point2D(Hero.MAXIMUM_HP / 2 + index, GameEngine.GRID_HEIGHT));
			hero.getInventory()[index] = e;
			GameEngine.getInstance().gui.addImage(e);
			return true;
		}
	}

	public void drop(int index) {
		GameElement[] inventory = GameEngine.getInstance().getHero().getInventory();
		GameElement e = inventory[index];
		inventory[index] = null;
		e.setPosition(GameEngine.getInstance().getHero().getPosition());
		GameEngine.getInstance().getCurrentRoom().addElement(e);
	}
}