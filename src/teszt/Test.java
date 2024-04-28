package teszt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	int tesztszam; //az összes teszt számát tárolja
	int hanyadik; //a lefuttandó teszt számát tárolja, ha 0 akkor az összes tesztet lefuttatja
	String filename; //a proto jar file nevét tárolja
	ArrayList<Process> processes;
	String resultfilename;


	public Test(int tesztszam, String fname ,String[] args) {
		this.tesztszam = tesztszam;
		this.filename = fname;
		if (args.length == 0) {
			this.hanyadik = 0;
		} else try {
			this.hanyadik = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Nem számot adott meg");
		}
	}

	public void run() {
		new File("logs").mkdir();
		if (hanyadik < 0 || hanyadik > tesztszam) {
			System.out.println("Nincs ilyen teszt");
			return;
		}
		this.processes = new ArrayList<>();
		if (hanyadik == 0) {
			for (int i = 1; i <= tesztszam; i++) {
				runProto(i);
			}
			for (Process p : processes) {
				try {
					p.waitFor();
				} catch (InterruptedException e) {
					System.out.println("A tesztek futása közben hiba történt");
				}
			}
		} else {
			runProto(hanyadik);
			try {
				processes.get(0).waitFor();
			} catch (InterruptedException e) {
				System.out.println("A tesztek futása közben hiba történt");
			}
		}

		//kimenet bemenet összehasonlítás
		try {
			compare_exp_out();
		} catch (FileNotFoundException e) {
			System.out.println("Nem található egy vagy több teszt fájl");
		} catch (IOException e) {
			System.out.println("Nem sikerült a result fájlt létrehozni");
		}
		System.out.println("A tesztek lefutottak");
	}

	private void compare_exp_out() throws FileNotFoundException, IOException {
		new File("result").mkdir();
		this.resultfilename = "result/result" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")) + ".txt";
		File resultfile = new File(resultfilename);
		FileWriter fw = new FileWriter(resultfile, true);
		fw.write("TEST RESULTS @ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")) + "\n\n");

		if (hanyadik == 0) {
			int passed = 0;
			for (int i = 1; i <= tesztszam; i++) {
				try {
					Scanner exp = new Scanner(new File("exp/exp_" + i + ".txt"));
					Scanner out = new Scanner(new File("logs/out_" + i + ".txt"));
					String result = constaincheck(exp, out);
					fw.write("Test " + i + " ");
					if (result.equals("PASSED")) {
						passed++;
						fw.write("PASSED\n");
					} else {
						fw.write("FAILED\n");
						fw.write("Nem található a logban: " + result + "\n\n");
					}
				} catch (IOException e) {
					System.out.println("Nem található az alábbi sorszámú fájl: " + i);
				}
			}
			fw.write("\n" + tesztszam + " tesztből " + passed + " sikeres\n");
		} else {
			fw.write("Test " + hanyadik + " ");
			try {
				Scanner exp = new Scanner(new File("exp/exp_" + hanyadik + ".txt"));
				Scanner out = new Scanner(new File("logs/out_" + hanyadik + ".txt"));
				String result = constaincheck(exp, out);
				if (result.equals("PASSED")) {
					fw.write("PASSED\n");
				} else {
					fw.write("FAILED\n");
					fw.write("Nem található a logban: " + result + "\n\n");
				}
			} catch (IOException e) {
				System.out.println("Nem található az alábbi sorszámú fájl: " + hanyadik);
			}
		}
		fw.close();
	}

	private String constaincheck(Scanner exp, Scanner out) {
		//read exp and out in a string list
		ArrayList<String> exps = new ArrayList<>();
		ArrayList<String> outs = new ArrayList<>();
		while (exp.hasNextLine()) {
			exps.add((exp.nextLine()).toLowerCase());

		}
		while (out.hasNextLine()) {
			outs.add((out.nextLine()).toLowerCase());
		}
		//check if exp contains out
		for (String s : outs) {
			if (!exps.contains(s)) {
				return s;
			}
		}
		return "PASSED";
	}

	private void runProto(int i) {
		String testinput = "inp/inp_" + i + ".txt";
		String testoutput = "logs/out_" + i + ".txt";

		try {
			Runtime rt = Runtime.getRuntime();
			Process p = rt.exec(new String[]{"cmd", "/c", "java","-jar", filename, "<", testinput, ">", testoutput});
			this.processes.add(p);
		}catch (IOException e) {
			System.out.println("Nem sikerült a fájlt megnyitni");
			System.out.println(testoutput);
		}
	}
}
