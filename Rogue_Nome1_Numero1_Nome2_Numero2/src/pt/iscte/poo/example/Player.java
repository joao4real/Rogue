package pt.iscte.poo.example;

public class Player {
	
	private String name;
	private int points;

	public Player(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public static Player playerFromLine(String line) {
		String[] strings = line.split("->");
		String name = strings[0].trim();
		int points = Integer.parseInt(strings[1].split("pts")[0].trim());
		return new Player(name, points);
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