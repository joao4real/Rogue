package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement{
	
	public Key(Point2D point){
		position = point;
	}

	@Override
	public String getName() {
		return "Key";
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
