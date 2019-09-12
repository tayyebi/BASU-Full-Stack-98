package ir.kukuapp.mobile.model;

public class PostModel {
    private String username, text;

    public PostModel(String username, String text) {
        this.username = username;
        this.text = text;
    }

    public String getUsername() {
        return this.username;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return getUsername() + ": " + getText();
    }
}
