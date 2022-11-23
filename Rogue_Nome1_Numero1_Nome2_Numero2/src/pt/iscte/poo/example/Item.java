package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public abstract class Item extends GameElement {

	public Item(Point2D point, String name) {
		super(point, name);
	}

	public void pick() {
	}

	public void drop() {
	}
}