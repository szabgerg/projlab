package logarlec;

//megvalósítja az ITargy interfészt
public class Camambert implements ITargy{

    /*
     * Camambert konstruktor
     */
    public Camambert() {
        System.out.println("Ca_letrehozva");
    }

    /*
    *A Camambert tárgy aktiválása, a Szobában a tárgy bekerül az aktív tárgyak inventoryba
    */
    @Override
    public void aktival(Karakter k) {
        System.out.println("Ca_aktivalva");
        k.getSzoba().getAktiv().AddTargy(this);
        // A karakterek elejtenek mindent, az oktatók benák lesznek
        for (Karakter karakter : k.getSzoba().getBentlevok()) {
            karakter.setBena(true);
            // Ha a karakter nem tud védekezni, akkor elejti az összes tárgyát
            for (ITargy targy : karakter.getEszkozkeszlet().getTargyak()) {
                if (!targy.szur(karakter)) {
                    karakter.mindentelejt();
                }
            }
        }
    }
    //a legfrissítővel eltávolítják a camambertet, a szoba nem lesz gázos
    public void gaztalanit(Szoba szoba){
        szoba.getAktiv().RemoveTargy(this);
    }

    // Ha egy karakter egy szobába lép, ahol camambert van, akkor a karakter elejt mindent és az oktató bena lesz
    public void akcio(Karakter k){
        System.out.println("Ca_akcio");
        k.setBena(true);
        // Ha a karakter nem tud védekezni, akkor elejti az összes tárgyát
        for (ITargy targy : k.getEszkozkeszlet().getTargyak()) {
            if (!targy.szur(k)) {
                k.mindentelejt();
            }
        }
    }

    /*
     * A Camambert tárgy használata
     * @param k - a karakter, aki használja
     * @return - false, mivel a Camambert nem használható
     */
    @Override
    public boolean hasznal(Karakter k) { return false;}

    /*
     * A Camambert tárgy szűrése
     * @param k - a karakter, aki használja
     * @return - false, mivel a Camambert nem szűr
     */
    @Override
    public boolean szur(Karakter k) { return false;}
}
