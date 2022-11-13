package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Armor extends GameElement implements Pickable {

    private static final double DODGE_CHANCE = 0.5;

    public Armor(Point2D point) {
        super(point, "Armor");
    }

    @Override
    public void pick() {
        GameEngine.getHero().setDodgeChance(DODGE_CHANCE);
    }

}