package pt.iscte.poo.example;

import java.util.ArrayList;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends Movable {

	private static final int MAXIMUM_HP = 10;
	private static final int DAMAGE = -1;

	private double dodgeChance = 0;

	private ArrayList<GameElement> inventory = new ArrayList<>();

	public Hero(Point2D point) {
		super(point, "Hero", MAXIMUM_HP, DAMAGE);
		updateHealthBar(MAXIMUM_HP);
	}

	public void setDodgeChance(double chance) {
		dodgeChance = chance;
	}

	@Override
	public void move(Direction dir) {
		Vector2D vector = dir.asVector();
		Point2D newPoint = super.position.plus(vector);
		GameElement e = GameEngine.getInstance().getCurrentRoom().getElement(newPoint);
		if (e instanceof Pickable) {
			pick(e);
		}
		super.move(dir);
		if (e instanceof Door) {
			Door d = (Door) e;
			if (d.isOpen())
				GameEngine.getInstance().manageRoom(d.getRoomName(), d.getHeroPoint());
			else
				for (GameElement i : inventory) {
					if (i instanceof Key) {
						Key k = (Key) i;
						if (k.getCode().equals(d.getKeyCode()))
							d.open();
					}
				}
		}
	}

	@Override
	public void attack(Movable m) {
		m.setHitpoints(super.damage);
	}

	public void pick(GameElement e) {
		inventory.add(e);
		GameEngine.getInstance().getCurrentRoom().removeElement(e);
		ImageMatrixGUI.getInstance().removeImage(e);
		((Pickable) e).pick();
	}

	@Override
	public void setHitpoints(int value) {
		if (Math.random() > dodgeChance)
			super.hitpoints += value;
		updateHealthBar(super.hitpoints);
		System.out.println(getName() + super.hitpoints);
	}

	public void setPosition(Point2D point) {
		super.position = point;
	}

	public void scaleDamage(int value) {
		super.damage *= value;
	}

	public void updateHealthBar(int hitpoints) {
		String code = "";
		for (int i = 0; i < MAXIMUM_HP / 2; i++) {
			if (i * 2 < hitpoints - 1)
				code = "Green";
			if (i * 2 == hitpoints - 1)
				code = "GreenRed";
			if (i * 2 >= hitpoints)
				code = "Red";
			GameEngine.getInstance().gui.addImage(GameElement.create(code, new Point2D(i, GameEngine.GRID_HEIGHT), null));
		}
	}
}