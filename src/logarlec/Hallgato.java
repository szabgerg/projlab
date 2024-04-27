package logarlec;

import java.util.Scanner;
/*
* Hallgato osztaly ami a jatekban szereplo hallgatokat reprezentalja
* A Hallgato osztaly a Karakter osztalybol szarmazik
* A Hallgato osztalyban a hallgato mozgasat, vedelmet, teleportalasat, es targyak aktivalasat valositjuk meg
 */
public class Hallgato extends Karakter {
	/*
	* Hallgato konstruktora
	* @param szoba - a szoba, ahol a hallgato tartozkodik
	* @param inventory - a hallgato targykeszlete
	*
	 */
	public Hallgato(Szoba szoba, Targyinventory inventory) {
		super(szoba, inventory);
		System.out.println("Hallgato_letrehozasa\n");
		System.out.println("Hallgato_eszkozkeszlete_letrehozva\n");
		System.out.println("Hallgato_szobaba_teve\n");
	}
	/*
	* Az hallgato mozgasat megvalosito metodus
	* @param newSzoba - az uj szoba, ahova a hallgato mozog
	* A metodusban a hallgato mozgasat valositjuk meg, es a szobaban levo aktiv
	* targyakat is vizsgaljuk
	* Ha a szoba gazos, akkor a hallgato maszkot hasznal
	* Ha a hallgatonak nincs maszkja, akkor elejt mindent
	 */
	@Override
	public void mozog(Szoba newSzoba) {
		System.out.println("Hallgato_mozogni_probal");
		if(!newSzoba.beenged()){
			System.out.print("Nincs_hely");
			System.out.print("Sikertelen_mozgas");
			return;}
		System.out.print("Van_hely");
		jelenlegi.kilep(this);
		newSzoba.getBentlevok().add(this);
		System.out.println("Uj_szobaba_mozgas");
		jelenlegi = newSzoba;

		System.out.print("Sikeres_mozgas");
		if(newSzoba.getAktiv().getTargyak().isEmpty()) return;


	}
	/*
	* Az hallgato targyak aktivalasat megvalosito metodus
	* A metodusban a hallgato targyak aktivalasat valositjuk meg
	* A hallgato kivalaszthat egy targyat, amit aktivalni szeretne
	* A hallgato valaszthat a kovetkezo targyak kozul:
	* 1. Tranzisztor
	* 2. Rongy
	* 3. Camambert
	* 4. Logarlec
	 */
	public void aktival(int hely) {
		Szoba jelenlegi = new Szoba();
		System.out.println("Hallgato_aktival_egy_targyat\n");
		System.out.println("A_targyak_amiket_magadnal_viselsz:\n" +
				"1. Tranzisztor\n" +
				"2. Rongy\n" +
				"3. Camambert\n" +
				"4. Logarlec\n" +
				"Melyik targyat szeretned aktivalni? (n)\n");
		Scanner scanner = new Scanner(System.in);
		int choice;
		do{
			choice = scanner.nextInt();
			switch (choice) {
				case 1:
					System.out.println("Tranzisztor_aktivalva\n");
					Tranzisztor tranzisztor = new Tranzisztor();
					tranzisztor.aktival(this);
					jelenlegi.setBentiTargyak(null);
					break;
				case 2:
					System.out.println("Rongy_aktivalva\n");
					Rongy rongy = new Rongy();
					rongy.aktival(this);
					jelenlegi.setBentiTargyak(null);
					break;
				case 3:
					System.out.println("Camambert_aktivalva\n");
					Camambert camambert = new Camambert();
					camambert.aktival(this);
					jelenlegi.setBentiTargyak(null);
					break;
				case 4:
					System.out.println("Logarlec_aktivalva\n");
					Logarlec logarlec = new Logarlec();
					logarlec.aktival(this);
					break;
				default:
					System.out.println("Nincs_ilyen_targy\n");
					break;
			}
		} while (!((choice > 0) && (choice < 5)));
		System.out.println("A_kivalasztott_targy_aktivalva\n");
		scanner.close();
	}
	/*
	* A metodusban a hallgato lelekelvetel elleni vedelmet valositjuk meg
	* Ha a hallgato rendelkezik tvsszel, akkor sikeresen vedekezik
	* Ha a hallgato nem rendelkezik tvsszel, akkor megvizsgaljuk, hogy rendelkezik-e sorospoharral
	* Ha a hallgato rendelkezik sorospoharral, akkor sikeresen vedekezik
	* Ha a hallgato nem rendelkezik sorospoharral, akkor meghal
	* @return - a hallgato sikeresen vedekezett-e
	 */

