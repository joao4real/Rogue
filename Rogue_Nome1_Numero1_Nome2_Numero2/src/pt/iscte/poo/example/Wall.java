package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Wall extends GameElement {

	public Wall(Point2D point) {
		super(point);
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