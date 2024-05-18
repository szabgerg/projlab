package logarlec;

import java.awt.*;

public class OktatoView extends KarakterView{
    private Oktato model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Oktato getModel(){return model;}
    protected void setModel(Oktato model){this.model=model;}
    @Override
    public void draw(Graphics g) {}
}
