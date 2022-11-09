package pt.iscte.poo.example;

import java.util.ArrayList;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends GameElement implements Movable {

	public static final int MAXIMUM_HP = 10;
	private static final int DAMAGE = -1;
	private int heroHp;
	private int key;
	private ArrayList<GameElement> inventory = new ArrayList<>();
	
	public Hero(Point2D point) {
		position = point;
		isWalkable = false;
		heroHp = MAXIMUM_HP;
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

	@Override
	public int getDamage() {
		return DAMAGE;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public void move(Room room) {
		Direction direction = Direction.directionFor(key);
		Vector2D vector = direction.asVector();
		Point2D newPoint = super.position.plus(vector);
		GameElement e = room.positionEvaluator(newPoint);
		if (e instanceof Movable) {
			attack((Movable) e);
		}
		if (e.isWalkable)
			super.position = newPoint;

	}

	@Override
	public void attack(Movable m) {
		m.setHitpoints(DAMAGE);
	}

	@Override
	public int getHitpoints() {
		return heroHp;
	}

	@Override
	public void setHitpoints(int value) {
		heroHp += value;
		System.out.println(getName() + " hp: " + heroHp);
	}

}
