import Karakterek.Hallgato;
import Karakterek.Karakter;
import Karakterek.Oktato;
import Karakterek.Takarito;
import Szoba.Szoba;
import Targyak.*;
import Targyak.Romlandok.*;
import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;

public class Proto {
	private static boolean exit = false;
	private static List<Oktato> oktatok;
	private static List<Hallgato> hallgatok;
	private static List<Takarito> takaritok;
	private static List<Szoba> szobak;
	private static Map<String, Consumer<String[]>> parancsok;

	static {
		parancsok = new HashMap<>();
		//Todo hasmap befejezése

	}

	public static void main(String[] args) {
		ertelmezo(System.in);
	}

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

	private static int pareseInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException n) {
			throw new IllegalArgumentException(s + "\t nem egy integer");
		}
	}

	private static <T> T tryParse(String s, String tipusnev, List<T> objektumok) {
		if (s.length() < 3) throw new IllegalArgumentException(s + "\t túl rövid név egy objektumnak");
		if (!s.substring(0, 2).equals(tipusnev)) return null;

		int idx = pareseInt(s.substring(2)) - 1;
		if (idx < 0 || idx > objektumok.size())
			throw new IllegalArgumentException(s.substring(2) + "nincs benne a lista hosszában, ez 1-től " + objektumok.size() + "-ig tart");
		return objektumok.get(idx);
	}

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
		Szoba sz = new Szoba(inventory, befogadokepesseg);
		szobak.add(sz);
		return sz;
	}


	private static void tesztParancsHossz(String[] tasks, int length) {
		if (tasks.length != length)
			throw new IllegalArgumentException(tasks.length > length ? "Túl sok paraméter" : "Túl kevés paraméter");
	}


	private static void setExit(String[] tasks) {
		tesztParancsHossz(tasks, 1);
		exit = true;
	}

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




}