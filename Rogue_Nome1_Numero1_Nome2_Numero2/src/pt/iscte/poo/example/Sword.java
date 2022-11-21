package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Sword extends Item {
    
    private final static int DAMAGE_AMP = 2;

    public Sword(Point2D point) {
        super(point, "Sword");
    }

    @Override
    public void pick() {
        GameEngine.getInstance().getHero().scaleDamage(DAMAGE_AMP);
    }

}