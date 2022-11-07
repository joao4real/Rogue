package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement{
	
	public HealingPotion(Point2D point){
		position = point;
		isWalkable = false;
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
}
