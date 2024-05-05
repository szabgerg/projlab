package logarlec;

import java.awt.*;

public class SorospoharView implements Drawable{
    private Sorospohar model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Sorospohar getModel(){return model;}
    protected void setModel(Sorospohar model){this.model=model;}
    @Override
    public void draw(Graphics g) {

    }
}
