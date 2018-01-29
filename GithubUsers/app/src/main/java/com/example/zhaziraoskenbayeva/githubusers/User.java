package com.example.zhaziraoskenbayeva.githubusers;

/**
 * Created by zhaziraoskenbayeva on 29/01/18.
 */

public class User {

    String photo_url;
    String login;
    String html_url;

    User(){
        photo_url = ""; login = ""; html_url = "";
    }
    User(String p, String l, String h){
        photo_url = p;
        login = l;
        html_url = h;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
