package teszt;

public class Main {
	static int TESZTEKSZAMA = 29;
	static String JARFILENAME = "log.jar";

	public static void main(String[] args) {
		if(args.length == 0 || args.length == 1) {
			Test test = new Test(TESZTEKSZAMA, JARFILENAME, args);
			test.run();
		}else {
			System.out.println("Hibás paraméterszám!");
		}
	}

}