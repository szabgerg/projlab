package logarlec;

// A Legfrissito osztály implementálja az ITargy interfészt.
// A Legfrissito tárgy feladata, hogy megszüntesse a Szoba gázosságát, 
// ha olyan Szobában aktiválják, ami gázos, vagyis van az aktivTargyak között Camambert. 
// Ebben a metódusban kikerül az aktivTargyak listájából a Camambert.
public class Legfrissito implements ITargy{

    // A Legfrissito konstruktora, ami kiírja a konzolra, hogy a Legfrissito létrejött.
    public Legfrissito() {
        System.out.println("Legfrissito_letrehozva\n");
    }

    // Az aktival metódus implementálása az ITargy interfészből.
    // Ez a metódus aktiválja a Legfrissitot a megadott Szobában.    
    @Override
    public void aktival(Karakter k) {
		// Ha a Szoba gázos (van benne Camambert), akkor eltávolítja a Camambertet az aktivTargyak listából.
        if (!k.getSzoba().getAktiv().getTargyak().isEmpty()){
            k.getSzoba().getAktiv().RemoveTargy(k.getSzoba().getAktiv().getTargyak().get(0));
            System.out.println("Szoba_gazossaga_megszunt\n");
        }
		// Ha a Szoba nem gázos (nincs benne Camambert), akkor nem történt változás.
        else{
            System.out.println("Nem_gazos_szoba\n");
        }
        System.out.println("Legfrissito_aktivalva\n");
    }
    //TODO: nem tudom melyik kell ide, melyik nem
    @Override
    public boolean hasznal(Karakter k) { return false;}
    @Override
    public boolean szur(Karakter k) { return false;}
    @Override
    public void akcio(Karakter k) {}
}