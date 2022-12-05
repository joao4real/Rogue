package pt.iscte.poo.example;

import java.util.Scanner;

public class Player {
	String name;
	int points;

	public Player(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public static Player playerFromLine(String line) {
		Scanner sc = new Scanner(line);
		String name = sc.next();
		sc.next();
		int points = sc.nextInt();
		sc.close();
		return new Player(name, points);

	}
}
