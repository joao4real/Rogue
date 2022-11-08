package pt.iscte.poo.example;

public interface Movable {

	public String getName();

	public void move(Room room);

	public int getLayer();
	
	public void attack(Room room);
	
	public int getHitpoints();
	
	public void setHitpoints(int value);

}
