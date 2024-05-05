package logarlec;

import java.awt.*;

public class TranzisztorView implements Drawable{
    private Tranzisztor model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Tranzisztor getModel(){return model;}
    protected void setModel(Tranzisztor model){this.model=model;}
    @Override
    public void draw(Graphics g) {

    }
}
