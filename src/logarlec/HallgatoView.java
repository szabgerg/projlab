package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class HallgatoView extends KarakterView{
    private static final String HALLGATO_IMAGE_PATH_FIU = "kepek/fiu.png";
    private static final String HALLGATO_IMAGE_PATH_LANY = "kepek/lany.png";
    private static Image hallgatoImage;

    static{
        try{
	        Random rand = new Random();
            int charDecider = rand.nextInt(10);
            if (charDecider % 2 == 0){
                hallgatoImage = ImageIO.read(new File(HALLGATO_IMAGE_PATH_LANY));
            }
            else{
                hallgatoImage = ImageIO.read(new File(HALLGATO_IMAGE_PATH_FIU));
            }
        }
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + HALLGATO_IMAGE_PATH_LANY);
        }
    }
    private Hallgato model;
    private Coordinates cd;

    public HallgatoView() {
        this.model = null;
        this.cd = new Coordinates(0, 0);
    }

    public HallgatoView(Hallgato model, Coordinates cd){
        this.model = model;
        this.cd = cd;
    }

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Hallgato getModel(){return model;}
    protected void setModel(Hallgato model){this.model=model;}

    @Override
    public void draw(Graphics g) {
        if (hallgatoImage != null){
            g.drawImage(hallgatoImage, cd.getX(), cd.getY(),115 , 300, null);
        }
        else{
            // Draw a placeholder rectangle if the image failed to load
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }
    }
}
