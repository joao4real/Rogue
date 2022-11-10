package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Sword extends GameElement {

	public Sword(Point2D point) {
		super(point);		
		isPickable = true;
	}

	@Override
	public String getName() {
		return "Sword";
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
