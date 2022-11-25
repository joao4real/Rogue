package pt.iscte.poo.example;

import java.awt.event.KeyEvent;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends Movable {

	private static final int MAXIMUM_HP = 10;
	private static final int DAMAGE = -1;
	private static final int DEFAULT_HERO_LAYER = 1;

	private double dodgeChance = 0;
	private GameElement[] inventory = new GameElement[3];
	private int inventoryPointer;

	public Hero(Point2D point) {
		super(point, "Hero", MAXIMUM_HP, DAMAGE);
		updateHealthBar(MAXIMUM_HP);
	}

	public void setDodgeChance(double chance) {
		dodgeChance = chance;
	}

	@Override
	public int getLayer() {
		return DEFAULT_HERO_LAYER;
	}

	@Override
	public void move(Direction dir) {
		Vector2D vector = dir.asVector();
		Point2D newPoint = super.position.plus(vector);
		GameElement e = GameEngine.getInstance().getCurrentRoom().getElement(newPoint);
		if (e instanceof Item) {
			pick(e);
		}
		super.move(dir);
		if (e instanceof Door) {
			Door d = (Door) e;
			if (d.isOpen())
				GameEngine.getInstance().manageRoom(d.getRoomName(), d.getHeroPoint());
			else if (hasKey(d.getKeyCode()))
				d.open();
		}
	}

	public void pick(GameElement e) {
		if (!addItem(e))
			return;
		GameEngine.getInstance().getCurrentRoom().removeElement(e);
		GameEngine.getInstance().gui.removeImage(e);
		((Item) e).pick();
	}

	public boolean addItem(GameElement e) {
		int index = getSlot();
		if (index < 0) {
			System.out.println("Inventory is Full!");
			return false;
		} else {
			e.setPosition(new Point2D(MAXIMUM_HP / 2 + index, GameEngine.GRID_HEIGHT));
			getInventory()[index] = e;
			GameEngine.getInstance().gui.addImage(e);
			return true;
		}
	}

	public int getSlot() {
		for (int i = 0; i < getInventory().length; i++)
			if (getInventory()[i] == null)
				return i;
		return -1;
	}

	public boolean hasKey(String doorKey) {
		for (GameElement e : getInventory())
			if (e instanceof Key)
				if (((Key) e).getCode().equals(doorKey))
					return true;
		return false;
	}

	@Override
	public void attack(Movable m) {
		m.setHitpoints(super.damage);
	}

	@Override
	public void setHitpoints(int value) {
		if (Math.random() > dodgeChance)
			super.setHitpoints(value);
		updateHealthBar(super.hitpoints);
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
			GameEngine.getInstance().gui
					.addImage(GameElement.create(code, new Point2D(i, GameEngine.GRID_HEIGHT), null));
		}
	}

	public void updateInventory() {
		for (GameElement e : getInventory()) {
			if (e != null)
				GameEngine.getInstance().gui.addImage(e);
		}
	}

	public void updateHeroBar() {
		updateHealthBar(hitpoints);
		updateInventory();
	}

	public int keyEventToInt(int key) {
		switch (key) {
		case KeyEvent.VK_1:
			return 0;
		case KeyEvent.VK_2:
			return 1;
		default:
			return 2;
		}
	}
	public void keyEvaluator(int keybind) {
		if(keybind == KeyEvent.VK_1 || keybind == KeyEvent.VK_2 ||keybind == KeyEvent.VK_3)
			setInventoryPointer(keyEventToInt(keybind));
		if(keybind == KeyEvent.VK_C && getInventory()[inventoryPointer] instanceof Consumable)
			((Consumable) getInventory()[inventoryPointer]).consume();	
		if(keybind == KeyEvent.VK_D && inventory[inventoryPointer] != null)
			((Item) inventory[inventoryPointer] ).drop(inventoryPointer);
			
		}

	public int getInventoryPointer() {
		return inventoryPointer;
	}

	public void setInventoryPointer(int inventoryPointer) {
		this.inventoryPointer = inventoryPointer;
	}

	public GameElement[] getInventory() {
		return inventory;
	}
}
