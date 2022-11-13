package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement implements Pickable {

    private String code;

    public Key(Point2D point, String code) {
        super(point, "Key");
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public void pick() {
    }

}