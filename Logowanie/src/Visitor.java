public class Visitor extends User{
    public Visitor(String userName, String password, String email, int token, int id, String login) {
        super(userName, password, email, token, id, login);

        accesLevel = 5;
    }

    @Override
    public int getAccessLevel() {
        System.out.println("\u001B[96mPoziom dostępu: " + accesLevel + ". Jesteś gościem:");
        System.out.println("    Dostęp do ekspozycji\u001B[0m");

        return accesLevel;
    }
}
