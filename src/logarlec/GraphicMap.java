package logarlec;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicMap extends JPanel {
    private static GraphicMap instance = new GraphicMap(); // Singleton példány
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
        return instance;
    }

    public void addDrawable(Drawable drawable) {
        drawableList.add(drawable);
    }
}
