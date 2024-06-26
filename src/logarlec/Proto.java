package logarlec;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class Proto {
	//tárolja, hogy érkezett-e exit parancs
	private static boolean exit = false;
	//tárolja az oktatókat
	private static List<Oktato> oktatok = new ArrayList<>();
	//tárolja a hallgatókat
	private static List<Hallgato> hallgatok = new ArrayList<>();
	//tárolja a takarítókat
	private static List<Takarito> takaritok = new ArrayList<>();
	//tárolja a szobákat
	private static List<Szoba> szobak = new ArrayList<>();
	//ha -1 akkor igazi random, ha 0-1 közötti érték akkor az lesz a random érték
	//ha kisebb mint 0,5 akkor false, ha nagyobb akkor true
	//ha int kell akkor meg kell szorozni 10-zel
	private static double randVal = -1;
	//ha igazi random kellene
	private static Random rand = new Random();

	public static void main(String[] args) {
		ertelmezo(System.in);
	}

	public static List<Szoba> getSzobak() {
		return szobak;
	}

	//parancsok mapje, hibákat dob, ha nem jó a parancs
	private static Map<String, Consumer<String[]>> parancsok;
	static {
		parancsok = new HashMap<>();
		parancsok.put("exit", Proto::setExit);
		parancsok.put("add", Proto::add);
		parancsok.put("clear", Proto::clear);
		parancsok.put("load", Proto::load);
		parancsok.put("random", Proto::random);
		parancsok.put("give", Proto::give);
		parancsok.put("move", Proto::move);
		parancsok.put("place", Proto::place);
		parancsok.put("pickup", Proto::pickup);
		parancsok.put("activate", Proto::activate);
		parancsok.put("pairing", Proto::pairing);
		parancsok.put("neighbours", Proto::neighbours);
		parancsok.put("merge", Proto::merge);
		parancsok.put("split", Proto::split);
	}
	//ebből kap a program random értéket
	public static double getRandVal() {
		if (randVal < 0) {
			return rand.nextDouble(0,1);
		}else {
			return randVal;
		}
	}

	public static Szoba getRandSzoba() {
		if (szobak.isEmpty()) throw new RuntimeException("Nincs szoba");
		return szobak.get(rand.nextInt(szobak.size()));
	}

	//feldolgozza az is-ben jövő parancsokat, rekurzívan hívhatja magát a load miatt
	protected static void ertelmezo(InputStream is) {
		Scanner sc = new Scanner(is);

		while (!exit && sc.hasNextLine()) {
			String[] tasks = sc.nextLine().split(" ");
			if (tasks.length == 1 && tasks[0].isEmpty()) continue;

			if (parancsok.containsKey(tasks[0])) {
				try {
					parancsok.get(tasks[0]).accept(tasks);
				} catch (Exception e) {
					System.out.println("Érvénytelen parancs: " + e.getMessage());
				}
			}
		}
		sc.close();
	}
	//intet csinál a stringből
	private static int pareseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException n) {
			throw new IllegalArgumentException(s + "\t nem egy integer");
		}
	}
	//boolt csinál a stringből
	private static boolean parseBool(String s) {
		if (s.equals("I") || s.equals("true")) return true;
		if (s.equals("N") || s.equals("false")) return false;
		throw new IllegalArgumentException(s + "\t nem egy boolean");
	}
	//névből objektumot csinál
	private static <T> T tryParse(String s, String tipusnev, List<T> objektumok) {
		if (s.length() < 3) throw new IllegalArgumentException(s + "\t túl rövid név egy objektumnak");
		if (!s.substring(0, 2).equals(tipusnev)) return null;

		int idx = pareseInt(s.substring(2)) - 1;
		if (idx < 0 || idx > objektumok.size())
			throw new IllegalArgumentException(s.substring(2) + "nincs benne a lista hosszában, ez 1-től " + objektumok.size() + "-ig tart");
		return objektumok.get(idx);
	}
	/*
	* névből csinálunk objektumot, a tryparse felhasználásával
	* egyszerűbb parancsokban ezt hívni
	* */
	private static Oktato parseOktato(String s) {
		return tryParse(s, "ok", oktatok);
	}

	private static Hallgato parseHallgato(String s) {
		return tryParse(s, "ha", hallgatok);
	}

	private static Takarito parseTakarito(String s) {
		return tryParse(s, "ta", takaritok);
	}

	private static Szoba parseSzoba(String s) {
		return tryParse(s, "sz", szobak);
	}

	private static ITargy parseTargy(String s) {
		if (s.length() < 2) throw new IllegalArgumentException(s + "\t túl rövid név egy objektumnak");
		String targy = s.substring(0,2);
		if("tv".equals(targy)){
			return newTvsz();
		}
		else if("so".equals(targy)){
			return newSorospohar();
		}
		else if("tr".equals(targy)){
			return newTranzisztor();
		}
		else if("ro".equals(targy)){
			return newRongy();
		}
		else if("ca".equals(targy)){
			return newCamambert();
		}
		else if("ma".equals(targy)){
			return newMaszk();
		}
		else if("le".equals(targy)){
			return newLegfrissito();
		}
		else if("lo".equals(targy)){
			return newLogarlec();
		}
		throw new IllegalArgumentException(s + "\tnem létező tárgy");
	}

	public static Tvsz newTvsz() {
		Tvsz t = new Tvsz();
		TvszView tv = new TvszView();
		tv.setCd(new Coordinates(1050,100)); // ez lesz a szobában a helye
		tv.setModel(t);
		GraphicMap.getMap().addDrawable(tv);
		return t;
	}

	public static Sorospohar newSorospohar() {
		Sorospohar s = new Sorospohar();
		SorospoharView sv = new SorospoharView();
		sv.setCd(new Coordinates(1060,330)); // ez lesz a szobában a helye
		sv.setModel(s);
		GraphicMap.getMap().addDrawable(sv);
		return s;
	}

	public static Tranzisztor newTranzisztor() {
		Tranzisztor t = new Tranzisztor();
		TranzisztorView tv = new TranzisztorView();
		tv.setCd(new Coordinates(870,470)); // ez lesz a szobában a helye
		tv.setModel(t);
		GraphicMap.getMap().addDrawable(tv);
		return t;
	}

	public static Rongy newRongy() {
		Rongy r = new Rongy();
		RongyView rv = new RongyView();
		rv.setCd(new Coordinates(730,300)); // ez lesz a szobában a helye
		rv.setModel(r);
		GraphicMap.getMap().addDrawable(rv);
		return r;
	}

	public static Camambert newCamambert() {
		Camambert c = new Camambert();
		CamambertView cv = new CamambertView();
		cv.setCd(new Coordinates(470,50)); // ez lesz a szobában a helye
		cv.setModel(c);
		GraphicMap.getMap().addDrawable(cv);
		return c;
	}

	public static Maszk newMaszk() {
		Maszk m = new Maszk();
		MaszkView mv = new MaszkView();
		mv.setCd(new Coordinates(250,270)); // ez lesz a szobában a helye
		mv.setModel(m);
		GraphicMap.getMap().addDrawable(mv);
		return m;
	}

	public static Legfrissito newLegfrissito() {
		Legfrissito l = new Legfrissito();
		LegfrissitoView lv = new LegfrissitoView();
		lv.setCd(new Coordinates(110,400)); // ez lesz a szobában a helye
		lv.setModel(l);
		GraphicMap.getMap().addDrawable(lv);
		return l;
	}

	public static Logarlec newLogarlec() {
		Logarlec l = new Logarlec();
		LogarlecView lv = new LogarlecView();
		lv.setCd(new Coordinates(80,110)); // ez lesz a szobában a helye
		lv.setModel(l);
		GraphicMap.getMap().addDrawable(lv);
		return l;
	}




	/*
	* új objektumokat létrehozó függvények, a modell is ezeket használja, hogy a nyilvántartásban is benne legyenek
	* */
	public static Oktato newOktato(Szoba sz, Targyinventory inventory) {
		Oktato o = new Oktato(sz, inventory);
		OktatoView ov = new OktatoView();
		List<Karakter> szb = sz.getBentlevok();
		szb.add(o);
		sz.setBentlevok(szb);
		ov.setCd(new Coordinates(845,50));
		ov.setModel(o);
		Graf.addAI(ov);
		GraphicMap.getMap().addDrawable(ov);
		oktatok.add(o);
		return o;
	}

	public static Hallgato newHallgato(Szoba sz, Targyinventory inventory) {
		Hallgato h = new Hallgato(sz, inventory);
		HallgatoView hv = new HallgatoView();
		List<Karakter> szb = sz.getBentlevok();
		szb.add(h);
		sz.setBentlevok(szb);
		hv.setCd(new Coordinates(500,200));
		hv.setModel(h);
		Graf.addHallgato(hv);
		GraphicMap.getMap().addDrawable(hv);
		hallgatok.add(h);
		return h;
	}

	public static Takarito newTakarito(Szoba sz, Targyinventory inventory) {
		Takarito t = new Takarito(sz, inventory);
		TakaritoView tv = new TakaritoView();
		List<Karakter> szb = sz.getBentlevok();
		szb.add(t);
		sz.setBentlevok(szb);
		tv.setCd(new Coordinates(200,300));
		tv.setModel(t);
		Graf.addAI(tv);
		GraphicMap.getMap().addDrawable(tv);
		takaritok.add(t);
		return t;
	}

	private static Szoba newSzoba(Targyinventory inventory, int befogadokepesseg) {
		Szoba sz = new Szoba(befogadokepesseg, inventory);
		SzobaView sv = new SzobaView();
		sv.setModel(sz);
		GraphicMap.getMap().addDrawable(sv);
		szobak.add(sz);
		return sz;
	}

	//ellenőrzi, hogy a parancsoknak megfelelő hosszúak-e a paraméterek
	private static void tesztParancsHossz(String[] tasks, int length) {
		if (tasks.length != length)
			throw new IllegalArgumentException(tasks.length > length ? "Túl sok paraméter" : "Túl kevés paraméter");
	}

	//ha jön exit parancs, itt állítja be
	private static void setExit(String[] tasks) {
		tesztParancsHossz(tasks, 1);
		exit = true;
	}
	//az add parancsot hajtja végre
	private static void add(String[] tasks) {
		if (tasks.length < 3) throw new IllegalArgumentException("Túl kevés paraméter");

		String tipus = tasks[1];
		Targyinventory inventory = new Targyinventory();
		switch (tipus) {
			case "ok":
				if (tasks.length > 8) throw new IllegalArgumentException("Túl sok paraméter");
				if (tasks.length > 2) {
					for (int i = 3; i < tasks.length; i++) {
						inventory.AddTargy(parseTargy(tasks[i]));
					}
				}
				newOktato(parseSzoba(tasks[2]), inventory);
				break;
			case "ha":
				if (tasks.length > 8) throw new IllegalArgumentException("Túl sok paraméter");
				if (tasks.length > 3) {
					for (int i = 3; i < tasks.length; i++) {
						inventory.AddTargy(parseTargy(tasks[i]));
					}
				}
				newHallgato(parseSzoba(tasks[2]), inventory);
				break;
			case "ta":
				if (tasks.length > 8) throw new IllegalArgumentException("Túl sok paraméter");
				if (tasks.length > 2) {
					for (int i = 3; i < tasks.length; i++) {
						inventory.AddTargy(parseTargy(tasks[i]));
					}
				}
				newTakarito(parseSzoba(tasks[2]), inventory);
				break;
			case "sz":
				if(tasks.length < 3) throw new IllegalArgumentException("Túl kevés paraméter");
				if(tasks.length == 3){
					if(szobak.isEmpty()){
						Szoba sz1 = newSzoba(new Targyinventory(), pareseInt(tasks[2]));
						Szoba sz2 = newSzoba(new Targyinventory(), pareseInt(tasks[2]));
						sz1.addSzomszed(sz2);
						sz2.addSzomszed(sz1);

					} else {
						Szoba sz = newSzoba(new Targyinventory(), pareseInt(tasks[2]));
						sz.addSzomszed(szobak.get(szobak.size()-2));
						szobak.get(szobak.size()-2).addSzomszed(sz);
					}
					
				} else if(tasks.length == 4){
					Szoba sz = newSzoba(new Targyinventory(), pareseInt(tasks[2]));
					sz.setSzomszedok(new ArrayList<Szoba>(Arrays.asList(parseSzoba(tasks[3]))));
					List<Szoba> szomszed = parseSzoba(tasks[3]).getSzomszedok();
					szomszed.add(sz);
					parseSzoba(tasks[3]).setSzomszedok(szomszed);
				}

				break;
			default:
				throw new IllegalArgumentException("Nem létező típus: " + tipus);
		}
	}
	//clear parancsot hajtja végre
	private static void clear(String[] tasks){
		tesztParancsHossz(tasks, 1);
		oktatok.clear();
		hallgatok.clear();
		takaritok.clear();
		szobak.clear();
		randVal = -1;
	}
	//load parancsot hajtja végre
	private static void load(String[] tasks){
		tesztParancsHossz(tasks, 2);
		try {
			ertelmezo(new FileInputStream(tasks[1]));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Nem található a fájl: " + tasks[1]);
		}
	}
	//random parancsot hajtja végre
	private static void random(String[] tasks){
		tesztParancsHossz(tasks, 2);
		if (tasks.length == 1){
			randVal = -1;
			return;
		}

		double rnd = -1;
		try {
			rnd = Double.parseDouble(tasks[1]);
			if(rnd < 0 || rnd > 1) throw new IllegalArgumentException(tasks[1] + "\t nem egy 0 és 1 közötti double");
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(tasks[1] + "\t nem egy double");
		}

		randVal = rnd;

	}
	//give parancsot hajtja végre
	private static void give(String[] tasks) {
		tesztParancsHossz(tasks, 3);
		String tipus = tasks[1].substring(0, 2); // Az első két karaktert az azonosító
		switch (tipus) {
			case "ok":
				Oktato o = parseOktato(tasks[1]);
				if(o.getEszkozkeszlet().getTargyak().size() >= 5) throw new IllegalArgumentException("Az oktató eszközkészlete tele van");
				o.getEszkozkeszlet().AddTargy(parseTargy(tasks[2]));
				break;
			case "ha":
				Hallgato h = parseHallgato(tasks[1]);
				if(h.getEszkozkeszlet().getTargyak().size() >= 5) throw new IllegalArgumentException("A hallgató eszközkészlete tele van");
				h.getEszkozkeszlet().AddTargy(parseTargy(tasks[2]));
				break;
			case "ta":
				Takarito t = parseTakarito(tasks[1]);
				if(t.getEszkozkeszlet().getTargyak().size() >= 5) throw new IllegalArgumentException("A takarító eszközkészlete tele van");
				t.getEszkozkeszlet().AddTargy(parseTargy(tasks[2]));
				break;
			case "sz":
				Szoba sz = parseSzoba(tasks[1]);
				sz.getBentiTargyak().AddTargy(parseTargy(tasks[2]));
				break;

		}
	}
	//move parancsot hajtja végre
	private static void move(String[] tasks){
		tesztParancsHossz(tasks, 3);
		String tipus = tasks[1].substring(0, 2);
		Szoba sz = parseSzoba(tasks[2]);
		switch (tipus) {
			case "ok":
				Oktato o = parseOktato(tasks[1]);
				o.mozog(sz);
				break;
			case "ha":
				Hallgato h = parseHallgato(tasks[1]);
				h.mozog(sz);
				break;
			case "ta":
				Takarito t = parseTakarito(tasks[1]);
				t.mozog(sz);
				break;
		}
	}
	//place parancsot hajtja végre
	private static void place(String[] tasks){
		tesztParancsHossz(tasks, 3);
		String tipus = tasks[1].substring(0, 2);
		int mit = pareseInt(tasks[2]);
		switch (tipus) {
			case "ok":
				Oktato o = parseOktato(tasks[1]);
				o.letesz(mit);
				break;
			case "ha":
				Hallgato h = parseHallgato(tasks[1]);
				h.letesz(mit);
				break;
			case "ta":
				Takarito t = parseTakarito(tasks[1]);
				t.letesz(mit);
				break;
		}
	}
	//pickup parancsot hajtja végre
	private static void pickup(String[] tasks){
		tesztParancsHossz(tasks, 3);
		String tipus = tasks[1].substring(0, 2);
		int mit = pareseInt(tasks[2]);
		switch (tipus) {
			case "ok":
				Oktato o = parseOktato(tasks[1]);
				o.felvesz(mit);
				break;
			case "ha":
				Hallgato h = parseHallgato(tasks[1]);
				h.felvesz(mit);
				break;
			case "ta":
				Takarito t = parseTakarito(tasks[1]);
				t.felvesz(mit);
				break;
		}
	}
	//activate parancsot hajtja végre
	private static void activate(String[] tasks) {
		tesztParancsHossz(tasks, 3);
		String tipus = tasks[1].substring(0, 2);
		int mit = pareseInt(tasks[2]);
		if (!tipus.equals("ha")) throw new IllegalArgumentException("Csak hallgató tudja aktiválni a tárgyakat");
		Hallgato h = parseHallgato(tasks[1]);
		h.aktival(mit);
	}
	//pairing parancsot hajtja végre
	private static void pairing(String[] tasks){
		tesztParancsHossz(tasks, 4);
		Hallgato h = parseHallgato(tasks[1]);
		h.osszekapcsol(pareseInt(tasks[2]), pareseInt(tasks[3]));
	}


	//neighbours parancsot hajtja végre
	private static void neighbours(String[] tasks) {
		tesztParancsHossz(tasks, 4);
		Szoba sz1 = parseSzoba(tasks[1]);
		Szoba sz2 = parseSzoba(tasks[2]);
		if(parseBool(tasks[3])) {
			if(!sz1.getSzomszedok().contains(sz2)) {
				List<Szoba> szomszedok = sz1.getSzomszedok();
				szomszedok.add(sz2);
				sz1.setSzomszedok(szomszedok);
				szomszedok = sz2.getSzomszedok();
				szomszedok.add(sz1);
				sz2.setSzomszedok(szomszedok);
			}else {
				if(sz1.getSzomszedok().size() == 1) {
					throw new IllegalArgumentException("A szoba csak egy szomszédos szobával rendelkezik");
				}
				if(sz2.getSzomszedok().size() == 1) {
					throw new IllegalArgumentException("A szoba csak egy szomszédos szobával rendelkezik");
				}

				List<Szoba> szomszedok = sz1.getSzomszedok();
				szomszedok.remove(sz2);
				sz1.setSzomszedok(szomszedok);
				szomszedok = sz2.getSzomszedok();
				szomszedok.remove(sz1);
				sz2.setSzomszedok(szomszedok);
			}
		} else {
			if(sz1.getSzomszedok().contains(sz2)) {
				if(sz1.getSzomszedok().size() == 1) {
					throw new IllegalArgumentException("A szoba csak egy szomszédos szobával rendelkezik");
				}else {
					List<Szoba> szomszedok = sz1.getSzomszedok();
					szomszedok.remove(sz2);
					sz1.setSzomszedok(szomszedok);
				}
			}else {
				List<Szoba> szomszedok = sz1.getSzomszedok();
				szomszedok.add(sz2);
				sz1.setSzomszedok(szomszedok);
			}
		}
	}
	//merge parancsot hajtja végre
	private static void merge(String[] tasks) {
		tesztParancsHossz(tasks, 3);
		Szoba sz1 = parseSzoba(tasks[1]);
		Szoba sz2 = parseSzoba(tasks[2]);
		sz1.merge(sz2);
	}
	//split parancsot hajtja végre
	private static void split(String[] tasks) {
		tesztParancsHossz(tasks, 2);
		Szoba sz1 = parseSzoba(tasks[1]);
		sz1.split();
	}

}