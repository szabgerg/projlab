package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A TakaritoView osztály felelős a takarítók megjelenítéséért.
 */
public class TakaritoView extends KarakterView{
    // A takarító képének fájl elérési útja
    private static final String TAKARITO_IMAGE_PATH = "kepek/takarito.png";
    
    // A takarító képe, amelyet statikusan betöltünk
    private static Image takaritoImage;

    // Statikus inicializáló blokk a kép betöltéséhez
    static{
        try{
            takaritoImage = ImageIO.read(new File(TAKARITO_IMAGE_PATH));
        }
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + TAKARITO_IMAGE_PATH);
        }
    }

    // A Takarito modell, amelyet ez a nézet megjelenít
    private Takarito model;
    // A koordináták, ahol a takarító megjelenik
    private Coordinates cd;

    /**
     * Alapértelmezett konstruktor, amely üres modellt és koordinátákat állít be.
     */
    public TakaritoView(){
        this.model = null;
        this.cd = new Coordinates(50,100);
    }

    /**
     * Konstruktor, amely beállítja a modellt és a koordinátákat.
     *
     * @param model A Takarito modell
     * @param cd A koordináták, ahol a takarító megjelenik
     */
    public TakaritoView(Takarito model, Coordinates cd){
        this.model = model;
        this.cd = cd;
    }

    /**
     * Getter a koordinátákhoz.
     *
     * @return A koordináták, ahol a takarító megjelenik
     */
    public Coordinates getCd() {return cd;}

    /**
     * Setter a koordinátákhoz.
     *
     * @param cd A koordináták, ahol a takarító megjelenik
     */
    public void setCd(Coordinates cd) {this.cd = cd;}

    /**
     * Getter a modellhez.
     *
     * @return A Takarito modell
     */
    protected Takarito getModel(){return model;}
    /**
     * Setter a modellhez.
     *
     * @param model A Takarito modell
     */
    protected void setModel(Takarito model){this.model=model;}

    /**
     * A Graphics objektum segítségével megrajzoljuk a takarítót.
     *
     * @param g A Graphics objektum, amellyel rajzolunk
     */
    @Override
    public void draw(Graphics g) {
        if (takaritoImage != null){
            // Ha a kép sikeresen betöltődött, megrajzoljuk
            g.drawImage(takaritoImage, cd.getX(), cd.getY(),170 , 250, null);
        }
        else{
            // Ha a kép betöltése nem sikerült, egy helykitöltő téglalapot rajzolunk
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Igazítsa a méretet szükség szerint
        }
    }
}
