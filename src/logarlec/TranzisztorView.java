package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TranzisztorView implements Drawable{
    private static final String TR_IMAGE_PATH = "kepek/tranzisztor.png";
    private static Image trImage;

    static {
        try {
            trImage = ImageIO.read(new File(TR_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + TR_IMAGE_PATH);
        }
    }
    private Tranzisztor model;
    private Coordinates cd;

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Tranzisztor getModel(){return model;}
    protected void setModel(Tranzisztor model){this.model=model;}
    @Override
    public void draw(Graphics g) {
        if (trImage != null) {
            g.drawImage(trImage, cd.getX(), cd.getY(), 100, 100, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
    }
}
