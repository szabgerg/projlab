package logarlec;

// A Legfrissito osztály implementálja az ITargy interfészt.
// A Legfrissito tárgy feladata, hogy megszüntesse a Szoba gázosságát, 
// ha olyan Szobában aktiválják, ami gázos, vagyis van az aktivTargyak között Camambert. 
// Ebben a metódusban kikerül az aktivTargyak listájából a Camambert.
public class Legfrissito implements ITargy{

    // A Legfrissito konstruktora, ami kiírja a konzolra, hogy a Legfrissito létrejött.
    public Legfrissito() {
        System.out.println("Le_letrehozva");
    }

    // Az aktival metódus implementálása az ITargy interfészből.
    // Ez a metódus aktiválja a Legfrissitot a megadott Szobában.    
    @Override
    public void aktival(Karakter k) {
        System.out.println("Le_aktivalva");
		// Ha a Szoba gázos (van benne Camambert), akkor eltávolítja a Camambertet az aktivTargyak listából.
        boolean gazos = false;
        for (ITargy t : k.getSzoba().getAktiv().getTargyak()) {
            if (t.gaztalanit(k.getSzoba())) {
                gazos = true;
                System.out.println("Szoba_gazossaga_megszunt");
            }
        }
        // Ha a Szoba nem gázos (nincs benne Camambert), akkor nem történt változás.
        if (!gazos){
            System.out.println("Nem_gazos_szoba");
        }
        k.getEszkozkeszlet().RemoveTargy(this);
    }
    /*
     * A Legfrissito tárgy használata
     * @param k - a karakter, aki használja
     * @return - false, mivel a Legfrissito nem használható
     */
    @Override
    public boolean hasznal(Karakter k) { return false;}

    /*
     * A Legfrissito tárgy szűrése
     * @param k - a karakter, aki használja
     * @return - false, mivel a Legfrissito nem szűr
     */
    @Override
    public boolean szur(Karakter k) { return false;}

    /*
     * A Legfrissito tárgy akciója
     * @param k - a karakter, aki használja
     */
    @Override
    public void akcio(Karakter k) {}
    /*
     * A Legfrissito tárgy gaztalanítása
     * @param szoba - a szoba, ahol a tárgy gaztalanításra kerül
     */
    public boolean gaztalanit(Szoba szoba) {return false;}
    /*
     * A Legfrissito tárgy romlása
     * @return -1, mivel a Legfrissito nem romlik
     */
    @Override
    public int romlik() {return -1;}
}