import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Tab extends JPanel {

    Tab() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public JButton getAdditionElement(UniversalElementType type) {
//        add(new UniversalElement(null, type.ordinal(), null, UniversalElementType.ADD));\

        JButton addBtn = new JButton();
        if (type == UniversalElementType.STORAGE) addBtn.setText("Dodaj nowy składnik");
        else if (type == UniversalElementType.TABLE) addBtn.setText("Dodaj nowy stolik");

        addBtn.addActionListener(e -> {
            String name;
            int amount = 0;
            String unit = "";

            System.out.println("Dodawanie nowego składnika: ");
            System.out.println("WPISZ: -1 aby przerwać!");

            try {
                Scanner read = new Scanner(System.in);

                boolean isAbleToContinue = true;

                System.out.print(" Nazwa: ");
                name = read.nextLine();

                if(name.equals("-1")) isAbleToContinue = false;

                if (isAbleToContinue) {
                    System.out.print(" Ilość: ");
                    amount = read.nextInt();

                    if(amount == -1) isAbleToContinue = false;
                }

                if (isAbleToContinue) {
                    System.out.print(" Jednostka: ");
                    read.nextLine();
                    unit = read.nextLine();
                    if(unit.equals("-1")) isAbleToContinue = false;
                }

                if (!isAbleToContinue) {
                    System.out.println("ANULOWANO DODAWANIE!");
                }

                if (isAbleToContinue) {
                    if(type == UniversalElementType.STORAGE) {
                        Window.storage.add(new Indegriend(name, amount, unit));
                        Window.invalidate();
                        System.out.println("ZAKOŃCZONO DODAWANIE ELEMENTU!");
                        System.out.println();
                    }
                }

            } catch (Exception exception) {
                System.out.println("Błąd wpisywania");
                exception.getStackTrace();
            }
        });

        return addBtn;
    }
}
