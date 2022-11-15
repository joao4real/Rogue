package pt.iscte.poo.example;

import java.util.Scanner;

import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement implements Pickable {

    private String code;

    public Key(Point2D point, Scanner info) {
        super(point, "Key");
        code = info.next();
    }

    public String getCode() {
        return code;
    }

    @Override
    public void pick() {
    }

}