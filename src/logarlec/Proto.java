package logarlec;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class Proto {
	//tárolja, hogy érkezett-e exit parancs
	private static boolean exit = false;
	//tárolja az oktatókat
	private static List<Oktato> oktatok;
	//tárolja a hallgatókat
	private static List<Hallgato> hallgatok;
	//tárolja a takarítókat
	private static List<Takarito> takaritok;
	//tárolja a szobákat
	private static List<Szoba> szobak;
	//ha -1 akkor igazi random, ha 0-1 közötti érték akkor az lesz a random érték
	//ha kisebb mint 0,5 akkor false, ha nagyobb akkor true
	//ha int kell akkor meg kell szorozni 10-zel
	private static double randVal = -1;
	//ha igazi random kellene
	private static Random rand = new Random();

	public static void main(String[] args) {
		ertelmezo(System.in);
	}

	public static void logger(String s) {//logolás, ha fut teszt akkor a teszt számával jelölt out-ba, különben a run_log.txt-be
		System.out.println(s);
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
		parancsok.put("teleport", Proto::teleport);
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
	//feldolgozza az is-ben jövő parancsokat, rekurzívan hívhatja magát a load miatt
	private static void ertelmezo(InputStream is) {
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
		if (s.length() < 3) throw new IllegalArgumentException(s + "\t túl rövid név egy objektumnak");
		return switch (s.substring(0, 2)) {
			case "tv" -> new Tvsz();
			case "so" -> new Sorospohar();
			case "tr" -> new Tranzisztor();
			case "ro" -> new Rongy();
			case "ca" -> new Camambert();
			case "ma" -> new Maszk();
			case "le" -> new Legfrissito();
			default -> throw new IllegalArgumentException(s + "\t nem létező tárgy");
		};

	}
	/*
	* új objektumokat létrehozó függvények, a modell is ezeket használja, hogy a nyilvántartásban is benne legyenek
	* */
	private static Oktato newOktato(Szoba sz, Targyinventory inventory) {
		Oktato o = new Oktato(sz, inventory);
		List<Karakter> szb = sz.getBentlevok();
		szb.add(o);
		sz.setBentlevok(szb);
		oktatok.add(o);
		return o;
	}

	private static Hallgato newHallgato(Szoba sz, Targyinventory inventory) {
		Hallgato h = new Hallgato(sz, inventory);
		List<Karakter> szb = sz.getBentlevok();
		szb.add(h);
		sz.setBentlevok(szb);
		hallgatok.add(h);
		return h;
	}

	private static Takarito newTakarito(Szoba sz, Targyinventory inventory) {
		Takarito t = new Takarito(sz, inventory);
		List<Karakter> szb = sz.getBentlevok();
		szb.add(t);
		sz.setBentlevok(szb);
		takaritok.add(t);
		return t;
	}

	private static Szoba newSzoba(Targyinventory inventory, int befogadokepesseg) {
		Szoba sz = new Szoba( befogadokepesseg, inventory);
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
		if (tasks.length < 2) throw new IllegalArgumentException("Túl kevés paraméter");

		String tipus = tasks[1];
		Targyinventory inventory = new Targyinventory();
		switch (tipus) {
			case "ok":
				if (tasks.length > 8) throw new IllegalArgumentException("Túl sok paraméter");
				if (tasks.length > 2) {
					for (int i = 2; i < tasks.length; i++) {
						inventory.AddTargy(parseTargy(tasks[i]));
					}
				}
				newOktato(parseSzoba(tasks[2]), inventory);
				break;
			case "ha":
				if (tasks.length > 8) throw new IllegalArgumentException("Túl sok paraméter");
				if (tasks.length > 2) {
					for (int i = 2; i < tasks.length; i++) {
						inventory.AddTargy(parseTargy(tasks[i]));
					}
				}
				newHallgato(parseSzoba(tasks[2]), inventory);
				break;
			case "ta":
				if (tasks.length > 8) throw new IllegalArgumentException("Túl sok paraméter");
				if (tasks.length > 2) {
					for (int i = 2; i < tasks.length; i++) {
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
						sz1.setSzomszedok(new ArrayList<Szoba>(Arrays.asList(sz2)));
						sz2.setSzomszedok(new ArrayList<Szoba>(Arrays.asList(sz1)));

					} else {
						Szoba sz = newSzoba(new Targyinventory(), pareseInt(tasks[2]));
						sz.setSzomszedok(new ArrayList<Szoba>(Arrays.asList(szobak.get(szobak.size()-1))));
						List<Szoba> szomszed = szobak.get(szobak.size()-1).getSzomszedok();
						szomszed.add(sz);
						szobak.get(szobak.size()-1).setSzomszedok(szomszed);
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
		String tipus = tasks[1];
		Targyinventory i;
		switch (tipus) {
			case "ok":
				Oktato o = parseOktato(tasks[2]);
				if(o.getEszkozkeszlet().getTargyak().size() >= 5) throw new IllegalArgumentException("Az oktató eszközkészlete tele van");
				i = o.getEszkozkeszlet();
				i.AddTargy(parseTargy(tasks[3]));
				o.setEszkozkeszlet(i);
				break;
			case "ha":
				Hallgato h = parseHallgato(tasks[2]);
				if(h.getEszkozkeszlet().getTargyak().size() >= 5) throw new IllegalArgumentException("A hallgató eszközkészlete tele van");
				i = h.getEszkozkeszlet();
				i.AddTargy(parseTargy(tasks[3]));
				h.setEszkozkeszlet(i);
				break;
			case "ta":
				Takarito t = parseTakarito(tasks[2]);
				if(t.getEszkozkeszlet().getTargyak().size() >= 5) throw new IllegalArgumentException("A takarító eszközkészlete tele van");
				i = t.getEszkozkeszlet();
				i.AddTargy(parseTargy(tasks[3]));
				t.setEszkozkeszlet(i);
				break;
			case "sz":
				Szoba sz = parseSzoba(tasks[2]);
				i = sz.getBentiTargyak();
				i.AddTargy(parseTargy(tasks[3]));
				sz.setBentiTargyak(i);
				break;

		}
	}
	//move parancsot hajtja végre
	private static void move(String[] tasks){
		tesztParancsHossz(tasks, 3);
		String tipus = tasks[1];
		Szoba sz = parseSzoba(tasks[2]);
		switch (tipus) {
			case "ok":
				Oktato o = parseOktato(tasks[3]);
				o.mozog(sz);
				break;
			case "ha":
				Hallgato h = parseHallgato(tasks[3]);
				h.mozog(sz);
				break;
			case "ta":
				Takarito t = parseTakarito(tasks[3]);
				t.mozog(sz);
				break;
		}
	}
	//place parancsot hajtja végre
	private static void place(String[] tasks){
		tesztParancsHossz(tasks, 3);
		String tipus = tasks[1];
		int mit = pareseInt(tasks[3]);
		switch (tipus) {
			case "ok":
				Oktato o = parseOktato(tasks[2]);
				o.letesz(mit);
				break;
			case "ha":
				Hallgato h = parseHallgato(tasks[2]);
				h.letesz(mit);
				break;
			case "ta":
				Takarito t = parseTakarito(tasks[2]);
				t.letesz(mit);
				break;
		}
	}
	//pickup parancsot hajtja végre
	private static void pickup(String[] tasks){
		tesztParancsHossz(tasks, 3);
		String tipus = tasks[1];
		int mit = pareseInt(tasks[3]);
		switch (tipus) {
			case "ok":
				Oktato o = parseOktato(tasks[2]);
				o.felvesz(mit);
				break;
			case "ha":
				Hallgato h = parseHallgato(tasks[2]);
				h.felvesz(mit);
				break;
			case "ta":
				Takarito t = parseTakarito(tasks[2]);
				t.felvesz(mit);
				break;
		}
	}
	//activate parancsot hajtja végre
	private static void activate(String[] tasks) {
		tesztParancsHossz(tasks, 3);
		String tipus = tasks[1];
		int mit = pareseInt(tasks[3]);
		if (!tipus.equals("ha")) throw new IllegalArgumentException("Csak hallgató tudja aktiválni a tárgyakat");
		Hallgato h = parseHallgato(tasks[2]);
		h.aktival(mit);
	}
	//pairing parancsot hajtja végre
	private static void pairing(String[] tasks){
		tesztParancsHossz(tasks, 4);
		Hallgato h = parseHallgato(tasks[1]);
		h.osszekapcsol(pareseInt(tasks[2]), pareseInt(tasks[3]));
	}
	//teleport parancsot hajtja végre
	private static void teleport(String [] tasks) {
		tesztParancsHossz(tasks, 3);
		Hallgato h = parseHallgato(tasks[1]);
		h.teleport(parseSzoba(tasks[2]));
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