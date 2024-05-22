package logarlec;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GraphicMap extends JPanel {
    private static GraphicMap instance = new GraphicMap(); // Singleton példány
    private List<Drawable> drawableList = new ArrayList<>();
    private Map<Class<? extends ITargy>, Function<ITargy, Drawable>> drawableFactoryMap = new HashMap<>();
    private Map<Class<? extends Karakter>, Function<Karakter, Drawable>> drawableFactoryMapKarakter = new HashMap<>();
    
    public GraphicMap() {
        drawableFactoryMap.put(Camambert.class, camambert -> new CamambertView());
        drawableFactoryMap.put(Legfrissito.class, legfrissito -> new LegfrissitoView());
        drawableFactoryMap.put(Logarlec.class, logarlec -> new LogarlecView());
        drawableFactoryMap.put(Maszk.class, maszk -> new MaszkView());
        drawableFactoryMap.put(Rongy.class, rongy -> new RongyView());
        drawableFactoryMap.put(Sorospohar.class, sorospohar -> new SorospoharView());
        drawableFactoryMap.put(Tranzisztor.class, tranzisztor -> new TranzisztorView());
        drawableFactoryMap.put(Tvsz.class, tvsz -> new TvszView());
        drawableFactoryMapKarakter.put(Oktato.class, oktato -> new OktatoView());
        drawableFactoryMapKarakter.put(Hallgato.class, hallgato -> new HallgatoView());
        drawableFactoryMapKarakter.put(Takarito.class, takarito -> new TakaritoView());
    }

    @Override
    public void paintComponent(Graphics g){
        List<Drawable> aktDrawableList = new ArrayList<>();
        List<ITargy> targyak = Graf.getAktKarakter().getModel().getSzoba().getBentiTargyak().getTargyak();
        List<Karakter> karakterek = Graf.getAktKarakter().getModel().getSzoba().getBentlevok();
        List<ITargy> hTargyak = Graf.getAktKarakter().getModel().getEszkozkeszlet().getTargyak();
        
        // Végigiterálunk a tárgyak listáján és létrehozzuk a megfelelő view-t
        for (ITargy targy : targyak) {
            Function<ITargy, Drawable> factory = drawableFactoryMap.get(targy.getClass());
            if (factory != null) {
                Drawable drawable = factory.apply(targy);
                aktDrawableList.add(drawable);
            } else {
                System.out.println("Nincs megjelenítés az alábbi tárgy típusra: " + targy.getClass().getSimpleName());
            }
        }

        // Karakterek hozzáadása az aktDrawableList-hez
        for (Karakter karakter : karakterek) {
            Function<Karakter, Drawable> factory = drawableFactoryMapKarakter.get(karakter.getClass());
            if (factory != null) {
                Drawable drawable = factory.apply(karakter);
                aktDrawableList.add(drawable);
            } else {
                System.out.println("Nincs megjelenítés az alábbi karakter típusra: " + karakter.getClass().getSimpleName());
            }
        }

        // Végigiterálunk a hordozott tárgyak listáján és létrehozzuk a megfelelő view-t
        int i = 0;
        for (ITargy targy : hTargyak) {
            Function<ITargy, Drawable> factory = drawableFactoryMap.get(targy.getClass());
            if (factory != null) {
                Drawable drawable = factory.apply(targy);
                drawable.setCd(new Coordinates(312+(i*142), 605));
                drawable.setTulajdonsag(40, 40, 1.0f);
                aktDrawableList.add(drawable);
                i++;
            } else {
                System.out.println("Nincs megjelenítés az alábbi tárgy típusra: " + targy.getClass().getSimpleName());
            }
        }

        i = 0;
        for (Class<? extends ITargy> targyClass : drawableFactoryMap.keySet()) {
            boolean contains = false;
            for (ITargy targy : targyak) {
                if (targyClass.isInstance(targy)) {
                    contains = true;
                    break;
                }
            }
            Function<ITargy, Drawable> factory = drawableFactoryMap.get(targyClass);
            if (factory != null) {
                ITargy exampleTargy = null;
                for (ITargy targy : targyak) {
                    if (targyClass.isInstance(targy)) {
                        exampleTargy = targy;
                        break;
                    }
                }
                Drawable drawable = factory.apply(exampleTargy);
                drawable.setCd(new Coordinates(1210, 31+(i*83)));
                if (!contains) {
                    drawable.setTulajdonsag(40, 40, 0.3f); // Halványan rajzoljuk ki
                } else {
                    drawable.setTulajdonsag(40,40, 1.0f); // Normálisan rajzoljuk ki
                }
                aktDrawableList.add(drawable);
            } else {
                System.out.println("Nincs megjelenítés az alábbi tárgy típusra: " + targyClass.getSimpleName());
            }
            i++;
        }

        super.paintComponent(g);
        new SzobaView().draw(g);
        for(Drawable drawable : aktDrawableList) {
            drawable.draw(g);
        }
    }
    public void clearDrawable(){
        drawableList.clear();
        repaint();
    }
    public static GraphicMap getMap(){
        return instance;
    }

    public void addDrawable(Drawable drawable) {
        drawableList.add(drawable);
    }
}
