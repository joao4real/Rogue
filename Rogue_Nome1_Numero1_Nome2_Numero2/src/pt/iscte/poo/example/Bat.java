package pt.iscte.poo.example;

import java.awt.event.KeyEvent;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bat extends Movable {

    private static final int MAXIMUM_HP = 3;
    private static final int DAMAGE = -1;
    private static final int HEAL = 1;

    public Bat(Point2D point) {
        super(point, "Bat", MAXIMUM_HP, DAMAGE);
    }

    @Override
    public void move(int key) {
        if (Math.random() > 0.5)
            key = keyFor(Direction.random());
        super.move(key);
    }

    @Override
    public void attack(Movable m) {
        if (Math.random() > 0.5) {
            m.setHitpoints(DAMAGE);
            heal();
        }
    }

    public void heal() {
        if (super.hitpoints < 3)
            setHitpoints(HEAL);
        System.out.println(getName() + super.hitpoints);
    }

    public static int keyFor(Direction d) {
        switch (d) {
        case DOWN:
            return KeyEvent.VK_DOWN;
        case UP:
            return KeyEvent.VK_UP;
        case LEFT:
            return KeyEvent.VK_LEFT;
        case RIGHT:
            return KeyEvent.VK_RIGHT;
        default:
            throw new IllegalArgumentException();
        }
    }
}