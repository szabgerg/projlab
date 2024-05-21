package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LegfrissitoView implements Drawable{
    private static final String LEGFRISSITO_IMAGE_PATH = "kepek/legfrissito.png";
    private static Image legfrissitoImage;

    static {
        try {
            legfrissitoImage = ImageIO.read(new File(LEGFRISSITO_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + LEGFRISSITO_IMAGE_PATH);
        }
    }

    private Legfrissito model;
    private Coordinates cd;

    public LegfrissitoView() {
        this.model = null;
        this.cd = new Coordinates(110, 400);
    }

    public LegfrissitoView(Legfrissito model, Coordinates cd) {
        this.model = model;
        this.cd = cd;
    }

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Legfrissito getModel(){return model;}
    protected void setModel(Legfrissito model){this.model=model;}

    @Override
    public void draw(Graphics g) {
        if (legfrissitoImage != null) {
            g.drawImage(legfrissitoImage, cd.getX(), cd.getY(), 115, 115, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
    }
}
