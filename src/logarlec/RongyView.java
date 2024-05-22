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
    private int width = 100;
    private int height = 75;
    private float transparency = 1.0f;

    public RongyView() {
        this.model = null;
        this.cd = new Coordinates(730, 300);
    }

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Rongy getModel(){return model;}
    protected void setModel(Rongy model){this.model=model;}
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        if (rongyImage != null) {
            g2d.drawImage(rongyImage, cd.getX(), cd.getY(), width, height, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g2d.setColor(Color.RED);
            g2d.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
        g2d.dispose();
    }

    @Override
    public void setTulajdonsag(int width, int height, float transparency) {
        Dimension scaledSize = getScaledDimension(new Dimension(rongyImage.getWidth(null), rongyImage.getHeight(null)), new Dimension(width, height));
        this.width = scaledSize.width;
        this.height = scaledSize.height;
        this.transparency = transparency;
    }
    
    // Új metódus az arányos méretezéshez
    private Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        int originalWidth = imgSize.width;
        int originalHeight = imgSize.height;
        int boundWidth = boundary.width;
        int boundHeight = boundary.height;
        int newWidth = originalWidth;
        int newHeight = originalHeight;

        // Check if scaling is necessary
        if (originalWidth > boundWidth || originalHeight > boundHeight) {
            // Calculate new dimensions
            float aspectRatio = (float) originalWidth / (float) originalHeight;
            if ((float) boundWidth / (float) boundHeight > aspectRatio) {
                newHeight = boundHeight;
                newWidth = Math.round(boundHeight * aspectRatio);
            } else {
                newWidth = boundWidth;
                newHeight = Math.round(boundWidth / aspectRatio);
            }
        }

        int x = cd.getX() + (width - rongyImage.getWidth(null)) / 2;
        int y = cd.getY() + (height - rongyImage.getHeight(null)) / 2;
        //setCd(new Coordinates(x, y));
        return new Dimension(newWidth, newHeight);
    }
}
