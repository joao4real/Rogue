package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Skeleton extends GameElement implements Movable {
	
//	private static final int MAXIMUM_HP = 5;

	public Skeleton(Point2D point){
		position=point;
	}

	@Override
	public String getName() {
		return "Skeleton";
	}
	
	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public void move(int key) {
		
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
