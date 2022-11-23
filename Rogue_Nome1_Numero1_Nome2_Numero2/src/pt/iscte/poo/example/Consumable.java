package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public abstract class Consumable extends Item{

	public Consumable(Point2D point, String name) {
		super(point, name);
	}

	public void consume(int keybind){
		GameEngine.getInstance().gui.removeImage(this);
	}
}
