package logarlec;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Graf {
	private static List<KarakterView> hallgatok = new LinkedList<>();
	private static List<KarakterView> ai = new LinkedList<>();
	private static int krktIdx = 0; //a következő karakter indexe

	public static void addHallgato(KarakterView k) {
		if (hallgatok == null)
			k.setSoros(true);
		hallgatok.add(k);
	}

	public static void addAI(KarakterView k) {
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

	public static KarakterView getAktKarakter() {
		if (krktIdx >= hallgatok.size())
			krktIdx = 0;
		return hallgatok.get(krktIdx);
	}

	public static void nextKarakter() {
		if (!vanHallgato()) return;
		getAktKarakter().setSoros(false);
		krktIdx++;
		if (krktIdx >= hallgatok.size()){
			krktIdx = 0;
			if(!ai.isEmpty()){
				for (KarakterView k : ai){
					System.out.println("Eskü mozgott");
					//k.getModel().mozog(null);
				}
			}
		}
		getAktKarakter().setSoros(true);
	}

	public static void main(String[] args) {
		GraphicMap.getMap().clearDrawable();

		try {
			new Fomenu();
		}catch (IOException e) {
			System.out.println("Hiba a főmenü betöltésekor" + e.getMessage());
		}


		//valamennyire szálbiztos
		try {
			FileInputStream fis = new FileInputStream("kepek/terkep.txt");
			SwingUtilities.invokeLater(() -> Proto.ertelmezo(fis));
			//proto bezárja a fájlt
		}catch (IOException e) {
			System.out.println("Hiba a térkép betöltésekor" + e.getMessage());
		}
	}

	public static void startGameUi(){
		JFrame frame = new JFrame("Labirintus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setResizable(false);
		Container panel = frame.getContentPane();
		panel.add(GraphicMap.getMap());
		frame.setVisible(true);
		GraphicMap.getMap().grabFocus();

		InputManager inp = new InputManager();
		GraphicMap.getMap().addKeyListener(inp);

		new Timer(17, e -> panel.repaint()).start();
		new Timer(5000, e -> nextKarakter()).start();

	}

}
