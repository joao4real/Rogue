package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Floor extends GameElement {
	
	public Floor(Point2D point) {
		super(point);
		isWalkable = true;
	}

	@Override
	public String getName() {
		return "Floor";
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