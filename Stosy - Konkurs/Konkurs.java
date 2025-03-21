import java.util.*;

public class Konkurs implements AutoCloseable {
    private LinkedList<Uczestnik> uczestnicy;
    private int counter = 1;
    private int maxLength = 0;
    private Random random;



    public Konkurs() {
        uczestnicy = new LinkedList<>();
        random = new Random();


        ANSI.println(ANSI.use(ANSI.GRAY) + "<|*|>-------------------------------------------------------------<|*|>");

        ANSI.println(ANSI.use(ANSI.WHITE) + "Do realizacji tego zadania zdecydowałem się wybrać stos typu" + ANSI.use(ANSI.AQUA, ANSI.ITALIC) + " LinkedList\n" +
                ANSI.use(ANSI.WHITE) + "ze względów na jego wydajność. Nie ma tu jednak głębszego znaczenia w\n" +
                "tym wyborze.");

        ANSI.println(ANSI.use(ANSI.GRAY) + "<|*|>-------------------------------------------------------------<|*|>");
    }



    public void zarejestrujUczestnika(String imie, String nazwisko) {
        zarejestrujUczestnika(new Uczestnik(imie, nazwisko, counter++));
    }
    public void zarejestrujUczestnika(Uczestnik uczestnik) {
        ANSI.println(ANSI.use(ANSI.LIGHT_PURPLE, ANSI.BOLD) + "\nRejestrowanie uczestnika:");
        uczestnicy.add(uczestnik);

        for(Uczestnik u : uczestnicy) {
            if(u.STRINGED.length() > maxLength) maxLength = u.STRINGED.length();
        }

        ANSI.println(ANSI.use(ANSI.GREEN) + "   Zarejestrowano uczestnika: " + uczestnik.getImie() + " " + uczestnik.getNazwisko());
    }



    public void usun(Uczestnik uczestnik) {
        ANSI.println(ANSI.use(ANSI.LIGHT_PURPLE, ANSI.BOLD) + "\nUsuwanie uczestnika:");
        uczestnicy.remove(uczestnik);

        ANSI.println(ANSI.use(ANSI.GREEN) + "   Usunięto uczestnika: " + uczestnik.getImie() + " " + uczestnik.getNazwisko());
    }
    public void usun(String imie, String nazwisko) {
        ANSI.println(ANSI.use(ANSI.LIGHT_PURPLE, ANSI.BOLD) + "\nUsuwanie uczestnika:");

        for(Uczestnik u : uczestnicy) {
            if(u.getImie().equals(imie) && u.getNazwisko().equals(nazwisko)) {
                uczestnicy.remove(u);
                ANSI.println(ANSI.use(ANSI.GREEN) + "   Usunięto uczestnika: " + u.getImie() + " " + u.getNazwisko());
                return;
            }
        }

        ANSI.println("   " + ANSI.use(ANSI.RED, ANSI.BOLD) + "Nie ma takiego uczestnika");
    }



    public void dyskfalifikuj(Uczestnik uczestnik) {
        ANSI.println(ANSI.use(ANSI.LIGHT_PURPLE, ANSI.BOLD) + "\nDyskflasyfikowanie uczestnika:");
        uczestnik.setCzyZdyskfalifikowany(true);

        ANSI.println(ANSI.use(ANSI.GREEN) + "   Zdysklasyfikowano uczestnika: " + uczestnik.getImie() + " " + uczestnik.getNazwisko());
    }
    public void dyskfalifikuj(String imie, String nazwisko) {
        ANSI.println(ANSI.use(ANSI.LIGHT_PURPLE, ANSI.BOLD) + "\nDyskflasyfikowanie uczestnika:");

        for(Uczestnik u : uczestnicy) {
            if(u.getImie().equals(imie) && u.getNazwisko().equals(nazwisko)) {
                u.setCzyZdyskfalifikowany(true);
                ANSI.println(ANSI.use(ANSI.GREEN) + "   Zdysklasyfikowano uczestnika: " + u.getImie() + " " + u.getNazwisko());
                return;
            }
        }

        ANSI.println("   " + ANSI.use(ANSI.RED, ANSI.BOLD) + "Nie ma takiego uczestnika");
    }



    public void wyswietlUczestnikow() {
        ANSI.println(ANSI.use(ANSI.LIGHT_PURPLE, ANSI.BOLD) + "\nWyświetlanie uczestników:");

        if(uczestnicy.isEmpty()) {
            ANSI.println("   " + ANSI.use(ANSI.RED, ANSI.BOLD) + "Brak uczestników");
            return;
        }

        for(Uczestnik u : uczestnicy) {
            System.out.println(u.getPrintable(maxLength));
        }
    }



    public void przydzielNumerStartowy(Uczestnik uczestnik, int value) {
        ANSI.println(ANSI.use(ANSI.LIGHT_PURPLE, ANSI.BOLD) + "\nPrzydzielanie numeru startowego uczestnika:");
        uczestnik.setNumerStartowy(value);

        ANSI.println(ANSI.use(ANSI.GREEN) + "   Przydzielono numer startowy uczestnika: " + uczestnik.getImie() + " " + uczestnik.getNazwisko());
    }
    public void przydzielNumerStartowy(String imie, String nazwisko, int value) {
        ANSI.println(ANSI.use(ANSI.LIGHT_PURPLE, ANSI.BOLD) + "\nPrzydzielanie numeru startowego uczestnika:");

        for(Uczestnik u : uczestnicy) {
            if(u.getImie().equals(imie) && u.getNazwisko().equals(nazwisko)) {
                u.setNumerStartowy(value);
                ANSI.println(ANSI.use(ANSI.GREEN) + "   Przydzielono numer startowy uczestnika: " + u.getImie() + " " + u.getNazwisko());
                return;
            }
        }

        ANSI.println("   " + ANSI.use(ANSI.RED, ANSI.BOLD) + "Nie ma takiego uczestnika");
    }
    public void przydzielNumerStartowy(String imie, String nazwisko) {
        przydzielNumerStartowy(imie, nazwisko, random.nextInt(50) + 1);
    }



    public void sprawdzObecnosc(String imie, String nazwisko) {
        ANSI.println(ANSI.use(ANSI.LIGHT_PURPLE, ANSI.BOLD) + "\nSprawdzanie obecności uczestnika:");

        for(Uczestnik u : uczestnicy) {
            if(u.getImie().equals(imie) && u.getNazwisko().equals(nazwisko)) {
                ANSI.println(ANSI.use(ANSI.GREEN) + "   Uczestnik obecny: " + u.getImie() + " " + u.getNazwisko());
                return;
            }
        }

        ANSI.println("   " + ANSI.use(ANSI.RED, ANSI.BOLD) + "Nie ma takiego uczestnika");
    }



    @Override
    public void close() {
        ANSI.println(ANSI.use(ANSI.GRAY) + "\n\n<|*|>-------------------------------------------------------------<|*|>");

        ANSI.println(ANSI.use(ANSI.AQUA, ANSI.BOLD) + "Jan Matysik" + ANSI.use(ANSI.WHITE) + "   |   " +
                ANSI.use(ANSI.AQUA, ANSI.BOLD) + "3TP" + ANSI.use(ANSI.WHITE) + "   |   " +
                ANSI.use(ANSI.AQUA, ANSI.BOLD) + "Stosy");

        ANSI.println(ANSI.use(ANSI.GRAY) + "<|*|>-------------------------------------------------------------<|*|>");
    }
}
