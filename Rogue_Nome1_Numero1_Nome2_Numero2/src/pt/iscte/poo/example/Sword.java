package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Sword extends Item {
    
    private final static double DAMAGE_AMP = 2.0;

    public Sword(Point2D point) {
        super(point, "Sword");
    }

    @Override
    public void pick(GameElement e) {
    	GameEngine.getInstance().getHero().scaleDamage(DAMAGE_AMP);
        super.pick(e);
    }

    @Override
    public void drop(int index) {
    	GameEngine.getInstance().getHero().scaleDamage(1 / DAMAGE_AMP);
        super.drop(index);
    }
}