package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class Movable extends GameElement{
	
	public int hitpoints;
	
	public Movable(Point2D point, int hitpoints) {
		super(point);
		this.hitpoints = hitpoints;
	}

	public int getHitpoints(){
		return hitpoints;
	}

	public void setHitpoints(int value){
		hitpoints += value;
	}
	
	public void move(int key){
		Direction direction = Direction.directionFor(key);
		Vector2D vector = direction.asVector();
		Point2D newPoint = super.position.plus(vector);
		GameElement e = GameEngine.getInstance().currentRoom.positionEvaluator(newPoint);
		if(e instanceof Movable){
			attack((Movable) e);
		}
		if(e.isWalkable)
			super.position = newPoint;
	}

	public abstract void attack(Movable m);

}
