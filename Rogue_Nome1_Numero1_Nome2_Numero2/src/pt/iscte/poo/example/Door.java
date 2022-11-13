package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement {
    
    private boolean isOpen = false;

    public Door(Point2D point) {
        super(point, "DoorClosed");
    }

    @Override
    public String getName() {
        return isOpen ? "DoorOpen" : super.getName();
    }

    public void openDoor(){
        isWalkable = true;
        isOpen = true;
    }
}