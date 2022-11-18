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
	}

	public void setDodgeChance(double chance) {
		dodgeChance = chance;
	}

	@Override
	public void move(Direction d) {
		Vector2D vector = d.asVector();
		Point2D newPoint = super.position.plus(vector);
		GameElement e = GameEngine.getInstance().getCurrentRoom().getElement(newPoint);
		if (e instanceof Pickable) {
			pick(e);
			super.move(d);
		}
		if (e instanceof Door) {
			Door p = (Door) e;
			if (!p.isOpen()) {
				for (GameElement i : inventory) {
					if (i instanceof Key) {
						Key k = (Key) i;
						if (k.getCode().equals(p.getKeyCode())) {
							p.open();
							return;
						}
					}
				}
			} else {
				GameEngine.getInstance().manageRoom(p.getRoomName(), p.getHeroPoint());
				return;
			}
		}
		super.move(d);
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
		System.out.println(getName() + super.hitpoints);
	}

	public void setPosition(Point2D point) {
		super.position = point;
	}

	public void scaleDamage(int value) {
		super.damage *= value;
	}
}