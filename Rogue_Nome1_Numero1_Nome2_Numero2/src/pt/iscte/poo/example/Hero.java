package pt.iscte.poo.example;

import java.awt.event.KeyEvent;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends Movable {

	public static final int MAXIMUM_HP = 10;
	private static final int DAMAGE = -1;
	
	//private static Hero INSTANCE = null;

	private double dodgeChance = 0;
	private boolean poisoned = false;
	private GameElement[] inventory = new GameElement[3];
	private int inventoryPointer;
	
/*	public static Hero getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Hero(GameEngine.STARTING_POINT);
        return INSTANCE;
    }*/

	public Hero(Point2D point) {
		super(point, "Hero", MAXIMUM_HP, DAMAGE);
		updateHealthBar(MAXIMUM_HP);
	}

	public void setDodgeChance(double chance) {
		dodgeChance = chance;
	}

	@Override
	public void move(Direction dir) {
		if(poisoned == true)
			setHitpoints(Scorpio.DAMAGE);
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
			else if (hasKey(d.getKeyCode()))
				d.open();
		}
	}

	public int getSlot() {
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] == null)
				return i;
		return -1;
	}

	public boolean hasKey(String doorKey) {
		for (GameElement e : inventory)
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

	public void scaleDamage(double value) {
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
		for (GameElement e : inventory) {
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
			inventoryPointer = keyEventToInt(keybind);
		if(keybind == KeyEvent.VK_C && inventory[inventoryPointer] instanceof Consumable)
			((Consumable) inventory[inventoryPointer]).consume();	
		if(keybind == KeyEvent.VK_D && inventory[inventoryPointer] != null)
			((Item) inventory[inventoryPointer]).drop(inventoryPointer);
			
		}

	public int getInventoryPointer() {
		return inventoryPointer;
	}

	public GameElement[] getInventory() {
		return inventory;
	}

	public boolean isPoisoned() {
		return poisoned;
	}
	
	public void setPoisonStatus(boolean currentStatus) {
		poisoned = currentStatus;
	}
	
	public boolean isInventoryEmpty() {
		for(int i = 0; i < inventory.length; i++)
			if(inventory[i] != null)
				return false;
		return true;
	}
}
