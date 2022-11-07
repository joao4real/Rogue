package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement{
	
	public HealingPotion(Point2D point){
		position = point;
	}

	@Override
	public String getName() {
		return "HealingPotion";
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
