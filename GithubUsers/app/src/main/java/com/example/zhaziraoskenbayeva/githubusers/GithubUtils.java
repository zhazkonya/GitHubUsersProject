package com.example.zhaziraoskenbayeva.githubusers;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaziraoskenbayeva on 29/01/18.
 */

public class GithubUtils {
    public final static  String GITHUB_API_BASE ="https://api.github.com/users";

    Context c = null;

    public GithubUtils(Context context) {
        c = context;
    }

    public List<User> requestToGithub() {
        List<User> users = new ArrayList<>();
        try{
            String request = GITHUB_API_BASE;
            HTTPDataHandler hh = new HTTPDataHandler();
            String stream = hh.GetHTTPData(request);
            users =  processResult(stream);
        }catch(Exception e){}
        return users;
    }

    private List<User> processResult(String stream) {
        List<User> users = new ArrayList<>();
        try {
            JSONArray objarr = new JSONArray(stream);
            for(int i=0; i<objarr.length(); i++){
                JSONObject uobj = objarr.getJSONObject(i);
                String photo_url  = uobj.getString("avatar_url");
                String login  = uobj.getString("login");
                String html_url  = uobj.getString("html_url");
                User u = new User(photo_url, login, html_url);
                users.add(u);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
}
