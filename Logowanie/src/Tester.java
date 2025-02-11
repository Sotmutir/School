public class Tester extends User{
    public Tester(String userName, String password, String email, int token, int id, String login) {
        super(userName, password, email, token, id, login);

        accesLevel = 2;
    }

    @Override
    public int getAccessLevel() {
        System.out.println("\u001B[93mPoziom dostępu: " + accesLevel + ". Jesteś testerem:");
        System.out.println("    Dostęp do środowiska testowego\u001B[0m");

        return accesLevel;
    }
}
