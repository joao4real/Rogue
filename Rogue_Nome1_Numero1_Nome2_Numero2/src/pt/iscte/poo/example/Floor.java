package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Floor extends GameElement {

    public Floor(Point2D point) {
        super(point, "Floor");
        isWalkable = true;
    }

}