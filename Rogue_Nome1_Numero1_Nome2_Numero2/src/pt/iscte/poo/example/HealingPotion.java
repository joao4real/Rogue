package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends Consumable {

	private static final int HEAL = 5;

	public HealingPotion(Point2D point) {
		super(point, "HealingPotion");
	}

	@Override
	public void pick() {
	}
	
	@Override
	public void consume() {
		GameEngine.getInstance().getHero().setHitpoints(HEAL);
		super.consume();
	}
}