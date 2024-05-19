package logarlec;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicMap extends JPanel {
    private List<Drawable> drawableList = new ArrayList<>();
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Drawable drawable : drawableList){
            drawable.draw(g);
        }
    }
    public void clearDrawable(){
        drawableList.clear();
        repaint();
    }
    public static GraphicMap getMap(){
        return new GraphicMap();
    }

    public void addDrawable(Drawable drawable) {
        drawableList.add(drawable);
    }
}
