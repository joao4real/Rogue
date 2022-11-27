package pt.iscte.poo.example;

import java.util.Scanner;

import pt.iscte.poo.utils.Point2D;

public class Key extends Item {

    private String code;

    public Key(Point2D point, Scanner info) {
        super(point, "Key");
        code = info.next();
    }

    public String getCode() {
        return code;
    }
}