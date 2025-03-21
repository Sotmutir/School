
public class Main {
    public static void main(String[] args) {
        try(var k = new Konkurs()) {

            k.zarejestrujUczestnika("Jan", "Kowalski");
            k.zarejestrujUczestnika("Jan", "Kowalski");
            k.zarejestrujUczestnika("Jan", "Beruh");
            k.zarejestrujUczestnika("Jan", "Nowak");

            k.wyswietlUczestnikow();

            k.usun("Jan", "Kowalski");

            k.dyskfalifikuj("Jan", "Kowalski");

            k.przydzielNumerStartowy("Jan", "Beruh", 1);

            k.wyswietlUczestnikow();
        }
    }
}