	public boolean vedekezes() {
		Targyinventory eszkozkeszlet = new Targyinventory();
		Szoba jelenlegi = new Szoba();
		System.out.println("Hallgato_vedekezik\n");
		System.out.println("Van Tvsz-e? (I/N)\n");
		Scanner scanner = new Scanner(System.in);
		String choice;
		do {
			choice = scanner.nextLine();
			if (choice.equals("I")) {
				Tvsz tvsz = new Tvsz();
				tvsz.aktival(null);
				System.out.println("A_hallgato_sikeresen_vedekezik\n");
				tvsz.romlik();
				System.out.println("Mennyi maradt meg a felhasznalasi idejebol? (n)\n");
				int left = scanner.nextInt();
				if (left == 1) {
					eszkozkeszlet.RemoveTargy(null);
				}
				scanner.close();
				return true;
			} else if (choice.equals("N")) {//söröspohár
				System.out.println("A_hallgato_nem_rendelkezik_tvsszel\n");
				System.out.println("A hallgatonak van sorospohara? (I/N)\n");
				String sors;
				do{
					sors = scanner.nextLine();
					if (sors.equals("I")) {
						Sorospohar sorsospohar = new Sorospohar();
						sorsospohar.aktival(this);
						System.out.println("A hallgato sikeresen vedekezik\n");
						sorsospohar.romlik();
						System.out.println("Mennyi maradt meg a felhasznalasi idejebol? (n)\n");
						int left = scanner.nextInt();
						if (left == 1) {
							eszkozkeszlet.RemoveTargy(null);
						}
						scanner.close();
						return true;
					} else if (sors.equals("N")) {
						System.out.println("A hallgato nem rendelkezik sorospoharral\n");
						System.out.println("A hallgato meghal\n");
						scanner.close();
						return false;
					} else {
						System.out.println("Nem ertelmezett valasz\n");
					}
				} while (!(sors.equals("I") || sors.equals("N")));
			} else {
				System.out.println("Nem ertelmezett valasz\n");
			}
		} while (!(choice.equals("I") || choice.equals("N")));
	return false;
	}

	/*
	* A metodusban a hallgato teleportalasat valositjuk meg
	* A hallgato teleportalasahoz szukseges ket tranzisztor
	* Ha ossze vannak kapcsolva a tranzisztorok, akkor a hallgato teleportal
	 */
	public void teleport(int hely) {
		System.out.println("Hallgato teleportal\n");
		Tranzisztor t1 = new Tranzisztor();
		t1.getPar();
		Tranzisztor t2 = new Tranzisztor();
		System.out.println("A tranzisztorok ossze vannak kapcsolva? (I/N)\n");
		Scanner scanner = new Scanner(System.in);
		String choice;
		do {
			choice = scanner.nextLine();
			if (choice.equals("I")) {
				System.out.println("A tranzisztorok ossze vannak kapcsolva\n");
				mozog(t2.getSzoba());
				t1.kikapcsol();
				t2.kikapcsol();

			} else if (choice.equals("N")) {
				System.out.println("A tranzisztorok nincsenek ossze kapcsolva\n");
				System.out.println("A hallgato nem tud teleportalni\n");
			} else {
				System.out.println("Nem ertelmezett valasz\n");
			}
		} while (!(choice.equals("I") || choice.equals("N")));
		scanner.close();
	}
	/*
	* A metodusban a hallgato tranzisztorok osszekapcsolasat valositjuk meg
	* Egy tranzisztor egyszerre csak egy masikkal kapcsolhato ossze
	*
	 */
	public void osszekapcsol(int mit, int mivel) {
		System.out.println("Transzisztorok osszekapcsolasa\n");
		Tranzisztor t1 = new Tranzisztor();
		boolean szabad1 = t1.canPair();
		Tranzisztor t2 = new Tranzisztor();
		boolean szabad2 = t2.canPair();
		if (szabad1 && szabad2) {
			t1.setPar(t2);
			t2.setPar(t1);
			System.out.println("A tranzisztorok osszekapcsolva\n");
		}else {
			System.out.println("A tranzisztorok nem kapcsolhatoak ossze\n");
		}
	}

	/*
	* A metodusban a hallgato szobajat allitjuk be
	* @param szoba - a beallitando szoba
	 */
	public void setSzoba(Szoba szoba) {
		System.out.println("Hallgato szoba atallitva\n");
	}
}
