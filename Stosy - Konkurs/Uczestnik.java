import java.util.Random;

public class Uczestnik {
    private String imie;
    private String nazwisko;
    private int numerStartowy = -1;
    private int index;
    public final String STRINGED;
    private boolean czyZdyskfalifikowany = false;

    public Uczestnik(String imie, String nazwisko, int index) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.index = index;

        STRINGED = "   " + index + ". " + imie + " " + nazwisko;
    }



    // Gettery
    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getNumerStartowy() {
        return numerStartowy;
    }


    public String getPrintable(int maxLength) {
        String s = STRINGED;
        for(int i = 1; i <= maxLength - STRINGED.length(); i++) s += " ";

        if(numerStartowy != -1) s += "   " + numerStartowy;
        else s += "   nieprzydzielony";

        if(czyZdyskfalifikowany) s = ANSI.use(ANSI.STRIKETHROUGH, ANSI.RED) + s + ANSI.use(ANSI.RESET);
        else if(numerStartowy == -1) s = ANSI.use(ANSI.ITALIC, ANSI.YELLOW) + s + ANSI.use(ANSI.RESET);
        else s = ANSI.use(ANSI.BLUE) + s + ANSI.use(ANSI.RESET);

        return s;
    }

    public boolean getCzyZdyskfalifikowany() {
        return czyZdyskfalifikowany;
    }

    public void setCzyZdyskfalifikowany(boolean value) {
        czyZdyskfalifikowany = value;
    }



    public void setNumerStartowy(int value) {
        numerStartowy = value;
    }
}
