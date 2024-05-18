package logarlec;

import java.awt.*;

public class TakaritoView extends KarakterView{
    private Takarito model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Takarito getModel(){return model;}
    protected void setModel(Takarito model){this.model=model;}
    @Override
    public void draw(Graphics g) {}
}
