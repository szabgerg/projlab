package logarlec;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicMap {
    private List<Drawable> drawableList;
    protected void paint(Graphics g){}
    public void clearDrawable(){}
    public static GraphicMap getMap(){
        return new GraphicMap();
    }
}
