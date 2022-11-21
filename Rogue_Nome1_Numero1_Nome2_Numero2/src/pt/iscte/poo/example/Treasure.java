package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Treasure extends Item{

    public Treasure(Point2D point) {
        super(point, "Treasure");
    }

    @Override
    public void pick() {
//        win();
    }

}