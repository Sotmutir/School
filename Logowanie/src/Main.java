public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");


        LoginService ls = new LoginService();

        // Poprawne z niepoprawnym na ziamnę

        ls.login("user1", "12345678");
        ls.login("user1", "12345678xx");

        ls.login("login2", "admin@examole.com", "000000");
        ls.login("login2z", "admin@examole.com", "000000");

        ls.login("manager1", 5457642);
        ls.login("manager1.", 5457642);

        ls.login(4, "ufEFuhgdg");
        ls.login(42, "ufEFuhgdg");
    }
}