package logarlec;

import java.awt.*;

public class TvszView implements Drawable{
    private Tvsz model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Tvsz getModel(){return model;}
    protected void setModel(Tvsz model){this.model=model;}
    @Override
    public void draw(Graphics g) {

    }
}
