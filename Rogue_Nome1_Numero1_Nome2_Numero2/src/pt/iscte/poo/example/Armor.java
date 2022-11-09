package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Armor extends GameElement{

	public Armor(Point2D point) {
		position = point;
		isPickable = true;
	}

	@Override
	public String getName() {
		return "Armor";
	}
	
	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	}
}
