package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RongyView implements Drawable{
    private static final String RONGY_IMAGE_PATH = "kepek/rongy.png";
    private static Image rongyImage;

    static {
        try {
            rongyImage = ImageIO.read(new File(RONGY_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + RONGY_IMAGE_PATH);
        }
    }
    private Rongy model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Rongy getModel(){return model;}
    protected void setModel(Rongy model){this.model=model;}
    @Override
    public void draw(Graphics g) {
        if (rongyImage != null) {
            g.drawImage(rongyImage, cd.getX(), cd.getY(), 100, 75, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
    }
}
