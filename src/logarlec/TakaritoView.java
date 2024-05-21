package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TakaritoView extends KarakterView{
    private static final String TAKARITO_IMAGE_PATH = "kepek/takarito.png";
    
    private static Image takaritoImage;

    static{
        try{
            takaritoImage = ImageIO.read(new File(TAKARITO_IMAGE_PATH));
        }
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + TAKARITO_IMAGE_PATH);
        }
    }

    private Takarito model;
    private Coordinates cd;

    public TakaritoView(){
        this.model = null;
        this.cd = new Coordinates(0,0);
    }

    public TakaritoView(Takarito model, Coordinates cd){
        this.model = model;
        this.cd = cd;
    }
    public Coordinates getCd() {return cd;}

    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Takarito getModel(){return model;}
    protected void setModel(Takarito model){this.model=model;}

    @Override
    public void draw(Graphics g) {
        if (takaritoImage != null){
            // Ha a kép sikeresen betöltődött, megrajzoljuk
            g.drawImage(takaritoImage, cd.getX(), cd.getY(),115 , 250, null);
        }
        else{
            // Ha a kép betöltése nem sikerült, egy helykitöltő téglalapot rajzolunk
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Igazítsa a méretet szükség szerint
        }
    }
}
