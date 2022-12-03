package pt.iscte.poo.example;

public class Player {
	String name;
	int points;
	
	public Player(String name,int points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return name + " -> " + points + " pts";
	}
}
