public class Admin extends User{
    public Admin(String userName, String password, String email, int token, int id, String login) {
        super(userName, password, email, token, id, login);

        accesLevel = 3;
    }

    @Override
    public int getAccessLevel() {
        System.out.println("\u001B[94mPoziom dostępu: " + accesLevel + ". Jesteś administratorem:");
        System.out.println("    Pełen dostęp\u001B[0m");

        return accesLevel;
    }
}
