package logarlec;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicMap extends JPanel {
    private List<Drawable> drawableList;
    
    public void paint(Graphics g){}
    public void clearDrawable(){}
    public static GraphicMap getMap(){
        return new GraphicMap();
    }

    public void addDrawable(Drawable drawable) {
        drawableList.add(drawable);
    }
}
