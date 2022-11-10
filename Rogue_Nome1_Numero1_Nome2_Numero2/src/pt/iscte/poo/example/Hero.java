package pt.iscte.poo.example;

import java.util.ArrayList;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends Movable {

	public static final int MAXIMUM_HP = 10;
	private static final int DAMAGE = -1;
	private ArrayList<GameElement> inventory = new ArrayList<>();

	public Hero(Point2D point) {
		super(point, MAXIMUM_HP);
	}

	@Override
	public String getName() {
		return "Hero";
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	public void move(int key) {
		Direction direction = Direction.directionFor(key);
		Vector2D vector = direction.asVector();
		Point2D newPoint = super.position.plus(vector);
		GameElement e = GameEngine.getInstance().currentRoom.getElement(newPoint);	
		if (e.isPickable) {
			inventory.add(e);
			GameEngine.getInstance().currentRoom.removeElement(e);
			ImageMatrixGUI.getInstance().removeImage(e);
		}
		super.move(key);
	}

	@Override
	public void attack(Movable m) {
		m.setHitpoints(DAMAGE);
	}

}
