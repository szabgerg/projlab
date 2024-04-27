package logarlec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Takarito extends Karakter {
	
	/* Takarito ctor */
	public Takarito(Szoba sz, Targyinventory inventory) {
		super(sz, inventory);
	}

	/* kiküld függvény, ha belép egy szobába
	 * mindenkit (aki nem béna) egy másik
	 * szobába küld
	 */
	public void kikuld() {
		Szoba tempSzoba = this.getSzoba();
		List<Karakter> kikuldendok = new ArrayList<>(tempSzoba.getBentlevok()); 
		Random random = new Random();
		int szomszedIndex = random.nextInt(tempSzoba.getSzomszedok().size());
		Szoba celSzoba = tempSzoba.getSzomszedok().get(szomszedIndex); // Véletlenszerűen kiválasztott szomszédos szoba
			
		for(Karakter k : kikuldendok){
			if(!(((Oktato)k).getBena())){
				k.mozog(celSzoba);
				this.getSzoba().getBentlevok().remove(k);
			}
		}
		this.getSzoba().setLegutobbTakaritva(0);
    }

	/* Szellőztet függvény,
	 * egy gázos szoba hatását semlegesíti
	 */
	public void szellozet(){
		Szoba tempSzoba = this.getSzoba();
		Targyinventory ti = tempSzoba.getAktiv();
		for(ITargy t: ti.targyak){
			if(t instanceof Camambert){
				this.getSzoba().getAktiv().RemoveTargy(t);
			}
		}
	}
}