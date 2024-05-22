package logarlec;

import java.awt.*;

public class SzobaView implements Drawable{
    private Szoba model;
    private Coordinates cd;
    public SzobaView(){
        this.model = null;
        this.cd = new Coordinates(0,0);
    }
    public SzobaView(Szoba model, Coordinates cd){
        this.model = model;
        this.cd = cd;
    }
    public Coordinates getCd() {return cd;}
    public void setCd(Coordinates cd) {this.cd = cd;}

    protected Szoba getModel(){return model;}
    protected void setModel(Szoba model){this.model=model;}
    @Override
    public void draw(Graphics g) {
        // Alap padló
        Image padlo = Toolkit.getDefaultToolkit().getImage("kepek/szoba_padlo.png");
        if (Graf.getAktKarakter().getModel().getSzoba().getRagacsos()) {
            padlo = Toolkit.getDefaultToolkit().getImage("kepek/szoba_padlo_ragacsos.png");
        }

        // Ha a model és az aktiv attribútum nem null, és van tárgy az aktivban
        if (Graf.getAktKarakter().getModel().getSzoba().getAktiv() != null
             && !Graf.getAktKarakter().getModel().getSzoba().getAktiv().getTargyak().isEmpty()) {
            for (ITargy t : Graf.getAktKarakter().getModel().getSzoba().getAktiv().getTargyak()) {
                // Ha a tárgy neve "Camambert", akkor a padló képe gázos lesz
                if (t.getClass().getSimpleName().equals("Camambert")) {
                    padlo = Toolkit.getDefaultToolkit().getImage("kepek/szoba_padlo_gazos.png");
                    break; // Ha találtunk egy camambert, nincs szükség további ellenőrzésekre
                }
            }
        }

        // Padló kirajzolása
        g.drawImage(padlo, cd.getX(), cd.getY(), null);

        // Szoba és hallgató inventoryk kirajzolása
        Image szoba_inventory = Toolkit.getDefaultToolkit().getImage("kepek/szoba_inventory.png");
        g.drawImage(szoba_inventory, 1195, 0, null);
        Image hallgato_inventory = Toolkit.getDefaultToolkit().getImage("kepek/hallgato_iventory.png");
        g.drawImage(hallgato_inventory, 200, 580, null);

        // Ajtók kirajzolása

        Image ajto = Toolkit.getDefaultToolkit().getImage("kepek/ajto.png");
        int ablakSzelesseg = 1200; // Ablak szélessége
        int ajtoSzelesseg = 50; // Az ajtó szélessége (kép szélességétől függően)
        int ajtokSzama = getModel().getSzomszedok().size();
        int tavolsag = (ablakSzelesseg - (ajtokSzama * ajtoSzelesseg)) / (ajtokSzama + 1); // Ajtók közötti távolság

        for (int i = 0; i < ajtokSzama; i++) {
            int x = tavolsag + i * (ajtoSzelesseg + tavolsag); // Ajtó x koordinátája
            g.drawImage(ajto, x, cd.getY(), ajtoSzelesseg, 100, null); // Ajtó kirajzolása
        }

    }

    @Override
    public void setTulajdonsag(int width, int height, float transparency) {}
}
