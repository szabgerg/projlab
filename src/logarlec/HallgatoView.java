package logarlec;

import java.awt.*;

public class HallgatoView extends KarakterView{
    private Hallgato model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Hallgato getModel(){return model;}
    protected void setModel(Hallgato model){this.model=model;}
    @Override
    public void draw(Graphics g) {}
}
