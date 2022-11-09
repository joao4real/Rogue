package pt.iscte.poo.example;

public interface Movable {

	public String getName();

	public int getDamage();

	public int getHitpoints();

	public void setHitpoints(int value);

	public void move(Room room);

	public void attack(Movable m);

}
