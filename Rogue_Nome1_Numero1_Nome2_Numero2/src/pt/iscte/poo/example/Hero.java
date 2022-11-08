package pt.iscte.poo.example;

import javax.lang.model.element.Element;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends GameElement implements Movable {

	public static final int MAXIMUM_HP = 10;
	private static final int DAMAGE = -1;
	private int HERO_HP;
	private int key;
	private GameElement opponent;

	public Hero(Point2D point) {
		position = point;
		isWalkable = false;
		setHitpoints(MAXIMUM_HP);
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

	public void setKey(int key) {
		this.key = key;
	}

	public void move(Room room) {
		Direction direction = Direction.directionFor(key);
		Vector2D vector = direction.asVector();
		Point2D newPosition = super.position.plus(vector);
		if (room.isPositionWalkable(newPosition)) {
			for (GameElement e : room.getElements()) {
				if (newPosition.equals(e.position) && e instanceof Movable) {
					opponent = e;
					attack(room);
					return;
				}
			}
			super.position = newPosition;
		}
	}

	@Override
	public void attack(Room room) {
		int index = room.getElements().indexOf(opponent);
		GameElement e = room.getElements().get(index);
		if (e instanceof Movable) {
			Movable m = (Movable) e;
			m.setHitpoints(DAMAGE);
		}
	}

	@Override
	public int getHitpoints() {
		return HERO_HP;
	} 

	@Override
	public void setHitpoints(int value) {
		HERO_HP += value;
	}

}
