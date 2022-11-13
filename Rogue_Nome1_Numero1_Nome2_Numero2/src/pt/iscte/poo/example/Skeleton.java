package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Skeleton extends Movable {

    private static final int DAMAGE = -1;
    private static final int MAXIMUM_HP = 5;
    private boolean moveTurn = true;

    public Skeleton(Point2D point) {
        super(point, "Skeleton", MAXIMUM_HP, DAMAGE);
    }

    @Override
    public void move(int key) {
        if (moveTurn) {
            super.move(key);
            moveTurn = false;
        } else
            moveTurn = true;
    }

    @Override
    public void attack(Movable m) {
        m.setHitpoints(DAMAGE);
    }

}