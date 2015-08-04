package com.isadev.apps.IsaTweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Isaac on 7/23/2015.
 */

/*


 */
//Parse the JSON + Store the data, encapsulate state logic or display
public class Tweet {
    //List out the attributes
    private String body;
    private long uid; //unique id for the tweet
    private User user; // Store embedded user object
    private String createdAt;

    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    //Deserialize the JSON and build tweet objects
    //Tweet.fromJSON("{ ... }") => <Tweet>
    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();

        //Extract the values from the json, store them
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Return the tweet object
        return tweet;
    }
    // tweet.fromJSONArray[ { ... }, { ... } ] => List<Tweet>
    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray){
        ArrayList<Tweet> tweets = new ArrayList<>();
        // Iterate the json array and create tweets
        for (int i = 0; i < jsonArray.length(); i++){
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if (tweet != null){
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        // Return the finished list
        return tweets;

    }

}
