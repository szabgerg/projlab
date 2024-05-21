package logarlec;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * OktatoView osztály, amely egy oktató megjelenítéséért felelős.
 */
public class OktatoView extends KarakterView{

    // Oktató képének fájl elérési útja
    private static final String OKTATO_IMAGE_PATH = "kepek/oktato.png";
    
    // Az Oktato modell, amelyet ez a nézet megjelenít
    private Oktato model;

    // A koordináták, ahol az oktató megjelenik
    private Coordinates cd;

    // Az oktató képe, amelyet statikusan betöltünk
    private static Image oktatoImage;

    // Statikus inicializáló blokk a kép betöltéséhez
    static{
        try{
            oktatoImage = ImageIO.read(new File(OKTATO_IMAGE_PATH));
        }
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load image: " + OKTATO_IMAGE_PATH);
        }
    }


    /**
     * Alapértelmezett konstruktor, amely üres modellt és koordinátákat állít be
     */
    public OktatoView() {
        this.model = null;
        this.cd = new Coordinates(845, 50);
    }

    /**
     * Konstruktor, amely beállítja a modellt és a koordinátákat
     *
     * @param model Az Oktato modell
     * @param cd A koordináták, ahol az oktató megjelenik
     */
    public OktatoView(Oktato model, Coordinates cd){
        this.model = model;
        this.cd = cd;
    }

    /**
     * Getter a koordinátákhoz
     *
     * @return A koordináták, ahol az oktató megjelenik
     */
    public Coordinates getCd() {return cd;}

    /**
     * Setter a koordinátákhoz
     *
     * @param cd A koordináták, ahol az oktató megjelenik
     */
    public void setCd(Coordinates cd) {this.cd = cd;}

    /**
     * Védett getter a modellhez
     *
     * @return Az Oktato modell
     */
    protected Oktato getModel(){return model;}

    /**
     * Védett setter a modellhez
     *
     * @param model Az Oktato modell
     */
    protected void setModel(Oktato model){this.model=model;}


    /**
     * A Graphics objektum segítségével megrajzoljuk az oktatót
     *
     * @param g A Graphics objektum, amellyel rajzolunk
     */
    @Override
    public void draw(Graphics g) {
        if (oktatoImage != null){
            // Ha a kép sikeresen betöltődött, megrajzoljuk
            g.drawImage(oktatoImage, cd.getX(), cd.getY(),115 , 280, null);
        }
        else{
            // Ha a kép betöltése nem sikerült, egy helykitöltő téglalapot rajzolunk
            g.setColor(Color.RED);
            g.fillRect(cd.getX(), cd.getY(), 100, 100); // Igazítsa a méretet szükség szerint
        }}
}
