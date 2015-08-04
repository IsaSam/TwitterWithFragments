package com.isadev.apps.IsaTweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Isaac on 7/23/2015.
 */
public class User {
    //list attributes
    private String name;
    private Long uid;
    private String screename;
    private String profileImageUrl;
    private String tagline;
    private int followersCount;
    private int followingsCount;

    public String getName() {
        return name;
    }

    public Long getUid() {
        return uid;
    }

    public String getScreename() {
        return screename;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getTagline() {
        return tagline;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return followingsCount;
    }

    //deserialize the user json => User
    public static User fromJSON(JSONObject json){
        User u =  new User();
        //Extract and fill the values
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screename =json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
            u.tagline = json.getString("description");
            u.followersCount = json.getInt("followers_count");
            u.followingsCount = json.getInt("friends_count");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Return a user
        return u;
    }
}
