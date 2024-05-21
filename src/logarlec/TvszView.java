package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TvszView implements Drawable{
    private static final String TVSZ_IMAGE_PATH = "kepek/tvsz.png";
    private static Image tvszImage;

    static {
        try {
            tvszImage = ImageIO.read(new File(TVSZ_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + TVSZ_IMAGE_PATH);
        }
    }
    private Tvsz model;
    private Coordinates cd;
    
    public TvszView() {
        this.model = null;
        this.cd = new Coordinates(1050, 100);
    }

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Tvsz getModel(){return model;}
    protected void setModel(Tvsz model){this.model=model;}
    @Override
    public void draw(Graphics g) {
        if (tvszImage != null) {
            g.drawImage(tvszImage, cd.getX(), cd.getY(), 100, 75, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
    }
}
