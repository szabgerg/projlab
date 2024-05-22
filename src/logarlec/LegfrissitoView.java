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
    private int width = 115;
    private int height = 115;
    private float transparency;

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
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        if (legfrissitoImage != null) {
            g2d.drawImage(legfrissitoImage, cd.getX(), cd.getY(), width, height, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g2d.setColor(Color.RED);
            g2d.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
        g2d.dispose();
    }

    @Override
    public void setTulajdonsag(int width, int height, float transparency) {
        Dimension scaledSize = getScaledDimension(new Dimension(legfrissitoImage.getWidth(null), legfrissitoImage.getHeight(null)), new Dimension(width, height));
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
