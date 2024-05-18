package logarlec;

import java.util.LinkedList;
import java.util.List;

public class Graf {
	private static List<Karakter> hallgatok = new LinkedList<>();
	private static List<Karakter> ai = new LinkedList<>();
	private static int krktIdx = 0; //a következő karakter indexe

	public static void addHallgato(Karakter k) {
		if (hallgatok == null)
			k.getView().setSoros(true);
		hallgatok.add(k);
	}

	public static void addAI(Karakter k) {
		ai.add(k);
	}

	public static void clearKarakterek() {
		hallgatok.clear();
		ai.clear();
		krktIdx = 0;
	}

	public static boolean vanHallgato() {
		return !hallgatok.isEmpty();
	}

	public static Karakter getAktKarakter() {
		if (krktIdx >= hallgatok.size())
			krktIdx = 0;
		return hallgatok.get(krktIdx++);
	}

	public static void nextKarakter() {
		if (!vanHallgato()) return;
		getAktKarakter().getView().setSoros(false);
		krktIdx++;
		if (krktIdx >= hallgatok.size()){
			krktIdx = 0;
			for (Karakter k : ai)
				k.mozog(null);
		}
		getAktKarakter().getView().setSoros(true);
	}

	void main(String[] args) {
		GraphicMap.getMap().clearDrawable();
	}

}
