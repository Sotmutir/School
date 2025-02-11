import java.util.ArrayList;
import java.util.List;

public class LoginService {
    List<User> users;


    public LoginService() {
        users = new ArrayList<>();

        users.add(new User("user1", "12345678", "name@example.com", 10101, 1, "login1"));
        users.add(new Admin("admin1", "000000", "admin@examole.com", 234654, 2, "login2"));
        users.add(new Manager("manager1", "8sf#DFsg", "manager@example.com", 5457642, 3, "login3"));
        users.add(new Programmer("prog1", "ufEFuhgdg", "programmer@example.com", 5646764, 4, "login4"));
        users.add(new Tester("test1", "asdfg", "tester@example.com", 562614, 5, "login5"));
        users.add(new Visitor("visit1", "gidjag", "visitor.com", 16246, 6, "login6"));
    }




    public boolean login(String username, String password) {
        for(User u : users) {
            if(u.getUserName().equals(username) && u.getPassword().equals(password)) {
                System.out.println("\u001B[32mZalogowano!\u001B[0m");
                return true;
            }
        }
        System.out.println("\u001B[31mNiezalogowano!\u001B[0m");
        return false;
    }




    public boolean login(String login, String email, String password) {
        for(User u : users) {
            if(u.getEmail().equals(email) && u.getPassword().equals(password) && u.getLogin().equals(login)) {
                System.out.println("\u001B[32mZalogowano!\u001B[0m");
                return true;
            }
        }
        System.out.println("\u001B[31mNiezalogowano!\u001B[0m");
        return false;
    }




    public boolean login(String username, int token) {
        for(User u : users) {
            if(u.getUserName().equals(username) && u.getToken() == token) {
                System.out.println("\u001B[32mZalogowano!\u001B[0m");
                return true;
            }
        }
        System.out.println("\u001B[31mNiezalogowano!\u001B[0m");
        return false;
    }




    public boolean login(int id, String password) {
        for(User u : users) {
            if(u.getPassword().equals(password) && u.getId() == id) {
                System.out.println("\u001B[32mZalogowano!\u001B[0m");
                return true;
            }
        }
        System.out.println("\u001B[31mNiezalogowano!\u001B[0m");
        return false;
    }
}
