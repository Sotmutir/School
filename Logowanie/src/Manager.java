public class Manager extends User{

    public Manager(String userName, String password, String email, int token, int id, String login) {
        super(userName, password, email, token, id, login);

        accesLevel = 4;
    }

    @Override
    public int getAccessLevel() {
        System.out.println("\u001B[95mPoziom dostępu: " + accesLevel + ". Jesteś menadźerem:");
        System.out.println("    Dostęp do raportów\u001B[0m");

        return accesLevel;
    }
}
