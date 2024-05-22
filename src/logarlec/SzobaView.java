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
        //ez az alap amit kirajzol, a szoba padlója és az inventoryk
        Image padlo = Toolkit.getDefaultToolkit().getImage("kepek/szoba_padlo.png");
        g.drawImage(padlo, cd.getX(), cd.getY(), null);
        Image szoba_inventory = Toolkit.getDefaultToolkit().getImage("kepek/szoba_inventory.png");
        g.drawImage(szoba_inventory, 1195, 0, null);
        Image hallgato_inventory = Toolkit.getDefaultToolkit().getImage("kepek/hallgato_iventory.png");
        g.drawImage(hallgato_inventory, 200, 580, null);

        //TODO: kirajzolni az inventorykba a tárgyakat
        //elso kocka a hallgato inventoryjaban kb (300,600) koord, a méretét is ugye állítani kell a tárgyaknak ide, a többi kocka x irányban jobbra haladva
    
        //a szoba inventoryjaban az elso kocka kb (1200,15) koord, ide is állítani kell a méretét a tárgyaknak, a többi kocka y irányban lefelé haladva

    }
    @Override
    public void setTulajdonsag(int width, int height, float transparency) {}
}
