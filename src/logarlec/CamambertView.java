package logarlec;

import java.awt.*;

public class CamambertView implements Drawable{
    private Camambert model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Camambert getModel(){return model;}
    protected void setModel(Camambert model){this.model=model;}

    @Override
    public void draw(Graphics g) {

    }
}
