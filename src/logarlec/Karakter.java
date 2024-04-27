package logarlec;

/*
 * Karakter osztály ami a játékban szereplő karaktereket reprezentálja
 * A karaktereknek van egy szobájuk ahol tartózkodnak és egy eszközkeszletük
 * A karakterek tudnak mozogni, tárgyakat felvenni, letenni és mindent elejteni
 * Ősosztálya a hallgató és az oktató osztálynak
 * */
public class Karakter {
	// Karakter protected attributumai
	protected Targyinventory eszkozkeszlet;
	protected Szoba jelenlegi;
	protected boolean bena = false;
	/*
	 * Szoba amiben a karakter tartózkodik
	 * Nem lehet null, amig a karakter a játékban van
	 *
	 */
	/* Karakter konstruktora
	 * @param szoba - a szoba, ahol a karakter tartózkodik
	 * @param inventory - a karakter eszközkeszlete
	 */
	public Karakter(Szoba szoba, Targyinventory inventory) {
		jelenlegi = szoba;
		eszkozkeszlet = inventory;
	}

	/*
	 * A karakter mozgását megvalósító metódus
	 * @param newSzoba - az új szoba, ahova a karakter mozog
	 * A metódusban a karakter mozgását valósítjuk meg
	 *
	 */
	public void mozog(Szoba newSzoba) {
		if(newSzoba.beenged()){
			jelenlegi.getBentlevok().remove(this);
			jelenlegi = newSzoba;
			newSzoba.getBentlevok().add(this);
		}
	}

	/*
	 * A metódusban a karakter tárgyfelvételét valósítjuk meg
	 * Ha a karakternek nincs helye a tárgyaknak, akkor nem tud felvenni
	 */
	public void felvesz(int i) {
		if(eszkozkeszlet.targyak.size() < 5){
			eszkozkeszlet.AddTargy(jelenlegi.getBentiTargyak().targyak.get(i));
		}
	}

	/*
	 * A metódusban a karakter tárgyletevését valósítjuk meg
	 * Ha a karakternek nincs tárgya, akkor nem tud letenni
	 * Amit letett, az a szobában lesz, mas karakterek felvehetik
	 */
	public void letesz(int i) {
		jelenlegi.getBentiTargyak().AddTargy(eszkozkeszlet.targyak.get(i));
		eszkozkeszlet.targyak.remove(i);
	}

	/*
	 * A karakter melyik szobában tartózkodik
	 */
	public Szoba getSzoba() {
		return jelenlegi;
	}

	/*
	 * A karakter minden tárgyát eldobó metódus
	 */
	public void mindentelejt() {
		for (ITargy t: jelenlegi.getBentiTargyak().targyak){
			jelenlegi.getBentiTargyak().AddTargy(t);
		}
		eszkozkeszlet.targyak.clear();
	}
	public void setBena(boolean bena) {

	}

	public boolean getBena() {
		return bena;
	}
	/*
	 * A karakter eszközkeszletét visszaadó metódus
	 */
	public Targyinventory getEszkozkeszlet() {
		return eszkozkeszlet;
	}

	/*
	 * A karakter eszközkeszletét beállító metódus
	 */
	public void setEszkozkeszlet(Targyinventory eszkozok) {
		eszkozkeszlet = eszkozok;
	}

	public boolean vedekezes() {
		return true;
	}


}
