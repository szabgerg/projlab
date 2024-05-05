package logarlec;

import java.awt.*;

public class LegfrissitoView implements Drawable{
    private Legfrissito model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Legfrissito getModel(){return model;}
    protected void setModel(Legfrissito model){this.model=model;}
    @Override
    public void draw(Graphics g) {

    }
}
