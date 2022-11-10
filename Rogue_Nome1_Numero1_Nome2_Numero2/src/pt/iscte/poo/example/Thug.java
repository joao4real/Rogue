package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thug extends Movable {

	private static final int DAMAGE = -3;
	private static final int MAXIMUM_HP = 10;

	public Thug(Point2D point) {
		super(point, MAXIMUM_HP);
	}

	@Override
	public String getName() {
		return "Thug";
	}

	@Override
	public Point2D getPosition() {
		return super.position;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void move(int key) {
		Room currentRoom = GameEngine.getInstance().currentRoom;
		Vector2D vector = Vector2D.movementVector(super.position,currentRoom.getHero().getPosition());
		Point2D newPoint = super.position.plus(vector);
		GameElement e = GameEngine.getInstance().currentRoom.positionEvaluator(newPoint);
		if(e instanceof Movable){
			attack((Movable) e);
		}
		if(e.isWalkable)
			super.position = newPoint;
	}

	@Override
	public void attack(Movable m) {
		double prob = Math.random();
		if(prob < 0.3)
		m.setHitpoints(DAMAGE);
	}

	public int getHitpoints() {
		return super.hitpoints;
	}

}