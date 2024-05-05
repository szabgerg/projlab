package logarlec;

import java.awt.*;

public class RongyView implements Drawable{
    private Rongy model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Rongy getModel(){return model;}
    protected void setModel(Rongy model){this.model=model;}
    @Override
    public void draw(Graphics g) {

    }
}
