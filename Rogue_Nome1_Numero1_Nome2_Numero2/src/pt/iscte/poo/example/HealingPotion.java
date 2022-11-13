package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement {

    private static final int HEAL = 5;

    public HealingPotion(Point2D point) {
        super(point, "HealingPotion");
    }

    public int getHeal() {
        return HEAL;
    }
}