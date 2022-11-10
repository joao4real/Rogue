package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement {

	private static final int HEAL = 5;

	public HealingPotion(Point2D point) {
		super(point);
		isPickable = true;
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

	public void setHitpoints(Room room) {
		if (room.getHero().getHitpoints() < 5) {
			room.getHero().setHitpoints(HEAL);
		} else {
			int newHP = Hero.MAXIMUM_HP - room.getHero().getHitpoints();
			room.getHero().setHitpoints(newHP);
		}
	}

	public int getHeal() {
		return HEAL;
	}
}
