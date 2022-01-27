package persistence;

public class UserModel {
    private Integer userId;
    private String username;
    private String password;
    private String email;

    public UserModel() {
    }

    public UserModel(Integer userId, String email, String username, String password) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserModel(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
