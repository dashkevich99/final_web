package by.gsu.epamlab.beans;

public class User {

    private int userId;
    private String login;
    private String email;
    private String password;

    public User() {
    }

    public User(int userId, String login, String email) {
        this(userId, login, email, null);
    }

    public User(String login, String email) {
        super();
        this.login = login;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int userId, String login, String email, String password) {
        super();
        this.userId = userId;
        this.login = login;
        this.email = email;
        this.password = password;
    }
}