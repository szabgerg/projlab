package logarlec;

import java.awt.*;

public class SzobaView implements Drawable{
    private Szoba model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Szoba getModel(){return model;}
    protected void setModel(Szoba model){this.model=model;}
    @Override
    public void draw(Graphics g) {}
}
