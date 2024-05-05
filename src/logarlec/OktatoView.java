package logarlec;

import java.awt.*;

public class OktatoView implements Drawable{
    private Oktato model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Oktato getModel(){return model;}
    protected void setModel(Oktato model){this.model=model;}
    @Override
    public void draw(Graphics g) {}
}
