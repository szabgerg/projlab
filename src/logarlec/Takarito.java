package logarlec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Takarito extends Karakter {
	
	/* Takarito ctor */
	public Takarito(Szoba sz, Targyinventory inventory) {
		super(sz, inventory);
		System.out.println("Takarito_letrehozasa");
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
}
