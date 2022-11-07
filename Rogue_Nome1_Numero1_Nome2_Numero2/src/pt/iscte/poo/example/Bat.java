package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Bat extends GameElement implements Movable{
	
	public Bat(Point2D point){
		position = point;
		isWalkable = false;
	}

	@Override
	public String getName() {
		return "Bat";
	}
	
	@Override
	public int getLayer() {
		return 0;
	}
	
	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public void move(int key, Room room) {
		
	}
}
