package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SorospoharView implements Drawable{
    private static final String SO_IMAGE_PATH = "kepek/sorospohar.png";
    private static Image sorImage;

    static {
        try {
            sorImage = ImageIO.read(new File(SO_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + SO_IMAGE_PATH);
        }
    }
    private Sorospohar model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Sorospohar getModel(){return model;}
    protected void setModel(Sorospohar model){this.model=model;}
    @Override
    public void draw(Graphics g) {
        if (sorImage != null) {
            g.drawImage(sorImage, cd.getX(), cd.getY(), 75, 75, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
    }
}
