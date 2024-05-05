package logarlec;

import java.awt.*;

public class MaszkView implements Drawable{
    private Maszk model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Maszk getModel(){return model;}
    protected void setModel(Maszk model){this.model=model;}
    @Override
    public void draw(Graphics g) {

    }
}
