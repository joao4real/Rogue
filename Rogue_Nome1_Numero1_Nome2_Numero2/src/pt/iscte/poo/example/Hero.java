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
		setHealth();
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
			updateHealth(value);
		System.out.println(getName() + super.hitpoints);
	}

	public void setPosition(Point2D point) {
		super.position = point;
	}

	public void scaleDamage(int value) {
		super.damage *= value;
	}

	public void setHealth() {
		for (int i = 0; i < Hero.MAXIMUM_HP / 2; i++)
			GameEngine.getInstance().gui.addImage(new Green(new Point2D(i, GameEngine.GRID_HEIGHT)));
	}
	
	public void updateHealth(int value) {
		  if(value == 0)
		    return;
		  int x = super.hitpoints;
		   if(x % 2 == 1){
		     x = x/2;
		     value--;
		     GameEngine.getInstance().gui.addImage(new Red(new Point2D(x--, GameEngine.GRID_HEIGHT)));
		   }
		   x = x/2-1;
		   while(value <= 0) {
		     if(value % 2 == 0) {
		    	 GameEngine.getInstance().gui.addImage(new Red(new Point2D(x--, GameEngine.GRID_HEIGHT)));
		       value += 2;
		    } else {
		    	GameEngine.getInstance().gui.addImage(new GreenRed(new Point2D(x--, GameEngine.GRID_HEIGHT)));
		       value++;
		    }
		  }
		}
}
