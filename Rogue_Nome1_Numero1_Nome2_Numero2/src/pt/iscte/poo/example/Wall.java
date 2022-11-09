package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Wall extends GameElement {

	private Point2D position;

	public Wall(Point2D point) {
		position = point;
	}

	@Override
	public String getName() {
		return "Wall";
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