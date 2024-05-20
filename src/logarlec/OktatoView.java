package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OktatoView extends KarakterView{
    private static final String OKTATO_IMAGE_PATH = "kepek/oktato.png";
    private Oktato model;
    private Coordinates cd;
    private static Image oktatoImage;

    static{
        try{
            oktatoImage = ImageIO.read(new File(OKTATO_IMAGE_PATH));
        }
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + OKTATO_IMAGE_PATH);
        }
    }

    public OktatoView() {
        this.model = null;
        this.cd = new Coordinates(0, 0);
    }

    public OktatoView(Oktato model, Coordinates cd){
        this.model = model;
        this.cd = cd;
    }

    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Oktato getModel(){return model;}
    protected void setModel(Oktato model){this.model=model;}

    @Override
    public void draw(Graphics g) {
        if (oktatoImage != null){
            g.drawImage(oktatoImage, cd.getX(), cd.getY(),115 , 300, null);
        }
        else{
            // Draw a placeholder rectangle if the image failed to load
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Adjust the size as needed
        }}
}
