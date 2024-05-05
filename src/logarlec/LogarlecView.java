package logarlec;

import java.awt.*;

public class LogarlecView implements Drawable{
    private Logarlec model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Logarlec getModel(){return model;}
    protected void setModel(Logarlec model){this.model=model;}
    @Override
    public void draw(Graphics g) {

    }
}
