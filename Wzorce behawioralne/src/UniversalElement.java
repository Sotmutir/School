import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UniversalElement extends JPanel {
    JLabel nameLbl;

    JLabel amountLbl;

    JButton deleteBtn;




    UniversalElement(String stringPar, int intPar, String prefixForIntPar, UniversalElementType type) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 100));
        setBackground(Color.LIGHT_GRAY);

        nameLbl = new JLabel(stringPar);

        amountLbl = new JLabel(prefixForIntPar + Integer.toString(intPar));

        deleteBtn = new JButton();
        if(type == UniversalElementType.STORAGE) deleteBtn.setText("Usuń składnik");
        else if(type == UniversalElementType.TABLE) deleteBtn.setText("Usuń stolik");

        deleteBtn.addActionListener(e -> {
            if(type == UniversalElementType.STORAGE) {
//                    System.out.println("Usuwanie składnika...");
                for (int i = 0; i < Window.storage.size(); i++) {
                    Indegriend ing = Window.storage.get(i);
                    if (ing.name.equals(stringPar) && ing.amount == intPar) {
                        Window.storage.remove(Window.storage.indexOf(ing));
                        Window.invalidate();
//                        System.out.println("Usunięto składnik " + ing.name);
                        break;
                    }
                }
            }

            else if(type == UniversalElementType.TABLE) {
//                System.out.println("Usuwanie stolika...");
                for (int i = 0; i < Window.tables.size(); i++) {
                    int ing = Window.tables.get(i);
                    if (ing == intPar) {
                        Window.tables.remove(Window.tables.indexOf(ing));
                        Window.invalidate();

//                        System.out.println("Usunięto stolik " + ing);
                        break;
                    }
                }
            }
        });

        add(nameLbl);
        add(amountLbl);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(deleteBtn);

    }

}
