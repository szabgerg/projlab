package logarlec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Takarito extends Karakter {
	
	/* Takarito ctor */
	public Takarito(Szoba sz, Targyinventory inventory) {
		super(sz, inventory);
		System.out.println("Takarito_letrehozva");
	}
	/*TAkarito mozgasa, letesz, felvesz tragyakat és kiküld embereket a szobákból*/
	public void mozog(Szoba newSzoba){
		if(Proto.getRandVal() > 0){
			if (!newSzoba.beenged() || !jelenlegi.getSzomszedok().contains(newSzoba)){
				System.out.println("Takarito_sikertelen_mozgas");
				return;
			}
			jelenlegi.kilep(this);
			jelenlegi.setLegutobbTakaritva(0);
			newSzoba.getBentlevok().add(this);
			System.out.println("Takarito_hozzaadva_uj_szobahoz");
			jelenlegi = newSzoba;
			System.out.println("Takarito_sikeres_mozgas");

			szellozet();
			kikuld();
		}else {
			double rand = Proto.getRandVal();
			if(rand < 0.5) {
				System.out.println("Takarito_nem_megy");
				random_letesz();
				random_felvesz();
				return;
			}
			int randint = (int) (rand * 10);
			if(randint > jelenlegi.getSzomszedok().size()){
				randint = jelenlegi.getSzomszedok().size()-1;
			}
			newSzoba = jelenlegi.getSzomszedok().get(randint);
			if (!newSzoba.beenged() || !jelenlegi.getSzomszedok().contains(newSzoba)) {
				System.out.println("Takarito_sikertelen_mozgas");
				random_letesz();
				random_felvesz();
				return;
			}
			jelenlegi.kilep(this);
			jelenlegi.setLegutobbTakaritva(0);
			newSzoba.getBentlevok().add(this);
			jelenlegi = newSzoba;
			System.out.println("Takarito_sikeres_mozgas");

			szellozet();
			kikuld();

		}
	}

	/* kiküld függvény, ha belép egy szobába
	 * mindenkit (aki nem béna) egy másik
	 * szobába küld, ha nem sikerül, bent hagyja
	 */
	public void kikuld() {
		Szoba tempSzoba = this.getSzoba();
		List<Karakter> kikuldendok = new ArrayList<>(tempSzoba.getBentlevok()); 
		Random random = new Random();
		int szomszedIndex = random.nextInt(tempSzoba.getSzomszedok().size());
		Szoba celSzoba = tempSzoba.getSzomszedok().get(szomszedIndex); // Véletlenszerűen kiválasztott szomszédos szoba
			
		for(Karakter k : kikuldendok){
			if (k == this) continue;
			if(!(k.bena)){
				k.mozog(celSzoba);
			}
		}
		this.getSzoba().setLegutobbTakaritva(0);
		System.out.println("Takarito_kikuldott");
    }

	/* Szellőztet függvény,
	 * egy gázos szoba hatását semlegesíti
	 */
	public void szellozet(){
		Szoba tempSzoba = this.getSzoba();
		Targyinventory ti = tempSzoba.getAktiv();
		for(ITargy t: ti.getTargyak()){
			if(t instanceof Camambert){
				this.getSzoba().getAktiv().RemoveTargy(t);
			}
		}
		System.out.println("Takarito_szelloztet");
	}

	//ha valódi játék van, akkor az takarito "ai", ez felel a random tárgy felvételért
	void random_felvesz() {
		double rand = Proto.getRandVal();
		if(rand < 0.5 || eszkozkeszlet.getTargyak().size() >= 5 || jelenlegi.getBentiTargyak().getTargyak().isEmpty()) return;
		int randint = (int) (rand * 10);
		if(randint > jelenlegi.getBentiTargyak().getTargyak().size()){
			randint = jelenlegi.getBentiTargyak().getTargyak().size()-1;
		}

		felvesz(randint);
		System.out.println("Takarito_felvett_egy_itemet");
	}
	//ha valódi játék van, akkor az takarito "ai", ez felel a random tárgy letevésért
	void random_letesz() {
		double rand = Proto.getRandVal();
		if(rand < 0.5 || eszkozkeszlet.getTargyak().isEmpty()) return;
		int randint = (int) (rand * 10);
		if(randint > eszkozkeszlet.getTargyak().size()){
			randint = eszkozkeszlet.getTargyak().size()-1;
		}
		letesz(randint);
		System.out.println("Takarito_letett_egy_itemet");

	}
}
