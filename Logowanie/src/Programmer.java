public class Programmer extends User{
    public Programmer(String userName, String password, String email, int token, int id, String login) {
        super(userName, password, email, token, id, login);

        accesLevel = 1;
    }

    @Override
    public int getAccessLevel() {
        System.out.println("\u001B[92mPoziom dostępu: " + accesLevel + ". Jesteś programistą:");
        System.out.println("    Dostęp do kodu, ale nie ustawień\u001B[0m");

        return accesLevel;
    }
}
