public class User {
    protected int accesLevel;

    protected String userName;
    protected String password;
    protected String email;
    protected String login;
    protected int token;
    protected int id;


    public User(String userName, String password, String email, int token, int id, String login) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.token = token;
        this.id = id;
        this.login = login;

        accesLevel = 0;
    }


    public int getAccessLevel() {
        System.out.println("Poziom dostępu: " + accesLevel + ". Jesteś zwykłym użytkowiniek.");

        return accesLevel;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public int getToken() {
        return token;
    }

    public String getLogin() {
        return login;
    }
}
