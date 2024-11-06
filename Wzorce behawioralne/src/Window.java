import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Window {
    public static JFrame win;

    public static JScrollPane ordersPane;
    public static JPanel orders;
    public static JPanel ordersSide;
    public static JButton ordersAddBtn;


    JTabbedPane tabs;
    public static Tab tab1, tab2;
    JPanel tab3;
    public static JPanel outerTab1, outerTab2;

    public static ArrayList<Indegriend> storage;
    public static ArrayList<Integer> tables;
    public static ArrayList<Order> orderedDishes;

    Window() {
        win = new JFrame("System zamówień restauracji - Wzorce behawioralne");

        storage = new ArrayList<>();
        storage.add(new Indegriend("Marchew", 20, "kg"));
        storage.add(new Indegriend("Masło", 10, "kg"));
        storage.add(new Indegriend("Zasoby McDonald", 100, "kg"));
        storage.add(new Indegriend("Zasoby KFC", 100, "kg"));
        storage.add(new Indegriend("Ogórki", 15, "kg"));
        storage.add(new Indegriend("Kostki rosołowe", 1000, "kg"));
        storage.add(new Indegriend("Warzywa w proszku", 1000000, "kg"));
        storage.add(new Indegriend("Sałata", 30, "kg"));
        storage.add(new Indegriend("Mięso w proszku", 50, "kg"));
        storage.add(new Indegriend("Mleko", 100, "L"));

        tables = new ArrayList<>();
        tables.add(1);
        tables.add(2);
        tables.add(3);
        tables.add(4);
        tables.add(5);


        // Tabs
        outerTab1 = new JPanel(new BorderLayout());
        tab1 = new Tab();
        for (int i = 0; i < storage.size(); i++) {
            tab1.add(new UniversalElement(storage.get(i).name, storage.get(i).amount, "Ilość [" + storage.get(i).unit + "]: ", UniversalElementType.STORAGE));
        }
        outerTab1.add(tab1, BorderLayout.CENTER);
        outerTab1.add(tab1.getAdditionElement(UniversalElementType.STORAGE), BorderLayout.SOUTH);

        outerTab2 = new JPanel(new BorderLayout());
        tab2 = new Tab();
        for (int i = 0; i < tables.size(); i++) {
            tab2.add(new UniversalElement("Stolik", tables.get(i), "Numer stolika: ", UniversalElementType.TABLE));
        }
        outerTab2.add(tab2, BorderLayout.CENTER);
        outerTab2.add(tab2.getAdditionElement(UniversalElementType.TABLE), BorderLayout.SOUTH);


        tab3 = new JPanel();
        tab3.setLayout(new BoxLayout(tab3, BoxLayout.Y_AXIS));
        tab3.add(new JLabel("Ten system zamówień przedstawia zastosowanie wzorca behawioralnego SZABLON."));
        tab3.add(new JLabel("Jest zastwosowane w uniwersalnych elementach w postaci klasy UniversalElement, której obiekty zasilają karty: Magazyn oraz Stoliki."));
        tab3.add(new JLabel("Jest to też zastosowane na samych kartach w postaci klasy Tab. Ta klasa to JPanel z ustawinym Layoutem."));
        tab3.add(Box.createRigidArea(new Dimension(0, 50)));
        tab3.add(new JLabel("Wykonane przez: Jan Matysik"));
        tab3.add(new JLabel("Klasa: 3TP 2024/2025"));


        // Adding tabs to tabs panel
        tabs = new JTabbedPane();
        tabs.add("Magazyn", outerTab1);
        tabs.add("Stoliki", outerTab2);
        tabs.add("Info do zadania", tab3);




        // Adding orders to list of orders
        orderedDishes = new ArrayList<>();

        ArrayList<Indegriend> dishesIndegriends = new ArrayList<>();
        dishesIndegriends.add(new Indegriend("Ogórki", 16, "kg"));
        orderedDishes.add(new Order("Zupa ogórkowa", tables.get(0), dishesIndegriends));

        dishesIndegriends = new ArrayList<>();
        dishesIndegriends.add(storage.get(2));
        orderedDishes.add(new Order("WieśMc", tables.get(1), dishesIndegriends));


        //Orders panel
        orders = new JPanel();
        orders.setLayout(new BoxLayout(orders, BoxLayout.Y_AXIS));
        for (int i = 0; i < orderedDishes.size(); i++) {
            orders.add(orderedDishes.get(i));
        }

        // Orders pane
        ordersPane = new JScrollPane(orders);
        ordersPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ordersPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ordersPane.setMinimumSize(new Dimension(300, 0));


        // Orders side for the window
        ordersSide = new JPanel();
        ordersSide.setLayout(new BoxLayout(ordersSide, BoxLayout.Y_AXIS));
//        ordersSide.setPreferredSize(new Dimension(0, 30));

        JLabel tmpLbl = new JLabel("Zamówienia");
        tmpLbl.setFont(new Font("Serif", Font.BOLD, 24));
        tmpLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        ordersAddBtn = new JButton("Dodaj nowe zamówienie");
        ordersAddBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        ordersSide.add(tmpLbl);
        ordersSide.add(ordersAddBtn);
        ordersSide.add(ordersPane);


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, ordersSide, tabs);


        win.getContentPane().add(splitPane);

        win.setBounds(200, 200, 600, 300);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }

    public static void invalidate() {
        tab1.removeAll();
        outerTab1.removeAll();
        for (int i = 0; i < storage.size(); i++) {
            UniversalElement tmpUE = new UniversalElement(storage.get(i).name, storage.get(i).amount, "Ilość [" + storage.get(i).unit + "]: ", UniversalElementType.STORAGE);
            if(storage.get(i).amount == 0) tmpUE.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

            tab1.add(tmpUE);
        }
        outerTab1.add(tab1, BorderLayout.CENTER);
        outerTab1.add(tab1.getAdditionElement(UniversalElementType.STORAGE), BorderLayout.SOUTH);

        tab2.removeAll();
        outerTab2.removeAll();
        for (int i = 0; i < tables.size(); i++) {
            tab2.add(new UniversalElement("Stolik", tables.get(i), "Numer stolika: ", UniversalElementType.TABLE));
        }
        outerTab2.add(tab2, BorderLayout.CENTER);
        outerTab2.add(tab2.getAdditionElement(UniversalElementType.TABLE), BorderLayout.SOUTH);

        orders.removeAll();
        for (int i = 0; i < orderedDishes.size(); i++) {
            orderedDishes.get(i).checkIndegriends();
            orders.add(orderedDishes.get(i));
//            System.out.println(Window.orderedDishes.get(i).dishLbl.getText());
        }

        win.revalidate();
        win.repaint();
    }
}
