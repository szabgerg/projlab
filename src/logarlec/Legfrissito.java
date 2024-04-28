package logarlec;

// A Legfrissito osztály implementálja az ITargy interfészt.
// A Legfrissito tárgy feladata, hogy megszüntesse a Szoba gázosságát, 
// ha olyan Szobában aktiválják, ami gázos, vagyis van az aktivTargyak között Camambert. 
// Ebben a metódusban kikerül az aktivTargyak listájából a Camambert.
public class Legfrissito implements ITargy{

    // A Legfrissito konstruktora, ami kiírja a konzolra, hogy a Legfrissito létrejött.
    public Legfrissito() {
        System.out.println("Le_letrehozva\n");
    }

    // Az aktival metódus implementálása az ITargy interfészből.
    // Ez a metódus aktiválja a Legfrissitot a megadott Szobában.    
    @Override
    public void aktival(Karakter k) {
        System.out.println("Le_aktivalva\n");
		// Ha a Szoba gázos (van benne Camambert), akkor eltávolítja a Camambertet az aktivTargyak listából.
        if (!k.getSzoba().getAktiv().getTargyak().isEmpty()){
            k.getSzoba().getAktiv().RemoveTargy(k.getSzoba().getAktiv().getTargyak().get(0));
            System.out.println("Szoba_gazossaga_megszunt\n");
        }
		// Ha a Szoba nem gázos (nincs benne Camambert), akkor nem történt változás.
        else{
            System.out.println("Nem_gazos_szoba\n");
        }
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
}