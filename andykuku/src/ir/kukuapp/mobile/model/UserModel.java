package ir.kukuapp.mobile.model;

public class UserModel {
    private String username, password, userid;

    public UserModel(String username, String password, String userid) {
        this.username = username;
        this.password = password;
        this.userid = userid;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() { return password; }

    public String getUserID() { return userid; }
}
