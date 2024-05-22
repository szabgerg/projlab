package logarlec;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class InputManager implements KeyListener {
    private String[] layout;

    public String[] getLayout() {
        return layout;
    }

    // szoveg input allapotgephez
    private StringBuilder command = new StringBuilder();

    private void numberInput(int n) {
        if (command.length() > 0) {
            char action = command.charAt(0);
            Karakter jelenlegiKar = Graf.getAktKarakter().getModel();

            switch (action) {
                case 'm':
                    /* mozgás */
                    List<Szoba> szobaList = jelenlegiKar.getSzoba().getSzomszedok();
                    n -= 1;
                    if (n >= 0 && n < szobaList.size()) {
                        jelenlegiKar.mozog(szobaList.get(n));
                    } else {
                        System.out.println("Nincs ilyen számú szoba");
                    }
                    break;

                case 'a':
                    Targyinventory eszkozkeszlet = jelenlegiKar.getEszkozkeszlet();
                    List<ITargy> targyak = eszkozkeszlet.getTargyak();

                    if (n >= 1 && n <= 5) { // Ellenőrizzük, hogy a megadott sorszám a megfelelő tartományban van-e
                                            // (1-től 5-ig)
                        if (targyak.size() <= n){
                            System.out.println("Az adott sorszámú tárgy nem található.");
                        }
                        else {
                            ITargy aktivalando = targyak.get(n - 1); // Az indexeket 0-tól kezdve indexeljük
                            aktivalando.aktival(jelenlegiKar);
                        }
                    } else {
                        System.out.println("Hibás sorszám, válasszon 1 és 5 közötti számot.");
                    }
                    break;

                case 'f': // Felvétel szám szerint, ahogy a szoba inventoryban számozva van, ami fix
                    // 1->tranzisztor
                    // 2->sorosohar
                    // 3->camambert
                    // 4->maszk
                    // 5->tvsz
                    // 6->logarlec
                    // 7->rongy
                    // 8->legfrissito
                    Targyinventory bentitargyak = jelenlegiKar.getSzoba().getBentiTargyak();
                    if (n >= 1 && n <= 8) { // Check if the number is within the valid range
                        String tipus = null;
                        switch (n) { // Determine the item type based on the given number
                            case 1:
                                tipus = "Tranzisztor";
                                break;
                            case 2:
                                tipus = "Sorospohar";
                                break;
                            case 3:
                                tipus = "Camambert";
                                break;
                            case 4:
                                tipus = "Maszk";
                                break;
                            case 5:
                                tipus = "Tvsz";
                                break;
                            case 6:
                                tipus = "Logarlec";
                                break;
                            case 7:
                                tipus = "Rongy";
                                break;
                            case 8:
                                tipus = "Legfrissito";
                                break;
                            default:
                                break;
                        }
                        // Iterate through the items in the room and pick up the item with the matching
                        // type
                        for (int i = 0; i < bentitargyak.getTargyak().size(); i++) {
                            if (bentitargyak.getTargyak().get(i).getClass().getSimpleName().equals(tipus)) {
                                jelenlegiKar.felvesz(i);
                                break;
                            }
                        }
                    }
                    break;

                case 'l': // Tárgy lerakása az inventoryból
                    if (n >= 1 && n <= jelenlegiKar.getEszkozkeszlet().getTargyak().size()) { // Ellenőrizzük a
                                                                                              // sorszámot
                        jelenlegiKar.letesz(n - 1); // Az indexek 0-tól kezdve vannak
                    } else {
                        System.out.println("Hibás sorszám, válasszon 1 és "
                                + jelenlegiKar.getEszkozkeszlet().getTargyak().size() + " közötti számot.");
                    }
                    break;

                case 'o': // Tranzisztor összekapcsolás
                    command.append(n);
                    if (command.toString().length() == 3) { // Ellenőrizze, hogy a parancs 3 karakter hosszú-e (o + 2
                                                            // szám)
                        try {
                            String[] numbers = command.toString().substring(1).split(""); // Az egyes karaktereket
                                                                                          // szétválasztjuk

                            int number1 = Integer.parseInt(numbers[0]);
                            int number2 = Integer.parseInt(numbers[1]);

                            List<ITargy> targyakk = Graf.getAktKarakter().getModel().eszkozkeszlet.getTargyak();

                            // Ellenőrizze, hogy az indexek a tartományban vannak-e
                            if (number1 >= 1 && number1 <= targyakk.size() && number2 >= 1
                                    && number2 <= targyakk.size()) {
                                // Ellenőrizze, hogy a tárgyak tranzisztorok-e
                                ITargy targy1 = targyakk.get(number1 - 1);
                                ITargy targy2 = targyakk.get(number2 - 1);

                                if (targy1 instanceof Tranzisztor && targy2 instanceof Tranzisztor) {
                                    Tranzisztor tr1 = (Tranzisztor) targy1;
                                    Tranzisztor tr2 = (Tranzisztor) targy2;

                                    // Összekapcsolás
                                    tr1.setPar(tr2);
                                    tr2.setPar(tr1);

                                    System.out.println("A tranzisztorok összekapcsolva.");
                                } else {
                                    System.out.println("Az egyik vagy mindkét tárgy nem tranzisztor.");
                                }
                            } else {
                                System.out.println("Hibás tranzisztor indexek.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Érvénytelen számformátum.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Index túlmutat a határon.");
                        } finally {
                            command.setLength(0); // Parancs törlése
                        }
                    }
                    break;

            }
        }
    }

    /* beviteli karakterek tárolása */
    private void wasInput(char c) {
        command.setLength(0); // Reset command
        command.append(c);
    }

    @Override
    public void keyPressed(KeyEvent k) {
        char c = k.getKeyChar();
        if (Character.isDigit(c)) {
            numberInput(Character.getNumericValue(c));
        } else if (c == ' ') {
            command.append(' ');
        } else {
            wasInput(c);
        }
    }

    @Override
    public void keyTyped(KeyEvent k) {
        /* nem kell */
    }

    @Override
    public void keyReleased(KeyEvent k) {
        /* nem kell */
    }

}