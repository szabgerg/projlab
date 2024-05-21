package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * HallgatoView osztály, amely egy hallgató megjelenítéséért felelős.
 */
public class HallgatoView extends KarakterView{

    // Hallgató fiú és lány képének fájl elérési útjai
    private static final String HALLGATO_IMAGE_PATH_FIU = "kepek/fiu.png";
    private static final String HALLGATO_IMAGE_PATH_LANY = "kepek/lany.png";
    
    // A hallgató képe, amelyet statikusan betöltünk
    private static Image hallgatoImage;

    // Statikus inicializáló blokk a kép betöltéséhez
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

    // A Hallgato modell, amelyet ez a nézet megjelenít
    private Hallgato model;

    // A koordináták, ahol a hallgató megjelenik
    private Coordinates cd;

     /**
     * Alapértelmezett konstruktor, amely üres modellt és koordinátákat állít be.
     */
    public HallgatoView() {
        this.model = null;
        this.cd = new Coordinates(500, 200);
    }

    /**
     * Konstruktor, amely beállítja a modellt és a koordinátákat.
     *
     * @param model A Hallgato modell
     * @param cd A koordináták, ahol a hallgató megjelenik
     */
    public HallgatoView(Hallgato model, Coordinates cd){
        this.model = model;
        this.cd = cd;
    }

     /**
     * Getter a koordinátákhoz.
     *
     * @return A koordináták, ahol a hallgató megjelenik
     */
    public Coordinates getCd() {return cd;}

    /**
     * Setter a koordinátákhoz.
     *
     * @param cd A koordináták, ahol a hallgató megjelenik
     */
    public void setCd(Coordinates cd) {this.cd = cd;}

    /**
     * Védett getter a modellhez.
     *
     * @return A Hallgato modell
     */
    protected Hallgato getModel(){return model;}

    /**
     * Védett setter a modellhez.
     *
     * @param model A Hallgato modell
     */
    protected void setModel(Hallgato model){this.model=model;}

    /**
     * A Graphics objektum segítségével megrajzoljuk a hallgatót.
     *
     * @param g A Graphics objektum, amellyel rajzolunk
     */
    @Override
    public void draw(Graphics g) {
        if (hallgatoImage != null){
            // Ha a kép sikeresen betöltődött, megrajzoljuk
            g.drawImage(hallgatoImage, cd.getX(), cd.getY(),115 , 300, null);
        }
        else{
            // Ha a kép betöltése nem sikerült, egy helykitöltő téglalapot rajzolunk
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Igazítsa a méretet szükség szerint
        }
    }
}
