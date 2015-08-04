package com.isadev.apps.IsaTweets.fragments;

import android.os.Bundle;
import android.util.Log;

import com.isadev.apps.IsaTweets.TwitterApplication;
import com.isadev.apps.IsaTweets.TwitterClient;
import com.isadev.apps.IsaTweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Isaac on 8/3/2015.
 */
public class UserTimelineFragment extends TweetsListFragment{
    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the client
        client = TwitterApplication.getRestClient();//Singleton client
        populateTimeline();
    }
    // Creates a new fragment given an int and title
    // UserTimelineFragment.newInstance("isasam");
    public static UserTimelineFragment newInstance(String screen_name) {
        UserTimelineFragment userfragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("Screen_name", screen_name);
        userfragment.setArguments(args);
        return userfragment;
    }

    // send an API request to get a timeline json
    // fill the listview by creating the tweet objects from the json
    private void populateTimeline() {
        String screenName = getArguments().getString("screen_name");
        client.getUserTimeline(screenName, new JsonHttpResponseHandler() {
            //SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                // DESERIALIZE JSON
                // CREATE MODELS AND ADD THEM TO THE ADAPTER
                // LOAD THE MODEL DATA INTO LISTVIEW
                addAll(Tweet.fromJSONArray(json));
                //Log.d("DEBUG", aTweets.toString());
            }

            //FAILURE
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }
}
