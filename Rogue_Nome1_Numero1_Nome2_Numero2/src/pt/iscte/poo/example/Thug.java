package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Thug extends Movable {

    private static final int MAXIMUM_HP = 10;
    private static final int DAMAGE = -3;
    private static final double ATTACK_CHANCE = 0.3;

    public Thug(Point2D point) {
        super(point, "Thug", MAXIMUM_HP, DAMAGE);
    }

//    @Override
//    public void move(Room room) {
//        Vector2D vector = Vector2D.movementVector(super.position, room.getHero().getPosition());
//        super.move(room, super.position.plus(vector));
//    }

    @Override
    public void attack(Movable m) {
        if (Math.random() < ATTACK_CHANCE)
            m.setHitpoints(DAMAGE);
    }

}