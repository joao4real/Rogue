package pt.iscte.poo.example;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends Movable {

	public static final int MAXIMUM_HP = 10;
	private static final int DAMAGE = -1;

	private static Hero INSTANCE = null;

	private boolean poisoned = false;
	private GameElement[] inventory = new GameElement[3];
	private ArrayList<GameElement> healthBar = new ArrayList<>();
	private ArrayList<GameElement> bar = new ArrayList<>();
	private int inventoryPointer;

	public static Hero getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Hero(GameEngine.STARTING_POINT);
		return INSTANCE;
	}

	public static Hero resetInstance() {
		if (INSTANCE != null)
			INSTANCE = null;
		return getInstance();
	}

	private Hero(Point2D point) {
		super(point, "Hero", MAXIMUM_HP, DAMAGE);
		updateHeroBar();
	}

	@Override
	public void move(Direction dir) {
		if (poisoned == true)
			setHitpoints(Scorpion.DAMAGE);
		Vector2D vector = dir.asVector();
		Point2D newPoint = super.position.plus(vector);
		GameElement e = GameEngine.getInstance().getCurrentRoom().getElement(newPoint);
		if (e instanceof Item) 
			((Item) e).pick(e);
		super.move(dir);
		if (e instanceof Door) {
			Door d = (Door) e;
			if (d.isOpen())
				GameEngine.getInstance().manageRoom(d.getRoomName(), d.getHeroPoint());
			else if (hasItem(d.getKeyCode())) {
				d.open();
				Door.DOOR_TO_OPEN = true;
			}
		}
		if (e instanceof Treasure)
			GameEngine.getInstance().win();
	}

	public int getSlot() {
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] == null)
				return i;
		return -1;
	}

	public boolean hasItem(String code) {
		for (GameElement e : inventory) {
			if (e != null) {
				if (e.getName().equals(code))
					return true;
				if (e instanceof Key)
					if (((Key) e).getCode().equals(code))
						return true;
			}
		}
		return false;
	}

	@Override
	public void attack(Movable m) {
		int i = hasItem("Sword") ? -Sword.EXTRA_DAMAGE : 0;
		m.setHitpoints(damage + i);
	}

	@Override
	public void setHitpoints(int value) {
		if (hasItem("Armor") && Math.random() > Armor.DODGE_CHANCE && value < 0)
			return;
		super.setHitpoints(value);
		updateHealthBar(super.hitpoints);
	}

	public void updateHealthBar(int hitpoints) {
		if (healthBar.isEmpty())
			for (int i = 0; i < MAXIMUM_HP / 2; i++)
				healthBar.add(GameElement.create("Green", new Point2D(i + 1, GameEngine.GRID_HEIGHT), null));
		else {
			healthBar.forEach(e -> GameEngine.getInstance().gui.removeImage(e));
			String code = "";
			for (int i = 0; i < MAXIMUM_HP / 2; i++) {
				if (i * 2 < hitpoints - 1)
					code = "Green";
				if (i * 2 == hitpoints - 1)
					code = "GreenRed";
				if (i * 2 >= hitpoints)
					code = "Red";
				healthBar.get(i).setName(code);
			}
		}
		healthBar.forEach(e -> GameEngine.getInstance().gui.addImage(e));
	}

	public void updateInventory() {
		for (GameElement e : inventory) {
			if (e != null)
				GameEngine.getInstance().gui.addImage(e);
		}
	}

	public void updateHeroBar() {
		if (bar.isEmpty()) {
			bar.add(new Heart(new Point2D(0, GameEngine.GRID_HEIGHT)));
			bar.add(new Backpack(new Point2D(6, GameEngine.GRID_HEIGHT)));
			bar.add(new Black(new Point2D(7, GameEngine.GRID_HEIGHT)));
			bar.add(new Black(new Point2D(8, GameEngine.GRID_HEIGHT)));
			bar.add(new Black(new Point2D(9, GameEngine.GRID_HEIGHT)));
			bar.add(new Frame(new Point2D(7, GameEngine.GRID_HEIGHT)));
		}
		bar.forEach(e -> GameEngine.getInstance().gui.addImage(e));
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
		if (keybind == KeyEvent.VK_1 || keybind == KeyEvent.VK_2 || keybind == KeyEvent.VK_3) {
			inventoryPointer = keyEventToInt(keybind);
			GameEngine.genericSearch(bar, e -> e.getName().equals("Frame"))
					.setPosition(new Point2D(inventoryPointer + 7, GameEngine.GRID_HEIGHT));
		}
		if (keybind == KeyEvent.VK_E && inventory[inventoryPointer] instanceof Consumable)
			((Consumable) inventory[inventoryPointer]).consume();
		if (keybind == KeyEvent.VK_Q && inventory[inventoryPointer] != null)
			((Item) inventory[inventoryPointer]).drop(inventoryPointer);

	}

	public int getInventoryPointer() {
		return inventoryPointer;
	}

	public GameElement[] getInventory() {
		return inventory;
	}

	public void setPoisonStatus(boolean currentStatus) {
		poisoned = currentStatus;
	}

	public boolean isInventoryEmpty() {
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] != null)
				return false;
		return true;
	}

}
