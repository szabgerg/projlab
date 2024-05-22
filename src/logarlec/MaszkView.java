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
    private int width = 130;
    private int height = 75;
    private float transparency = 1.0f;

    
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
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        if (maszkImage != null) {
            g2d.drawImage(maszkImage, cd.getX(), cd.getY(), width, height, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g2d.setColor(Color.RED);
            g2d.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
        g2d.dispose();
    }

    @Override
    public void setTulajdonsag(int width, int height, float transparency) {
        Dimension scaledSize = getScaledDimension(new Dimension(maszkImage.getWidth(null), maszkImage.getHeight(null)), new Dimension(width, height));
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
        int x = cd.getX() + (45 - newWidth) / 2;
        int y = cd.getY() + (45 - newHeight) / 2;
        setCd(new Coordinates(x, y));
        return new Dimension(newWidth, newHeight);
    }
}
