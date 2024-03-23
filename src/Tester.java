import java.util.Scanner;

public class Tester {
    public Tester() {
        System.out.println("Tester létrejött");
    }

    public void startMenu(){
    Scanner scanner = new Scanner(System.in);

    System.out.println("1. Játék elindítása(Init)\n" +
                   "2. Sikeres tárgyfelvétel\n" +
                   "3. Sikertelen tárgyfelvétel\n" +
                   "4. Tárgy letevése\n" +
                   "5. Sikeres karakter mozgás szobák között\n" +
                   "6. Sikertelen szobaváltási kísérlet\n" +
                   "7. Sikeres hallgató-lélek elvétel\n" +
                   "8. Utolsó játékos kiesése\n" +
                   "9. Sikertelen lélekelvétel TVSZ használata miatt\n" +
                   "10. Sikertelen lélekelvétel söröspohár miatt\n" +
                   "11. Rongy használat\n" +
                   "12. Hallgatók nyernek\n" +
                   "13. Camembert begázosítja a szobát\n" +
                   "14. Karakter sikeresen összekapcsol két tranzisztort\n" +
                   "15. Karakter sikeresen teleportál tranzisztorral\n" +
                   "16. Tranzisztor felhasználás folyamatának belső működése\n" +
                   "17. Gázos szobában jelenlét maszk nélkül\n" +
                   "18. Gázos szoba kivédése maszkkal\n" +
                   "19. Rongy száradás\n" +
                   "20. A rongy tárgy megszáradt\n" +
                   "21. Egy szoba kettővé válik szét\n" +
                   "22. Két szoba egyesül egy szobává\n" +
                   "23. Elátkozott szoba működése játék közben\n" +
                   "24. Oktató belép egy szobába, ahol aktív rongy van és lebénul");
    
    System.out.println("Add meg a végrhajtandó művelet menüjének számát");
    int num = scanner.nextInt();

    while(num < 1 || num > 24){
        System.out.println("Hibás szám, adj meg újat!");
        num = scanner.nextInt();
    }

    switch(num){
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
        case 5:
            break;
        case 6:
            break;
        case 7:
            break;
        case 8:
            break;
        case 9:
            break;
        case 10:
            break;
        case 11:
            break;
        case 12:
            break;
        case 13:
            break;
        case 14:
            break;
        case 15:
            break;
        case 16:
            break;
        case 17:
            break;
        case 18:
            break;
        case 19:
            break;
        case 20:
            break;
        case 21:
            break;
        case 22:
            break;
        case 23:
            break;
        case 24:
            break;
        default:
            break;
     }
    }

}
