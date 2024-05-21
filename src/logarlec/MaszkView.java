package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MaszkView implements Drawable{
    private static final String MASZK_IMAGE_PATH = "kepek/maszk.png";
    private static Image maszkImage;

    static {
        try {
            maszkImage = ImageIO.read(new File(MASZK_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + MASZK_IMAGE_PATH);
        }
    }
    private Maszk model;
    private Coordinates cd;

    
    public MaszkView() {
        this.model = null;
        this.cd = new Coordinates(250, 270);
    }


    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Maszk getModel(){return model;}
    protected void setModel(Maszk model){this.model=model;}
    @Override
    public void draw(Graphics g) {
        if (maszkImage != null) {
            g.drawImage(maszkImage, cd.getX(), cd.getY(), 130, 75, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
    }
}
