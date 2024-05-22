package logarlec;

import java.awt.*;

public interface Drawable {
    public void draw (Graphics g);
    public void setCd(Coordinates cd);
    void setTulajdonsag(int width, int height, float transparency);
}
