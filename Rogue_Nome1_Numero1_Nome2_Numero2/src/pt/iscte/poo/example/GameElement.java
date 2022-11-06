package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile{
	
	public int hitpoints;
	public int damage;
	public Point2D position;

	@Override
	public abstract String getName();

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public abstract int getLayer();

	public abstract void setHitpoints(int value);
}
