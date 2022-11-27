package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Armor extends Item {

	private static final double DODGE_CHANCE = 0.5;

	public Armor(Point2D point) {
		super(point, "Armor");
	}

	@Override
	public void pick(GameElement e) {
		GameEngine.getInstance().getHero().setDodgeChance(DODGE_CHANCE);
		super.pick(e);
	}

	@Override
	public void drop(int index) {
		GameEngine.getInstance().getHero().setDodgeChance(0.0);
		super.drop(index);
	}

}