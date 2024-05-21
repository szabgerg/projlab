package logarlec;

import java.awt.*;

public class KarakterView implements Drawable{

	private boolean soros = false;
	private Karakter model;

	public void setSoros(boolean b) { soros = b; }

	protected Karakter getModel() { return model; }

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setCd(Coordinates cd) {}
}
  