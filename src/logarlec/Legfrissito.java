package logarlec;

// A Legfrissito osztály implementálja az ITargy interfészt.
// A Legfrissito tárgy feladata, hogy megszüntesse a Szoba gázosságát, 
// ha olyan Szobában aktiválják, ami gázos, vagyis van az aktivTargyak között Camambert. 
// Ebben a metódusban kikerül az aktivTargyak listájából a Camambert.
public class Legfrissito implements ITargy{

    // A Legfrissito konstruktora, ami kiírja a konzolra, hogy a Legfrissito létrejött.
    public Legfrissito() {
        System.out.println("Legfrissito létrehozva");
    }

    // Az aktival metódus implementálása az ITargy interfészből.
    // Ez a metódus aktiválja a Legfrissitot a megadott Szobában.    
    @Override
    public void aktival(Karakter k) {
        System.out.println("Legfrissito aktiválva");
		
		// Ha a Szoba gázos (van benne Camambert), akkor eltávolítja a Camambertet az aktivTargyak listából.
        if (!s.getAktiv().getTargyak().isEmpty()){
            s.getAktiv().RemoveTargy(s.getAktiv().getTargyak().get(0));
            System.out.println("Szoba gázossága megszűnt");
        }
		// Ha a Szoba nem gázos (nincs benne Camambert), akkor nem történt változás.
        else{
            System.out.println("Nem gázos a szoba, nem történt változás");
        }
    }
}