package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement{
	
	public Door(Point2D point){
		position = point;
	}

	@Override
	public String getName() {
		return "DoorClosed";
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
