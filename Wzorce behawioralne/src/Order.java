import javax.swing.*;
import java.awt.*;
//import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Order extends JToolBar {

    JLabel dishLbl;
    JLabel tableLbl;
    JButton executeBtn;

    JLabel tmpLbl;

    ArrayList<Indegriend> indegriends;

    Color colorRed;
    Color colorGreen;
    Color colorOrange;

    public boolean isAbleToExecute;


    Order(String dishName, int tableNum, ArrayList<Indegriend> indegriends) {
        this.indegriends = indegriends;
        isAbleToExecute = true;

        colorGreen = new Color(80, 234, 106);
        colorRed = new Color(234, 93, 80);
        colorOrange = new Color(234, 196, 80);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setMinimumSize(new Dimension(200, 0));

        setBackground(Color.LIGHT_GRAY);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        dishLbl = new JLabel(dishName);

        tableLbl = new JLabel("Numer stolika: " + Integer.toString(tableNum));

        executeBtn = new JButton("Wykonaj zamówienie");
        executeBtn.addActionListener(e -> {
            for(int i = 0; i < Window.storage.size(); i++) {
                for(int j = 0; j < indegriends.size(); j++) {
                    if(Window.storage.get(i).name.equalsIgnoreCase(indegriends.get(j).name)) {
//                        System.out.println("usunięto " + indegriends.get(j).name + " w ilości " + indegriends.get(j).amount);
                        Window.storage.get(i).amount -= indegriends.get(j).amount;
                    }
                }
            }

//            System.out.println("usunołeś element: " + Window.orderedDishes.get(Window.orderedDishes.indexOf(this)).dishLbl.getText());

            Window.orderedDishes.remove(this);
            Window.invalidate();

        });


        checkIndegriends();

    }

    public void checkIndegriends() {
        removeAll();
        add(dishLbl);
        add(tableLbl);
        add(Box.createRigidArea(new Dimension(0, 10)));

        isAbleToExecute = true;
        for (int i = 0; i < indegriends.size(); i++) {
            tmpLbl = new JLabel(indegriends.get(i).name + " - " + indegriends.get(i).amount + " " + indegriends.get(i).unit);
            tmpLbl.setFont(new Font("Serif", Font.BOLD, 15));

            boolean hasFound = false;
            for (int j = 0; j < Window.storage.size(); j++) {
                if(Window.storage.get(j).name == this.indegriends.get(i).name) {
                    hasFound = true;
                    if(Window.storage.get(j).amount >= this.indegriends.get(i).amount) {
                        tmpLbl.setForeground(colorGreen);
                    }
                    else {
                        tmpLbl.setForeground(colorOrange);
                        isAbleToExecute = false;
                    }
                }
            }
            if(!hasFound) {
                tmpLbl.setForeground(colorRed);
                isAbleToExecute = false;
            }

            add(tmpLbl);
        }

        executeBtn.setEnabled(isAbleToExecute);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(executeBtn);
        add(Box.createRigidArea(new Dimension(0, 40)));
    }
}
