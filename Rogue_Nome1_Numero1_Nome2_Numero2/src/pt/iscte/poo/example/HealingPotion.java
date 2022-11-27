package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends Consumable {

	private static final int HEAL = 5;

	public HealingPotion(Point2D point) {
		super(point, "HealingPotion");
	}
	
	@Override
	public void consume() {
		Hero hero = GameEngine.getInstance().getHero();
		hero.setHitpoints(HEAL);
		hero.setPoisonStatus(false);
		super.consume();
	}
}