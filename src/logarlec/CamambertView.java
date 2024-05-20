package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CamambertView implements Drawable{
    private static final String CAMAMBERT_IMAGE_PATH = "kepek/camambert.png";
    private static Image camambertImage;

    static {
        try {
            camambertImage = ImageIO.read(new File(CAMAMBERT_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + CAMAMBERT_IMAGE_PATH);
        }
    }
    private Camambert model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Camambert getModel(){return model;}
    protected void setModel(Camambert model){this.model=model;}

    @Override
    public void draw(Graphics g) {
        if (camambertImage != null) {
            g.drawImage(camambertImage, cd.getX(), cd.getY(), 100, 75, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
    }
}
