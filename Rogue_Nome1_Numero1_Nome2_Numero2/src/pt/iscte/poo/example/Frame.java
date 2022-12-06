package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Frame extends GameElement {

	public Frame(Point2D point) {
		super(point, "Frame");
	}

	public int getLayer() {
		return 0;
	}

}