package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Treasure extends GameElement {

	public Treasure(Point2D point) {
		position = point;
	}

	@Override
	public String getName() {
		return "Treasure";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public boolean isWalkable(Point2D point) {
		return false;
	}

}
