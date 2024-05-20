package logarlec;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LogarlecView implements Drawable {
    private static final String LOGARLEC_IMAGE_PATH = "kepek/logarlec.png";
    private static Image logarlecImage;

    static {
        try {
            logarlecImage = ImageIO.read(new File(LOGARLEC_IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + LOGARLEC_IMAGE_PATH);
        }
    }

    private Logarlec model;
    private Coordinates cd;

    public LogarlecView() {
        this.model = null;
        this.cd = new Coordinates(0, 0);
    }

    public LogarlecView(Logarlec model, Coordinates cd) {
        this.model = model;
        this.cd = cd;
    }

    public Coordinates getCd() {
        return cd;
    }

    public void setCd(Coordinates cd) {
        this.cd = cd;
    }

    public Logarlec getModel() {
        return model;
    }

    public void setModel(Logarlec model) {
        this.model = model;
    }

    @Override
    public void draw(Graphics g) {
        if (logarlecImage != null) {
            g.drawImage(logarlecImage, cd.getX(), cd.getY(), 150, 75, null);
        } else {
            // Draw a placeholder rectangle if the image failed to load
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
    }
}
