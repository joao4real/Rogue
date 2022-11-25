package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public abstract class Item extends GameElement {

	public Item(Point2D point, String name) {
		super(point, name);
	}

	public abstract void pick();

	public void drop(int index) {
		GameElement[] inventory = GameEngine.getInstance().getHero().getInventory();
		GameElement e = inventory[index];
		inventory[index] = null;
		e.setPosition(GameEngine.getInstance().getHero().getPosition());
		GameEngine.getInstance().getCurrentRoom().addElement(e);
	}
}