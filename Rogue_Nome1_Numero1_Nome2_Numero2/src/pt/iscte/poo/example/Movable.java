package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class Movable extends GameElement {

    public int hitpoints;
    public int damage;

    public Movable(Point2D point, String name, int hitpoints, int damage) {
        super(point, name);
        this.hitpoints = hitpoints;
        this.damage = damage;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int value) {
        hitpoints += value;
        System.out.println(getName() + hitpoints);
    }

    public void move(Direction d) {
        Vector2D vector = d.asVector();
        Point2D newPoint = super.position.plus(vector);
        GameElement e = GameEngine.getInstance().getCurrentRoom().positionEvaluator(newPoint);
        if (e instanceof Movable) {
            attack((Movable) e);
        }
        if (e.isWalkable)
            super.position = newPoint;
    }

    public abstract void attack(Movable m);

}